package com.mygdx.game.ItemsPack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class BronyaIzTravi extends  Armor {

    Texture armour;

    public BronyaIzTravi(String text, TextButtonStyle style) {
        super(text, style);
        defence = 5;
        armour = new Texture("Armour.png");

        Skin skin = new Skin();
        BitmapFont font = new BitmapFont();

        skin.add("armour", armour);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("armour");
        textButtonStyle.font = font;
        textButtonStyle.down = skin.getDrawable("armour");
        textButtonStyle.checked = skin.getDrawable("armour");

        setStyle(textButtonStyle);
    }
}
