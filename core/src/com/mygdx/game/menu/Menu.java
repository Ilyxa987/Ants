package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGame;

public class Menu implements Screen {

    final MyGame game;
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture bgImage;
    Buttons buttons;
    BitmapFont font1;

    public Menu(final MyGame mygame) {
        game = mygame;

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        createBackground();
        buttons = new Buttons(game, this);

        font1 = new BitmapFont();
        font1.setColor(255,255,255, 1);
    }

    private void createBackground() {
        bgImage = new Texture("bg.png");
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        Gdx.input.setInputProcessor(buttons);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bgImage, 0, 0);
        font1.draw(batch, "Designer: Alexey Popov", 15, 20);
        batch.end();

        buttons.draw();
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
        batch.dispose();
        bgImage.dispose();
        buttons.dispose();
    }
}
