package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.MyGame;
import com.mygdx.game.menu.Menu;

public class BattleButtons extends Stage {
    final MyGame myGame;
    final Menu menu;
    Texture pauseImage, inventorImage, skillHubImage;
    TextButton pause, inventor, skillHub;

    public BattleButtons(final MyGame myGame, final Menu menu) {
        this.myGame = myGame;

        this.menu = menu;

        pauseImage = new Texture("pause.png");
        inventorImage = new Texture(Gdx.files.internal("inventor .png"));
        skillHubImage = new Texture("skillbar.png");

        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();

        skin.add("pause", pauseImage);
        skin.add("inventor", inventorImage);
        skin.add("skillhub", skillHubImage);

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

        TextButton.TextButtonStyle skillHubStyle = new TextButton.TextButtonStyle();
        skillHubStyle.font = font;
        skillHubStyle.up = skin.getDrawable("skillhub");
        skillHubStyle.down = skin.getDrawable("skillhub");
        skillHubStyle.checked = skin.getDrawable("skillhub");

        pause = new TextButton("", pauseStyle);
        pause.setPosition(1350, 600);
        addActor(pause);

        inventor = new TextButton("", inventorStyle);
        inventor.setPosition(1360, 500);
        addActor(inventor);

        skillHub = new TextButton("", skillHubStyle);
        skillHub.setPosition(Gdx.graphics.getWidth() / 6, -17);
        addActor(skillHub);

        pause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("PAUSE");
                   myGame.setScreen(menu);
            }
        });
    }
}
