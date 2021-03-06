package com.mygdx.game.GameSc;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Effects.FireEffect;
import com.mygdx.game.ItemsPack.Items;
import com.mygdx.game.SkillsPack.AttackSkill;
import com.mygdx.game.SkillsPack.FireBall;
import com.mygdx.game.UnitsPack.Enemy;
import com.mygdx.game.UnitsPack.Player;
import com.mygdx.game.UnitsPack.Units;

import java.util.ArrayList;

public class GameMap extends Stage implements GestureDetector.GestureListener {
    final Games games;
    public Player player;
    Enemy enemy;
    Vector3 touchPos, scroll;
    OrthographicCamera camera;
    public ArrayList<Units> unitsArray;
    public ArrayList<Items> itemsArrayList;
    public Units activeUnit;
    TiledMap map;
    public MapObjects mapObjects;
    OrthoCachedTiledMapRenderer renderer;
    ArrayList<FireEffect> fireEffects;
    boolean fire = false, take = true;
    SpriteBatch batch;
    int[] fone;
    int[] ground;
    Texture trainer, task;
    float stateTime = 0f;



    public GameMap(Games games) {
        this.games = games;

        player = new Player("Player", 1, 1, this);
        enemy = new Enemy("Enemy", Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight()/3, this, player);
        unitsArray = new ArrayList<>();
        unitsArray.add(player);
        unitsArray.add(enemy);
        activeUnit = unitsArray.get(0);
        itemsArrayList = new ArrayList<>();
        fireEffects = new ArrayList<>();


        camera = new OrthographicCamera();
        touchPos = new Vector3();
        scroll = new Vector3();
        camera.unproject(touchPos);
        camera.setToOrtho(false, 1560, 720);
        camera.update();

        map = new TmxMapLoader().load("newEraAlfa.tmx");
        MapLayer mapLayer = map.getLayers().get("water");
        mapObjects = mapLayer.getObjects();

        renderer = new OrthoCachedTiledMapRenderer(map, 1, 5000);

        batch = new SpriteBatch();

       fone = new int[] {0};
       ground = new int[] {1};
       trainer = new Texture("trainer.png");
       task = new Texture("Task.png");
       stateTime = 0f;
    }


    @Override
    public void draw() {
        stateTime += Gdx.graphics.getDeltaTime();
        super.draw();
        batch.begin();
        camera.update();
        renderer.setView(camera);
        renderer.render(fone);

        if (fireEffects != null){
            for (int i = 0; i < fireEffects.size(); i++) {
                System.out.println("SIZE " + fireEffects.size());
                fireEffects.get(i).draw(batch, 1);
                if (activeUnit.Attack || activeUnit.Move)
                fireEffects.get(i).damage(activeUnit);
            }
            fire = false;
        }
        for (int i = 0; i < itemsArrayList.size(); i++) {
            itemsArrayList.get(i).draw(batch, 1);
        }
        if (activeUnit.Move) {
            if (activeUnit == player)
                activeUnit.Move(touchPos.x, touchPos.y);
            else
                activeUnit.Move(player.getX(), player.getY());
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
            if (unitsArray.get(i) == enemy)
               unitsArray.get(i).update(player);
            else
                unitsArray.get(i).update(enemy);
            unitsArray.get(i).draw(batch);
        }
        renderer.render(ground);
        if (!take)
            take = true;
        if (stateTime<3) batch.draw(trainer, 0, 0);
        if (stateTime>3&& stateTime<7) batch.draw(task,0.2f*Gdx.graphics.getWidth(),Gdx.graphics.getHeight()*0.1f);
        batch.end();
    }

    public Games getGames() {
        return games;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        touchPos.set(x, y, 0);
        camera.unproject(touchPos);
        if (touchPos.x >= enemy.getX() && AttackSkill.attack == true){
            System.out.println("RRRRRRR");
            player.Attack = true;
            player.Move = false;
        }
        else if (FireBall.Fire && activeUnit == player && activeUnit.actionPoint >= 2) {
            for (int i = 0; i < 1; i++) {
                fireEffects.add(new FireEffect(touchPos.x /*- 64 + i % 4 * 32*/, touchPos.y /*+ 64 - j * 32*/));
            }
            FireBall.Fire = false;
            player.Move = false;
            fire = true;
            activeUnit.actionPoint -= 2;
        }
        else if (!fire) {
            player.Attack = false;
            if (!BattleButtons.cameraMove)
            player.Move = true;
            if (itemsArrayList != null && take) {
                for (int i = 0; i < itemsArrayList.size(); i++) {
                    if (touchPos.x >= itemsArrayList.get(i).getX()) {
                        player.takeItem(itemsArrayList.get(i));
                        itemsArrayList.remove(i);
                        take = false;
                        break;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Gdx.app.log("DELTA", deltaX + " " + deltaY);
        if (BattleButtons.cameraMove) {
            camera.translate(-deltaX * 0.001f, deltaY * 0.001f);
            camera.update();
            if (camera.position.x < camera.viewportWidth / 2)
                camera.position.set(camera.viewportWidth / 2, camera.position.y, camera.position.z);
            if (camera.position.y < camera.viewportHeight / 2)
                camera.position.set(camera.position.x, camera.viewportHeight / 2, camera.position.z);
            if (camera.position.y > camera.viewportHeight * 2 - 200)
                camera.position.set(camera.position.x, camera.viewportHeight * 2 - 200, camera.position.z);
            if (camera.position.x > camera.viewportWidth / 2 + 50)
                camera.position.set(camera.viewportWidth / 2 + 50, camera.position.y, camera.position.z);
        }
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {
        batch = new SpriteBatch();
    }

    @Override
    public void dispose() {
        super.dispose();
        map.dispose();
        renderer.dispose();
    }
}
