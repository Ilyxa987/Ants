package com.mygdx.game.GameSc;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class HealthBar extends ProgressBar {

    public HealthBar(float max, float x, float y) {
        super(0, max, 1, false, new ProgressBarStyle());
        Pixmap pixmap = new Pixmap(64, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();
        getStyle().background = drawable;

        pixmap = new Pixmap(64, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        TextureRegionDrawable drawable1 = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();
        getStyle().knobBefore = drawable1;

        pixmap = new Pixmap(0, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        TextureRegionDrawable drawable2 = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();
        getStyle().knob = drawable2;

        setWidth(64);
        setHeight(20);

        setAnimateDuration(0);
        setValue(max);


        setPosition(x, y);
    }

    public void draw(Batch batch, float parentAlpha, float x, float y) {
        setPosition(x, y);
        super.draw(batch, parentAlpha);
    }
}
