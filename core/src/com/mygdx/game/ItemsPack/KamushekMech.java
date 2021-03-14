package com.mygdx.game.ItemsPack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class KamushekMech extends Weapon {
    Texture sword;

    public KamushekMech(String text, TextButtonStyle style) {
        super(text, style);
        sword = new Texture("sword.png");
        damage = 30;
        radios = 30;

        Skin skin = new Skin();
        BitmapFont font = new BitmapFont();

        skin.add("sword", sword);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("sword");
        textButtonStyle.font = font;
        textButtonStyle.down = skin.getDrawable("sword");
        textButtonStyle.checked = skin.getDrawable("sword");

        setStyle(textButtonStyle);
    }
}
