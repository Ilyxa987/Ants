package com.mygdx.game.Effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.UnitsPack.Units;

public class FireEffect extends Actor {
    float x;
    float y;
    Texture img;
    TextureRegion[] fireEf;
    TextureRegion currentFrame;
    private static final int FRAME_COLS = 2;
    private static final int FRAME_ROWS = 2;
    Float stateTime;
    Animation gorenia;


    public FireEffect(float x, float y) {
        this.x = x;
        this.y = y;
        setPosition(x, y);
        img = new Texture("met.png");
        TextureRegion[][] tmpR = TextureRegion.split(img, img.getWidth()/FRAME_COLS, img.getHeight()/FRAME_ROWS); // #10
        fireEf = new TextureRegion[FRAME_COLS*FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                fireEf[index++] = tmpR[i][j];
            }
        }
        stateTime = 0f;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();
        gorenia = new Animation(0.2f, fireEf);
        currentFrame = (TextureRegion) gorenia.getKeyFrame(stateTime, true);
        super.draw(batch, parentAlpha);
        batch.draw(currentFrame, x, y);
    }

    public void damage(Units units) {
        if (units.getX() >= x && units.getX() <= x + 32
        && units.getY() >= y && units.getY() <= y + 32) {
            units.hitpoints -= 10;
            units.Damage();
        }
    }
}
