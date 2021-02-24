package com.mygdx.game.UnitsPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameSc.GameMap;
import com.mygdx.game.GameSc.HealthBar;
import com.mygdx.game.ItemsPack.Armor;
import com.mygdx.game.ItemsPack.Weapon;


public class Player extends Units {



    public Player(String name, float x, float y, GameMap gameMap) {
        super(name, x, y, gameMap);
        hitpoints = 60;
        healthBar = new HealthBar(hitpoints, this.x, this.y);
        activeWeapon = new Weapon(20, 5);
        activeArmor = new Armor(10);
        damage = activeWeapon.damage;
        defence = activeArmor.defence;
        img = new Texture("icon.png");
        actionPoint = 4;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img, x, y);
        healthBar.draw(batch, 1, x, y);
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

