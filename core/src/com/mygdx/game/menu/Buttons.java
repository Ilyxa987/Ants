package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.GdxBuild;
import com.mygdx.game.GameSc.Games;
import com.mygdx.game.MyGame;


public class Buttons extends Stage {
    final MyGame game;
    Texture buttonUpImage, buttonDownImage, settingsImage, exitImage, bluetoothTex;
    TextButton button, settings, exit, bluetoothServerButton, bluetoothClientButton;
    public Games game1;
    final Menu menu;
    BitmapFont font1;

    public Buttons(final MyGame mygame, final Menu menu) {
        game = mygame;

        this.menu = menu;

        game1 = new Games(game, this.menu);

        Pixmap pixmap = new Pixmap(300, 100, Pixmap.Format.RGB888);
        pixmap.setColor(0, 0, 120, 1);
        pixmap.fill();

        bluetoothTex = new Texture(pixmap);

        buttonUpImage = new Texture("play.png");
        buttonDownImage = new Texture("play.png");
        settingsImage = new Texture("settings.png");
        exitImage = new Texture("exit.png");

        pixmap.dispose();

        BitmapFont font = new BitmapFont();
        font1 = new BitmapFont();
        font1.setColor(0, 0, 0,0);
        Skin skin = new Skin();

        skin.add("play", buttonUpImage);
        skin.add("play", buttonDownImage);
        skin.add("settings", settingsImage);
        skin.add("exit", exitImage);
        skin.add("blue", bluetoothTex);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("play");
        textButtonStyle.down = skin.getDrawable("play");
        textButtonStyle.checked = skin.getDrawable("play");

        TextButton.TextButtonStyle settingsStyle = new TextButton.TextButtonStyle();
        settingsStyle.font = font;
        settingsStyle.up = skin.getDrawable("settings");
        settingsStyle.down = skin.getDrawable("settings");
        settingsStyle.checked = skin.getDrawable("settings");

        TextButton.TextButtonStyle exitStyle = new TextButton.TextButtonStyle();
        exitStyle.font = font;
        exitStyle.up = skin.getDrawable("exit");
        exitStyle.down = skin.getDrawable("exit");
        exitStyle.checked = skin.getDrawable("exit");

        TextButton.TextButtonStyle bluetoothButtonStyle = new TextButton.TextButtonStyle();
        bluetoothButtonStyle.font = font;
        bluetoothButtonStyle.up = skin.getDrawable("blue");
        bluetoothButtonStyle.down = skin.getDrawable("blue");
        bluetoothButtonStyle.checked = skin.getDrawable("blue");

        button = new TextButton("", textButtonStyle);
        button.setPosition(Gdx.graphics.getWidth() / 2 - buttonUpImage.getWidth() / 2, Gdx.graphics.getHeight() / 2 + settingsImage.getHeight() / 2);
        addActor(button);

        settings = new TextButton("", settingsStyle);
        settings.setPosition(Gdx.graphics.getWidth() / 2 - settingsImage.getWidth() / 2, Gdx.graphics.getHeight() / 2 - settingsImage.getHeight() / 2);
        addActor(settings);

        exit = new TextButton("", exitStyle);
        exit.setPosition(Gdx.graphics.getWidth() / 2 - exitImage.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 2 - settingsImage.getHeight() - exitImage.getHeight() / 2);
        addActor(exit);

//        bluetoothServerButton = new TextButton("Bluetooth Server", bluetoothButtonStyle);
//        bluetoothServerButton.setPosition(1000, 100);
//        addActor(bluetoothServerButton);
//
//        bluetoothClientButton = new TextButton("Client", bluetoothButtonStyle);
//        bluetoothClientButton.setPosition(1000, 300);
//        addActor(bluetoothClientButton);

        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("It is test game");
                game.setScreen(game1);
            }
        });

        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("SETTING");
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("EXIT");
                Gdx.app.exit();
                dispose();
            }
        });

//        bluetoothServerButton.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//            }
//        });
        font.dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        buttonUpImage.dispose();
        buttonDownImage.dispose();
        settingsImage.dispose();
        exitImage.dispose();
    }
}
