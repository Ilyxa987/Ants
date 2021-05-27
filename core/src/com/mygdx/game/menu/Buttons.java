package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.GdxBuild;
import com.mygdx.game.GameSc.Games;
import com.mygdx.game.GameSc.KatSc;
import com.mygdx.game.MyGame;


public class Buttons extends Stage {
    final MyGame game;
    Texture buttonUpImage, buttonDownImage, settingsImage, exitImage, disign, skipImg;
    public TextButton button, settings, exit, disignName, skip;
    public KatSc game1;
    public boolean katTF = true;

    public Buttons(final MyGame mygame) {
        game = mygame;

        game1 = new KatSc(game);

        Pixmap pixmap = new Pixmap(300, 100, Pixmap.Format.Alpha);
        pixmap.setColor(0, 0, 0, 0);
        pixmap.fill();

        buttonUpImage = new Texture("play.png");
        buttonDownImage = new Texture("play.png");
        settingsImage = new Texture("settings.png");
        exitImage = new Texture("exit.png");
        disign = new Texture(pixmap);

        pixmap.dispose();

        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();

        skin.add("play", buttonUpImage);
        skin.add("play", buttonDownImage);
        skin.add("settings", settingsImage);
        skin.add("exit", exitImage);
        skin.add("disign", disign);

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

        TextButton.TextButtonStyle disignStyle = new TextButton.TextButtonStyle();
        disignStyle.font = font;
        disignStyle.up = skin.getDrawable("disign");
        disignStyle.down = skin.getDrawable("disign");
        disignStyle.checked = skin.getDrawable("disign");


        button = new TextButton("", textButtonStyle);
        button.setPosition(Gdx.graphics.getWidth() / 2 - buttonUpImage.getWidth() / 2, Gdx.graphics.getHeight() / 2 + settingsImage.getHeight() / 2);
        addActor(button);

        settings = new TextButton("", settingsStyle);
        settings.setPosition(Gdx.graphics.getWidth() / 2 - settingsImage.getWidth() / 2, Gdx.graphics.getHeight() / 2 - settingsImage.getHeight() / 2);
        addActor(settings);

        exit = new TextButton("", exitStyle);
        exit.setPosition(Gdx.graphics.getWidth() / 2 - exitImage.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 2 - settingsImage.getHeight() - exitImage.getHeight() / 2);
        addActor(exit);

        disignName = new TextButton("Designer: Alexey Popov", disignStyle);
        disignName.setPosition(Gdx.graphics.getWidth()*0.1f, Gdx.graphics.getHeight()*0.1f);
        addActor(disignName);
        disignName.getLabel().setFontScale(5);

        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("It is test game");
                if (katTF){
                    game.setScreen(new KatSc(game));
                }else
                    {
                        game.setScreen(new Games(game));
                    }

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
        font.dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        buttonUpImage.dispose();
        buttonDownImage.dispose();
        settingsImage.dispose();
        exitImage.dispose();
        disign.dispose();
    }
}