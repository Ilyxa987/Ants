package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.MyGame;
import com.mygdx.game.menu.Menu;

public class DeathButtons extends Stage {
    final MyGame myGame;
    Texture tryImage, exitImage;
    public TextButton exit, tryButton;
    public static boolean cameraMove = false;

    public DeathButtons(final MyGame myGame) {
        this.myGame = myGame;

        tryImage = new Texture("try.png");
        exitImage = new Texture("exit.png");

        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();

        skin.add("exit", exitImage);
        skin.add("try", tryImage);

        TextButton.TextButtonStyle exitStyle = new TextButton.TextButtonStyle();
        exitStyle.font = font;
        exitStyle.up = skin.getDrawable("exit");
        exitStyle.down = skin.getDrawable("exit");
        exitStyle.checked = skin.getDrawable("exit");

        TextButton.TextButtonStyle tryStile = new TextButton.TextButtonStyle();
        tryStile.font = font;
        tryStile.up = skin.getDrawable("try");
        tryStile.checked = skin.getDrawable("try");
        tryStile.down = skin.getDrawable("try");

        exit = new TextButton("", exitStyle);
        exit.setPosition(Gdx.graphics.getWidth() *0.5f - exitImage.getWidth() *0.5f, Gdx.graphics.getHeight() *0.3f - 4 - tryImage.getHeight() - exitImage.getHeight() / 2);
        addActor(exit);

        tryButton = new TextButton("", tryStile);
        tryButton.setPosition(Gdx.graphics.getWidth()*0.5f - tryImage.getWidth()*0.5f, Gdx.graphics.getHeight()*0.3f - tryImage.getHeight()*0.5f);
        addActor(tryButton);

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
           myGame.setScreen(new Menu(myGame));
            }
        });

        tryButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
           myGame.setScreen(new Games(myGame));
            }
        });

        tryButton.setDisabled(true);
        exit.setDisabled(true);

    }
}

