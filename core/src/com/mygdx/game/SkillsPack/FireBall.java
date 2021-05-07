package com.mygdx.game.SkillsPack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class FireBall extends Skills{

    Texture texture;
    public static boolean Fire = false;
    public FireBall(String text, TextButtonStyle style) {
        super(text, style);
        texture = new Texture("fireball.png");

        Skin skin = new Skin();
        BitmapFont font = new BitmapFont();

        skin.add("FireBall", texture);

        TextButton.TextButtonStyle fireBallStyle = new TextButtonStyle();
        fireBallStyle.font = font;
        fireBallStyle.up = skin.getDrawable("FireBall");
        fireBallStyle.down = skin.getDrawable("FireBall");
        fireBallStyle.checked = skin.getDrawable("FireBall");

        setStyle(fireBallStyle);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                   Fire = true;
            }
        });
        font.dispose();
    }

    public void draw(Batch batch, float parentAlpha, float x, float y) {
        setPosition(x, y);
        super.draw(batch, parentAlpha);
    }
}
