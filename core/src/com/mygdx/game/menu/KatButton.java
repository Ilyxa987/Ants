package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.GameSc.Games;
import com.mygdx.game.MyGame;

public class KatButton extends Stage {
    final MyGame game;
    public TextButton skip;
    Texture skipImg;
    public Games game1;

    public  KatButton(final MyGame game){
        this.game = game;

        skipImg = new Texture("skip.png");
        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();
        skin.add("skip", skipImg);

        TextButton.TextButtonStyle skipStyle = new TextButton.TextButtonStyle();
        skipStyle.font = font;
        skipStyle.up = skin.getDrawable("skip");
        skipStyle.down = skin.getDrawable("skip");
        skipStyle.checked = skin.getDrawable("skip");

        skip = new TextButton("",skipStyle);
        skip.setPosition(Gdx.graphics.getWidth()*0.9f, Gdx.graphics.getHeight()*0.1f);
        addActor(skip);

        skip.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Games(game));
                dispose();
            }
        });
        skip.setDisabled(true);
        font.dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
