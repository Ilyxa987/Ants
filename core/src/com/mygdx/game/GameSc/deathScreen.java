package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGame;
import com.mygdx.game.menu.KatButton;

public class deathScreen implements Screen {
    final MyGame game;
    SpriteBatch batch = new SpriteBatch();
    OrthographicCamera camera = new OrthographicCamera();
    DeathButtons deathButtons;
    InputMultiplexer inputMultiplexer;
    float stateTime=0;
    Texture ded;

    public deathScreen(final MyGame game) {
        this.game = game;
        this.camera.setToOrtho(false,1560.0F, 720.0F);
        deathButtons = new DeathButtons(game);
        inputMultiplexer = new InputMultiplexer();
        ded = new Texture("ded.png");

    }

    @Override
    public void render(float delta) {
        stateTime+= Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(16384);
        inputMultiplexer.addProcessor(deathButtons);
        Gdx.input.setInputProcessor(inputMultiplexer);
        camera.update();
        batch.begin();
        batch.draw(ded,Gdx.graphics.getWidth()*0.5f-ded.getWidth()*0.5f,Gdx.graphics.getHeight()*0.5f-ded.getHeight()*0.5f);
        batch.end();
        if (stateTime>2){
            deathButtons.draw();
            deathButtons.exit.setDisabled(false);
            deathButtons.tryButton.setDisabled(false);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
deathButtons.dispose();
batch.dispose();
    }
}
