package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.SkillsPack.AttackSkill;
import com.mygdx.game.SkillsPack.Skills;
import com.mygdx.game.UnitsPack.Enemy;
import com.mygdx.game.UnitsPack.Player;
import com.mygdx.game.UnitsPack.Units;

import java.util.ArrayList;

import sun.rmi.runtime.Log;

public class GameMap extends Stage {
    int textureSize = 32;
    Texture spriteOne, spriteTwo;
    final Games games;
    float height = Gdx.graphics.getHeight(), width = Gdx.graphics.getWidth();
    Player player;
    Enemy enemy;
    Vector3 touchPos, scroll;
    OrthographicCamera camera;
    SkillBar skillBar;
    public ArrayList<Units> unitsArray;
    public Units activeUnit;
    TiledMap map;
    OrthoCachedTiledMapRenderer renderer;


    public GameMap(Games games) {
        this.games = games;

        player = new Player("Player", 0, 0, this);
        enemy = new Enemy("Enemy", Gdx.graphics.getWidth() / 3 * 2, Gdx.graphics.getHeight() / 2, this, player);
        unitsArray = new ArrayList<>();
        unitsArray.add(player);
        unitsArray.add(enemy);
        activeUnit = unitsArray.get(0);

        skillBar = new SkillBar();

        camera = new OrthographicCamera();
        touchPos = new Vector3();
        scroll = new Vector3();
        camera.unproject(touchPos);
        camera.setToOrtho(false, 1200, 720); // При изменении этого параметра выходит BufferOverFlowException
        camera.update();

        map = new TmxMapLoader().load("mapp.tmx");

        renderer = new OrthoCachedTiledMapRenderer(map);
    }


    @Override
    public void draw() {
        super.draw();
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        camera.update();
        renderer.setView(camera);
        renderer.render();
        if (activeUnit.Move) {
            if (activeUnit == player)
                activeUnit.Move(touchPos.x, touchPos.y);
            else
                activeUnit.Move(player.getX(), Gdx.graphics.getHeight() - player.getY());
        }
        if (activeUnit.Attack) {
            if (activeUnit == player) {
                activeUnit.attack(enemy);
                AttackSkill.attack = false;
                System.out.println("ENEMY");
            }
            else {
                if (activeUnit.actionPoint >= 2) {
                    activeUnit.attack(player);
                    if (activeUnit.actionPoint <= 1)
                        activeUnit.actionPoint = 0;
                }
                else
                    activeUnit.actionPoint = 0;
            }
        }
        activeUnit.actionListener();
        player.changeActiveItems();
        for (int i = 0; i < unitsArray.size(); i++) {
            unitsArray.get(i).draw(batch);
            unitsArray.get(i).update(player);
        }
        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Coordinates Camera " + camera.position.x + " " + camera.position.y);
        touchPos.set(screenX, screenY, 0);
        if (touchPos.x >= enemy.getX() && AttackSkill.attack == true){
            System.out.println("RRRRRRR");
            player.Attack = true;
            player.Move = false;
        }
        else {
            player.Attack = false;
            player.Move = true;
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        screenX = (int) (screenX + (camera.position.x - 329.11108));
        screenY = (int) (screenY + ((camera.position.y - 160) / 1048209));
        camera.position.set(Gdx.graphics.getWidth() - screenX, screenY, camera.position.z);
        return super.touchDragged(screenX, screenY, pointer);
    }

    public Games getGames() {
        return games;
    }
}
