package com.mygdx.game.UnitsPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.GameSc.HealthBar;
import com.mygdx.game.SkillsPack.AttackSkill;



public class Enemy extends Units {
    Texture img = new Texture("enemy.png");
    Player player;


    public Enemy(String name, float x, float y, final Player player) {
        super(name, x, y);
        this.player = player;
        this.setBounds(x, y, 64, 64);
        hitpoints = 40;
        healthBar = new HealthBar(hitpoints, this.x, this.y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img, x, y);
        healthBar.draw(batch, 1);
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
