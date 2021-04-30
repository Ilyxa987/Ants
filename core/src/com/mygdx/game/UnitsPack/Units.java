package com.mygdx.game.UnitsPack;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.GameSc.GameMap;
import com.mygdx.game.GameSc.HealthBar;
import com.mygdx.game.ItemsPack.Armor;
import com.mygdx.game.ItemsPack.Weapon;

import sun.rmi.runtime.Log;


public abstract class Units extends Actor{
    Player player;
    public String name;
    public int hitpoints, Speed = 100;
    public float x, y, dx, dy;
    public final int k = 25;
    public int steps;
    public boolean Move = false, Attack = false;
    public Weapon activeWeapon;
    public Armor activeArmor;
    int damage;
    int defence;
    public HealthBar healthBar;
    boolean alive = true;
    public int actionPoint;
    int stepMetr = 0;
    int radios;
    GameMap gameMap;
    Rectangle rectangle;
    //TiledMap map;
    //MapLayer fone, ground, foreground;
    public Texture animll;






    public Units(String name, float x, float y, GameMap gameMap) {
        this.gameMap = gameMap;
        this.name = name;
        this.x = x;
        this.y = y;
        healthBar = new HealthBar(20, 0, 0);
        rectangle = new Rectangle(x, y, 64, 98);
        animll = new Texture("anLeftE.png");
//        gameMap.fone = gameMap.map.getLayers().get("first");
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getDefence() {
        return defence;
    }

    public int getDamage() {
        return damage;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void Move(float x, float y) {
        if (actionPoint >= 1) {
            steps = (int) (k * Math.sqrt((x - this.x) * (x - this.x) + (y - this.y) * (y - this.y)) / Speed);
            dx = (x - this.x) / steps;
            dy = (y - this.y) / steps;
            if (this.x == x && this.y == y) {
                this.Stop();
            } else {
                this.x += dx;
                this.y += dy;
                stepMetr++;
                if (stepMetr % 30 == 0)
                    actionPoint --;
            }
        }
    }

    public void Stop() {
        this.x += 0;
        this.y += 0;
        Move = false;
    }


    public void attack(Units a) {
        if (actionPoint >= 2) {
            int d;
            if (this.damage - a.defence <= 0)
                d = 0;
            else
                d = this.damage - a.defence;
            if (a.getX() - this.getX() -64 < this.radios &&
                    this.getX() - a.getX() - 64 < this.radios &&
                    a.getY() - this.getY() - 98 < this.radios &&
                    this.getY() - a.getY() - 98 < this.radios) {
                a.hitpoints -= d;
                a.Damage();
                actionPoint -= 2;
            }
            Attack = false;
        }
    }

    //Функция получения урона
    public void Damage() {
        if (hitpoints > 0)
            healthBar.setValue(this.hitpoints);
        else {
            healthBar.setValue(0);
        }
        if (hitpoints <= 0 && alive) {
            death();//Смерть персонажа
        }
        System.out.println("IIII");
    }

    public void actionListener() {
        if (actionPoint <= 0) {
            stepMetr = 0;
            if (gameMap.unitsArray.indexOf(this) + 1 < gameMap.unitsArray.size()) {
                gameMap.activeUnit = gameMap.unitsArray.get(gameMap.unitsArray.indexOf(this) + 1);
                gameMap.activeUnit.actionPoint = 4;
                gameMap.activeUnit.Move = true;
            }
            else {
                gameMap.activeUnit = gameMap.unitsArray.get(0);
                gameMap.activeUnit.actionPoint = 4;
            }
        }
    }


    public void draw(SpriteBatch batch){}
    public void update(Units units) {
        if (x + 64 >= units.getX()
                && y >= units.getY() - 98
                && y <= units.getY() + 98
                && x <= units.getX() + 64
                && alive == true) {
            units.Stop();
            Stop();
        }

        /*for (int i = 0; gameMap.mapObjects.iterator().hasNext(); i++) {
            MapProperties mapProperties = gameMap.mapObject.getProperties();

            float width, height, x, y;
            Rectangle objectRectangle = new Rectangle();
            width = (float) mapProperties.get("width");
            height = (float) mapProperties.get("height");
            x = (float) mapProperties.get("x");
            y = (float) mapProperties.get("y");
            objectRectangle.set(x, y, width, height);
            gameMap.walls.add(objectRectangle);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + gameMap.walls.size());
        }
*/
//        for (RectangleMapObject rectangleMapObject: gameMap.mapObjects.getByType(RectangleMapObject.class)) {
//            Rectangle rectangle = rectangleMapObject.getRectangle();
//            if (Intersector.overlaps(rectangle, this.getRectangle()))
//                Stop();
//        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void death() {
        alive = false;
        gameMap.unitsArray.remove(this);
    }
}