package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Games implements Screen {
    private SpriteBatch batch = new SpriteBatch();
    private Texture pause;
    private Texture inventor;
    OrthographicCamera camera = new OrthographicCamera();
    GameMap gameMap;

    public Games() {
        this.camera.setToOrtho(false, 800.0F, 480.0F);
        this.pause = new Texture(Gdx.files.internal("pause.png"));
        this.inventor = new Texture(Gdx.files.internal("inventor .png"));
        this.gameMap = new GameMap(this);
    }

    public void show() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(16384);
        Gdx.input.setInputProcessor(this.gameMap);
        this.camera.update();
        this.gameMap.draw();
        this.batch.begin();
        this.batch.draw(this.pause, 1350.0F, 600.0F);
        this.batch.draw(this.inventor, 1360.0F, 500.0F);
        this.batch.end();
    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
    }

    public void dispose() {
        this.batch.dispose();
        this.pause.dispose();
    }
}