package com.mygdx.game.UnitsPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.Rectangle;


public class Enemy extends Units {
    Texture img = new Texture("enemy.png");


    public Enemy(String name, float x, float y) {
        super(name, x, y);
        this.setBounds(x, y, x + 64, y + 64);
        hitpoints = 20;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img, x, y);
    }

    @Override
    public void update(Units units) {
        if (x + 64 > Gdx.graphics.getWidth()) {
            x = Gdx.graphics.getWidth() - 64;
        }
        if (x < 0) {
            x = 0;
        }
        if (y + 64 > Gdx.graphics.getHeight()) {
            y = Gdx.graphics.getHeight() - 64;
        }
        if (y < 0) {
            y = 0;
        }
    }
}
