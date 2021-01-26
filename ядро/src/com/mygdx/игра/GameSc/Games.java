package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGame;
import com.mygdx.game.UnitsPack.Player;

public class Games implements Screen, InputProcessor {
    private SpriteBatch batch;
    private Texture pause, inventor;
    Player player;
    OrthographicCamera camera;
    Vector3 touchPos;
    Boolean A = false;


    public Games() {

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 800, 480);
        pause = new Texture(Gdx.files.internal("pause.png"));
        inventor = new Texture(Gdx.files.internal("inventor .png"));
        player = new Player("Player", 0, 0);
        touchPos = new Vector3();
        camera.unproject(touchPos);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(this);

        camera.update();

        batch.begin();
        batch.draw(pause, 1350,600);
        batch.draw(inventor, 1360,500);
        player.draw(batch);
        if (A == true) {
            player.Move(touchPos.x, touchPos.y);
        }
        player.update();
        batch.end();
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        A = true;
        touchPos.set(screenX, screenY, 0);
        return A;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
