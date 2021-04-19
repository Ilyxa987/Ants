package com.mygdx.game.UnitsPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.GameSc.GameMap;
import com.mygdx.game.GameSc.HealthBar;
import com.mygdx.game.ItemsPack.Armor;
import com.mygdx.game.ItemsPack.BronyaIzTravi;
import com.mygdx.game.ItemsPack.KamushekMech;
import com.mygdx.game.ItemsPack.Weapon;

import java.awt.Rectangle;


public class Enemy extends Units {

    private static final int FRAME_COLS = 2;
    private static final int FRAME_ROWS = 2;
    Player player;
    Texture animll, animrr;
    TextureRegion[] walkL, walkR;
    Animation rWalk, lWalk;
    float stateTime;
    TextureRegion img, currentFrame;
    Texture png;


    public Enemy(String name, float x, float y, GameMap gameMap, final Player player) {
        super(name, x, y, gameMap);

        this.player = player;
        this.setBounds(x, y, 64, 64);
        hitpoints = 40;
        healthBar = new HealthBar(hitpoints, this.x, this.y);
        img = new TextureRegion();
        img = currentFrame;
        Move = true;
        activeWeapon = new KamushekMech();
        activeArmor = new BronyaIzTravi();
        damage = activeWeapon.damage;
        defence = activeArmor.defence;
        Attack = true;
        radios = activeWeapon.radios;
        animll = new Texture("anLeftE.png");
        animrr = new Texture("anRightE.png");
        TextureRegion[][] tmpR = TextureRegion.split(animrr, animrr.getWidth() / 2, animrr.getHeight() / 2); // #10
        walkR = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkR[index++] = tmpR[i][j];
            }
        }
        rWalk = new Animation(0.025f, walkR);
        TextureRegion[][] tmpL = TextureRegion.split(animll, animll.getWidth() / 2, animll.getHeight() / 2); // #10
        walkL = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index1 = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkL[index1++] = tmpL[i][j];
            }
        }
        lWalk = new Animation(0.25f, walkL);
        stateTime = 0f;
        SpriteBatch batch = new SpriteBatch();
    }


    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion) lWalk.getKeyFrame(stateTime, true);
        img = currentFrame;// #16
        if (this.dx>=0){
            lWalk = new Animation(0.3f, walkR);
        }
        if (this.dx<0){
            lWalk = new Animation(0.3f, walkL);
        }
        if (Move==false){
            lWalk = new Animation(0.3f, walkR);
        }
        if (alive) {
            batch.draw(img, x, y);
            healthBar.draw(batch, 1, x, y);
        }


        //batch.draw(currentFrameL, x, y);
    }

    @Override
    public void update(Units units) {
        if (x + 64 > Gdx.graphics.getWidth()) {
            x = Gdx.graphics.getWidth() - 64;
        }
        if (x < 0) {
            x = 0;
        }
        if (y + 64 > Gdx.graphics.getHeight()) {
            y = Gdx.graphics.getHeight() - 64;
        }
        if (y < 0) {
            y = 0;
        }
        /*if (x + img.getRegionWidth() >= units.getX()
                && y >= units.getY() - img.getRegionHeight()
                && y <= units.getY() + units.img.getRegionHeight()
                && x <= units.getX() + units.img.getRegionWidth()
                && alive == true) {
            units.Stop();
            Stop();
        }*/
        if (x + animll.getWidth()/FRAME_COLS >= units.getX()
                && y >= units.getY() - animll.getHeight()/FRAME_ROWS
                && y <= units.getY() + units.animll.getHeight()/FRAME_ROWS
                && x <= units.getX() + units.animll.getWidth()/FRAME_COLS
                && alive == true) {
            units.Stop();
            Stop();
        }
    }

        @Override
        public void attack (Units a){
            super.attack(a);
            Attack = true;
        }


}

