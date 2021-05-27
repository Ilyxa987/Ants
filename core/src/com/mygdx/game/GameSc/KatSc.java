package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.MyGame;
import com.mygdx.game.menu.KatButton;

public class KatSc implements Screen {
    final MyGame game;
    private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 8;
    TextureRegion[] kat;
    public Animation katSc;
    float stateTime;
    TextureRegion img;
    Texture katTexture;
    OrthographicCamera camera = new OrthographicCamera();
    KatButton button;
    SpriteBatch batch;
    InputMultiplexer inputMultiplexer;

 public KatSc(final MyGame game){
     this.game = game;
     camera.setToOrtho(false,800, 480);
     katTexture = new Texture("kat800.png");
     TextureRegion[][] tmpR = TextureRegion.split(katTexture, katTexture.getWidth()/FRAME_COLS, katTexture.getHeight()/FRAME_ROWS); // #10
     kat = new TextureRegion[FRAME_COLS*FRAME_ROWS];
     int index = 0;
     for (int i = 0; i < FRAME_ROWS; i++) {
         for (int j = 0; j < FRAME_COLS; j++) {
             kat[index++] = tmpR[i][j];
         }
     }
     katSc = new Animation(0.2f, kat);
     button = new KatButton(this.game);
     batch = new SpriteBatch();
     inputMultiplexer = new InputMultiplexer();
 }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(16384);
        GL20 gl = Gdx.graphics.getGL20();
        gl.glViewport(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        stateTime += Gdx.graphics.getDeltaTime();
        img = (TextureRegion) katSc.getKeyFrame(stateTime);
        batch.draw(img, 0, 0);
        inputMultiplexer.addProcessor(button);
        Gdx.input.setInputProcessor(inputMultiplexer);
        batch.end();
        if (katSc.getKeyFrameIndex(stateTime)==30){
            game.setScreen(new Games(game));
        }
        if (stateTime>=2)button.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {    }

    @Override
    public void resume() {    }

    @Override
    public void show() {    }

    @Override
    public void hide() {    }

    @Override
    public void dispose() {
button.dispose();
batch.dispose();

    }
}
