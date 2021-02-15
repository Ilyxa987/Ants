package com.mygdx.game.SkillsPack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class AttackSkill extends Skills {
    Texture texture;
    public static boolean attack = false;
    public AttackSkill(String text, TextButtonStyle style) {
        super(text, style);
        texture = new Texture("Attack.png");

        Skin skin = new Skin();
        BitmapFont font = new BitmapFont();

        skin.add("Attack", texture);

        TextButton.TextButtonStyle attackStyle = new TextButtonStyle();
        attackStyle.font = font;
        attackStyle.up = skin.getDrawable("Attack");
        attackStyle.down = skin.getDrawable("Attack");
        attackStyle.checked = skin.getDrawable("Attack");

        setStyle(attackStyle);
        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                attack = true;
                System.out.println("ATTACK");
            }
        });
    }
}
