package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGame;
import com.mygdx.game.UnitsPack.Player;

public class Games implements Screen {
    private SpriteBatch batch;
    private Texture pause, inventor;
    final MyGame game;
    Player player;
    OrthographicCamera camera;

    public Games(final MyGame mygame) {
        game = mygame;

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        pause = new Texture(Gdx.files.internal("pause.png"));
        inventor = new Texture(Gdx.files.internal("inventor .png"));

        player = new Player("Player", 0, 0);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.begin();
        batch.draw(pause, 1350,600);
        batch.draw(inventor, 1360,500);
        batch.end();

        player.draw(batch);
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
    public void dispose () {
        batch.dispose();
        pause.dispose();
    }
}
