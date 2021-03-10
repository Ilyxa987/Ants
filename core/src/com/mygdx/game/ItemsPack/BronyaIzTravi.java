package com.mygdx.game.ItemsPack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class BronyaIzTravi extends  Armor {

    Texture armour;

    public BronyaIzTravi() {
        defence = 5;
        armour = new Texture("Armour.png");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(armour, getX(), getY());
    }
}
