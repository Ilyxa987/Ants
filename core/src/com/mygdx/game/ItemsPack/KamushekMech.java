package com.mygdx.game.ItemsPack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class KamushekMech extends Weapon {
    Texture sword;

    public KamushekMech() {
        name = "KamushekMech";
        sword = new Texture("sword.png");
        damage = 30;
        radios = 5;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(sword, getX(), getY());
    }
}
