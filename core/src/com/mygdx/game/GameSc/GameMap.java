package com.mygdx.game.GameSc;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Effects.FireEffect;
import com.mygdx.game.SkillsPack.AttackSkill;
import com.mygdx.game.SkillsPack.FireBall;
import com.mygdx.game.UnitsPack.Enemy;
import com.mygdx.game.UnitsPack.Player;
import com.mygdx.game.UnitsPack.Units;

import java.util.ArrayList;

public class GameMap extends Stage implements GestureDetector.GestureListener {
    final Games games;
    Player player;
    Enemy enemy;
    Vector3 touchPos, scroll;
    OrthographicCamera camera;
    public ArrayList<Units> unitsArray;
    public Units activeUnit;
    TiledMap map;
    TiledMapTileLayer tiledMapTileLayer;
    public MapObjects mapObjects;
    OrthoCachedTiledMapRenderer renderer;
    ArrayList<FireEffect> fireEffects;
    boolean fire = false;
    int j;


    public GameMap(Games games) {
        this.games = games;

        player = new Player("Player", 0, 0, this);
        enemy = new Enemy("Enemy", Gdx.graphics.getWidth() / 3 * 2, Gdx.graphics.getHeight() / 2, this, player);
        unitsArray = new ArrayList<>();
        unitsArray.add(player);
        unitsArray.add(enemy);
        activeUnit = unitsArray.get(0);


        camera = new OrthographicCamera();
        touchPos = new Vector3();
        scroll = new Vector3();
        camera.unproject(touchPos);
        camera.setToOrtho(false, 1560, 720);
        camera.update();

        map = new TmxMapLoader().load("mapp.tmx");
        tiledMapTileLayer = (TiledMapTileLayer) map.getLayers().get("walls");
        mapObjects = tiledMapTileLayer.getObjects();

        renderer = new OrthoCachedTiledMapRenderer(map, 1, 5000);
    }


    @Override
    public void draw() {
        super.draw();
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        camera.update();
        renderer.setView(camera);
        renderer.render();
        if (fireEffects != null){
            for (int i = 0; i < fireEffects.size(); i++) {
                System.out.println("SIZE " + fireEffects.size());
                fireEffects.get(i).draw(batch, 1);
                if (activeUnit.Attack || activeUnit.Move)
                fireEffects.get(i).damage(activeUnit);
            }
            fire = false;
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
            unitsArray.get(i).update(player);
            unitsArray.get(i).draw(batch);
        }
        batch.end();
        //player.exchangeActiveItems();
    }

//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        System.out.println("Coordinates Camera " + camera.position.x + " " + camera.position.y);
//        touchPos.set(screenX, screenY, 0);
//        camera.unproject(touchPos);
//        if (touchPos.x >= enemy.getX() && AttackSkill.attack == true){
//            System.out.println("RRRRRRR");
//            player.Attack = true;
//            player.Move = false;
//        }
//        else {
//            player.Attack = false;
//            player.Move = true;
//        }
//        return true;
//    }

//    @Override
//    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        if (Gdx.input.getCurrentEventTime() > 20000 * 20) {
//            touchPos.set(screenX, screenY, 0);
//            camera.unproject(touchPos);
//            camera.position.set(Gdx.graphics.getWidth() - screenX, screenY, camera.position.z);
//            System.out.println("Coordinates Camera " + camera.position.x + " " + camera.position.y);
//        }
//        return super.touchDragged(screenX, screenY, pointer);
//    }


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
            fireEffects = new ArrayList<>();
            for (int i = 0; i < 16; i++) {
                if (i % 4 == 0)
                    j = i / 4;
                fireEffects.add(new FireEffect(touchPos.x - 64 + i % 4 * 32, touchPos.y + 64 - j * 32));
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

    }

    @Override
    public void dispose() {
        super.dispose();
        map.dispose();
        renderer.dispose();
    }
}
