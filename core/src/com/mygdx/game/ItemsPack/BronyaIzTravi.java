package com.mygdx.game.ItemsPack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class BronyaIzTravi extends  Armor {

    Texture armour;

    public BronyaIzTravi() {
        name = "BronyaIzTravi";
        defence = 5;
        armour = new Texture("Armour.png");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(armour, getX(), getY());
    }
}
