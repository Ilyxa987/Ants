package com.mygdx.game.GameSc;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Effects.FireEffect;
import com.mygdx.game.ItemsPack.Items;
import com.mygdx.game.SkillsPack.AttackSkill;
import com.mygdx.game.SkillsPack.FireBall;
import com.mygdx.game.UnitsPack.Enemy;
import com.mygdx.game.UnitsPack.Player;
import com.mygdx.game.UnitsPack.Units;

import java.util.ArrayList;

import sun.rmi.runtime.Log;

public class GameMap extends Stage implements GestureDetector.GestureListener {
    final Games games;
    Player player;
    Enemy enemy;
    Vector3 touchPos, scroll;
    OrthographicCamera camera;
    public ArrayList<Units> unitsArray;
    public ArrayList<Items> itemsArrayList;
    public Units activeUnit;
    public TiledMap map;
    TiledMapTileLayer tiledMapTileLayer;
    public MapLayer fone, ground, foreground;
    public MapObjects mapObjects;
    public MapObject wallOblekt, wallOblekt2, wallOblekt3, wallOblekt4, wallOblekt5, wallOblekt6, wallOblekt7, mapObject ;
    OrthoCachedTiledMapRenderer renderer;
    ArrayList<FireEffect> fireEffects;
    public ArrayList<Rectangle> walls;
    boolean fire = false, take = true;
    int j;
    public float oY, oX, oH, oW,
            oY2, oX2, oH2, oW2,
            oY3, oX3, oH3, oW3,
            oY4, oX4, oH4, oW4,
            oY5, oX5, oH5, oW5,
            oY6, oX6, oH6, oW6,
            oY7, oX7, oH7, oW7;
    public  Rectangle rect;


    public GameMap(Games games) {
        this.games = games;

        player = new Player("Player", 0, 0, this);
        enemy = new Enemy("Enemy", Gdx.graphics.getWidth() / 3 * 2, Gdx.graphics.getHeight() / 2, this, player);
        unitsArray = new ArrayList<>();
        unitsArray.add(player);
        unitsArray.add(enemy);
        activeUnit = unitsArray.get(0);
        itemsArrayList = new ArrayList<>();
        fireEffects = new ArrayList<>();
        walls = new ArrayList<>();


        camera = new OrthographicCamera();
        touchPos = new Vector3();
        scroll = new Vector3();
        camera.unproject(touchPos);
        camera.setToOrtho(false, 1560, 720);
        camera.update();

        map = new TmxMapLoader().load("newEra.tmx");
        fone = map.getLayers().get("first");
        //ground = map.getLayers().get("first");
        foreground = map.getLayers().get("second");
       // wallOblekt = fone.getObjects().get(49);


        mapObjects = map.getLayers().get(0).getObjects();

        for (MapObject object:mapObjects) {
            MapProperties mapProperties = mapObject.getProperties();
if (object instanceof TextureMapObject){
    TextureMapObject mapObject = (TextureMapObject)object;
    this.rect = new Rectangle(mapObject.getX(), mapObject.getY(),
            mapObject.getTextureRegion().getRegionWidth(), mapObject.getTextureRegion().getRegionHeight());
    walls.add(this.rect);
}
else
{this.rect = ((RectangleMapObject)object).getRectangle();}
            /*float width, height, x, y;
            Rectangle objectRectangle = new Rectangle();
                width = (float) mapProperties.get("width");
                height = (float) mapProperties.get("height");
                x = (float) mapProperties.get("x");
                y = (float) mapProperties.get("y");
                objectRectangle.set(x, y, width, height);
                walls.add(objectRectangle);
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + walls.size());*/
        }


            renderer = new OrthoCachedTiledMapRenderer(map, 1, 5000);
    }


    @Override
    public void draw() {
        int[] fone = {0};
        int[] ground = {1};
        super.draw();
        SpriteBatch batch = new SpriteBatch();
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
            unitsArray.get(i).update(player);
            unitsArray.get(i).draw(batch);
        }
        renderer.render(ground);
        if (!take)
            take = true;
        batch.end();
        //player.exchangeActiveItems();
    }
    /*public boolean getCollision(int layerIndex) {
        MapObjects mapObjects = map.getLayers().get(layerIndex).getObjects();

        for (MapObject mapObject : mapObjects) {
            MapProperties mapProperties = mapObject.getProperties();

            float width, height, x, y;
            Rectangle objectRectangle = new Rectangle();
            Rectangle playerRectangle = new Rectangle();

            if (mapProperties.containsKey("width") && mapProperties.containsKey("height") && mapProperties.containsKey("x") && mapProperties.containsKey("y")) {
                width = (float) mapProperties.get("width");
                height = (float) mapProperties.get("height");
                x = (float) mapProperties.get("x");
                y = (float) mapProperties.get("y");
                objectRectangle.set(x, y, width, height);
            }

            playerRectangle.set(
                    activeUnit.getX(),
                    activeUnit.getY(),
                    player.animll.getWidth(),
                    player.animll.getHeight());

// If the player rectangle and the object rectangle is colliding, return the object
            if (Intersector.overlaps(objectRectangle, playerRectangle)) {
                return true;
            }
        }

// If no colliding object was found in that layer
        return false;
    }*/

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
            for (int i = 0; i < 2; i++) {
                /*if (i % 4 == 0)
                    j = i / 4;*/
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

    }

    @Override
    public void dispose() {
        super.dispose();
        map.dispose();
        renderer.dispose();
    }
}
