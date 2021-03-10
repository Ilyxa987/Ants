package com.mygdx.game.ItemsPack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class KamushekMech extends Weapon {
    Texture sword;

    public KamushekMech() {
        sword = new Texture("sword.png");
        damage = 30;
        radios = 30;
        sword = new Texture("Sword.jpg");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(sword, getX(), getY());
    }
}
