package com.mygdx.game.Effects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.UnitsPack.Units;

public class FireEffect extends Actor {
    float x;
    float y;
    Texture img;

    public FireEffect(float x, float y) {
        this.x = x;
        this.y = y;
        setPosition(x, y);
        img = new Texture("fire.png");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(img, x, y);
    }

    public void damage(Units units) {
        if (units.getX() >= x && units.getX() <= x + 32
        && units.getY() >= y && units.getY() <= y + 32) {
            units.hitpoints -= 10;
            units.Damage();
        }
    }
}
