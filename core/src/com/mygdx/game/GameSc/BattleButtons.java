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

public class BattleButtons extends Stage {
    final MyGame myGame;
    final Menu menu;
    Texture pauseImage, inventorImage;
    TextButton pause, inventor;

    public BattleButtons(final MyGame myGame, final Menu menu) {
        this.myGame = myGame;

        this.menu = menu;

        pauseImage = new Texture("pause.png");
        inventorImage = new Texture(Gdx.files.internal("inventor .png"));

        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();

        skin.add("pause", pauseImage);
        skin.add("inventor", inventorImage);

        TextButton.TextButtonStyle pauseStyle = new TextButton.TextButtonStyle();
        pauseStyle.font = font;
        pauseStyle.up = skin.getDrawable("pause");
        pauseStyle.down = skin.getDrawable("pause");
        pauseStyle.checked = skin.getDrawable("pause");

        TextButton.TextButtonStyle inventorStyle = new TextButton.TextButtonStyle();
        inventorStyle.font = font;
        inventorStyle.up = skin.getDrawable("inventor");
        inventorStyle.down = skin.getDrawable("inventor");
        inventorStyle.checked = skin.getDrawable("inventor");

        pause = new TextButton("", pauseStyle);
        pause.setPosition(1350, 600);
        addActor(pause);

        inventor = new TextButton("", inventorStyle);
        inventor.setPosition(1360, 500);
        addActor(inventor);

        pause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("PAUSE");
                   myGame.setScreen(menu);
            }
        });
    }
}
