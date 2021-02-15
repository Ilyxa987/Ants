package com.mygdx.game.SkillsPack;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public abstract class Skills extends TextButton {
    public Skills(String text, TextButtonStyle style) {
        super(text, style);
    }


    public void use(){}
}
