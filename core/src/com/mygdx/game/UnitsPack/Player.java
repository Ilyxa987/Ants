package com.mygdx.game.UnitsPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.awt.Rectangle;


public class Player extends Units {
    Texture img = new Texture("icon.png");



    public Player(String name, float x, float y) {
        super(name, x, y);
        hitpoints = 20;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img, x, y);
    }

    @Override
    public void update(Units units) {
        if (x + 32 > Gdx.graphics.getWidth()) {
            x = Gdx.graphics.getWidth() - 32;
        }
        if (x < 0) {
            x = 0;
        }
        if (y + 32 > Gdx.graphics.getHeight()) {
            y = Gdx.graphics.getHeight() - 32;
        }
        if (y < 0) {
            y = 0;
        }
        if (x + 32 >= units.getX() && y >= units.getY() - 32 && y <= units.getY() + 64 && x <= units.getX() + 64)
            this.Stop();
    }
}

