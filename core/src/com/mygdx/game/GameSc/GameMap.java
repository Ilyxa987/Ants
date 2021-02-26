package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    int mapArray[][];
    float height = Gdx.graphics.getHeight(), width = Gdx.graphics.getWidth();
    float x, y;
    Player player;
    Enemy enemy;
    Vector3 touchPos;
    OrthographicCamera camera;
    SkillBar skillBar;
    public ArrayList<Units> unitsArray;
    public Units activeUnit;


    public GameMap(Games games) {
        this.games = games;

        player = new Player("Player", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, this);
        enemy = new Enemy("Enemy", Gdx.graphics.getWidth() / 3 * 2, Gdx.graphics.getHeight() / 2, this, player);
        unitsArray = new ArrayList<>();
        unitsArray.add(player);
        unitsArray.add(enemy);
        activeUnit = unitsArray.get(0);

        skillBar = new SkillBar();

        camera = new OrthographicCamera();
        touchPos = new Vector3();
        camera.unproject(touchPos);


        spriteOne = new Texture("spriteOne.png");
        spriteTwo = new Texture("spriteTwo.png");

        mapArray = new int[(int) height / textureSize][(int) width / textureSize];

        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                if (i <= mapArray.length / 4 || j <= mapArray[i].length / 4 || i >= mapArray.length / 4 * 3 || j >= mapArray[i].length / 4 * 3)
                    mapArray[i][j] = 1;
                else
                    mapArray[i][j] = 0;
            }
        }
    }


    @Override
    public void draw() {
        super.draw();
        x = 0;
        y = 0;
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                if (mapArray[i][j] == 1)
                    batch.draw(spriteTwo, x, y);
                else
                    batch.draw(spriteOne, x, y);
                x += textureSize;
            }
            y += textureSize;
            x = 0;
        }
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
        for (int i = 0; i < unitsArray.size(); i++) {
            unitsArray.get(i).draw(batch);
            unitsArray.get(i).update(player);
        }
        batch.end();
        System.out.println("Move " + activeUnit.Move + " ActiveUnit " + activeUnit + " actionPoint " + activeUnit.actionPoint + " Attack " + activeUnit.Attack);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
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
}
