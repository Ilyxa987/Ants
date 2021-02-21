package com.mygdx.game.UnitsPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ItemsPack.Armor;
import com.mygdx.game.ItemsPack.Weapon;


public class Player extends Units {



    public Player(String name, float x, float y) {
        super(name, x, y);
        hitpoints = 20;
        activeWeapon = new Weapon(20, 5);
        activeArmor = new Armor(20);
        damage = activeWeapon.damage;
        defence = activeArmor.defence;
        img = new Texture("icon.png");
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
    }
}

