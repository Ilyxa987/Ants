package com.mygdx.game.UnitsPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameSc.GameMap;
import com.mygdx.game.GameSc.Games;
import com.mygdx.game.GameSc.HealthBar;
import com.mygdx.game.GameSc.Inventory;
import com.mygdx.game.ItemsPack.Armor;
import com.mygdx.game.ItemsPack.Items;
import com.mygdx.game.ItemsPack.Weapon;



public class Player extends Units {

    private static final int FRAME_COLS = 2;
    private static final int FRAME_ROWS = 2;
    Games playerGame;
    Inventory playerInventory;
    Texture animll, animrr;
    TextureRegion[] walkL,walkR;
    Animation rWalk, lWalk;
    float stateTime;
    TextureRegion img, currentFrame;
    Texture png;

    public Player(String name, float x, float y, GameMap gameMap) {
        super(name, x, y, gameMap);
        hitpoints = 60;
        healthBar = new HealthBar(hitpoints, this.x, this.y);
        playerGame = gameMap.getGames();
        playerInventory = playerGame.getInventory(); //Подключение инвентаря к игроку
        activeWeapon = (Weapon) playerInventory.getWeaponSlot().getWeaponl();
        activeArmor = (Armor) playerInventory.getArmourSlot().getArmorl();
        damage = activeWeapon.damage;
        defence = activeArmor.defence;
        img = new TextureRegion();
        img = currentFrame;
        actionPoint = 4;
        radios = activeWeapon.radios;
        animll = new Texture("Left2.png");
        animrr = new Texture("Right2.png");
        TextureRegion[][] tmpR = TextureRegion.split(animrr, animrr.getWidth()/2, animrr.getHeight()/2); // #10
        walkR = new TextureRegion[FRAME_COLS*FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkR[index++] = tmpR[i][j];
            }
        }
        rWalk = new Animation(0.1f, walkR);
        TextureRegion[][] tmpL = TextureRegion.split(animll, animll.getWidth()/2, animll.getHeight()/2); // #10
        walkL = new TextureRegion[FRAME_COLS*FRAME_ROWS];
        int index1 = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkL[index1++] = tmpL[i][j];
            }
        }
        lWalk = new Animation(0.25f, walkL);
        stateTime = 0f;
        rectangle = new Rectangle(x, y, 64, 98);
    }


            @Override
            public void draw (SpriteBatch batch){
                //batch.draw(img, x, y);
                //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
                img = (TextureRegion) rWalk.getKeyFrame(stateTime, true); // #16
                batch.draw(img, x, y); // #17
                healthBar.draw(batch, 1, x, y);
            }

            @Override
            public void update (Units units){
                stateTime += Gdx.graphics.getDeltaTime(); // #15
                if (this.dx>=0){
                    rWalk = new Animation(0.3f, walkR);
                }
                if (this.dx<0){
                    rWalk = new Animation(0.3f, walkL);
                }
                if (Move==false){
                    rWalk = new Animation(0.3f, walkR);
                }
                currentFrame = (TextureRegion) rWalk.getKeyFrame(stateTime, true);
                super.update(units);
            }


            @Override
            public void actionListener () {
                if (actionPoint <= 0) {
                    Move = false;
                    stepMetr = 0;
                    if (gameMap.unitsArray.indexOf(this) + 1 < gameMap.unitsArray.size()) {
                        gameMap.activeUnit = gameMap.unitsArray.get(gameMap.unitsArray.indexOf(this) + 1);
                        gameMap.activeUnit.actionPoint = 4;
                        gameMap.activeUnit.Move = true;
                    } else {
                        gameMap.activeUnit = gameMap.unitsArray.get(0);
                        gameMap.activeUnit.actionPoint = 4;
                    }
                }
            }


            // Замена активных предметов. Не работает
            public void changeActiveItems () {
                activeWeapon = (Weapon) playerInventory.getWeaponSlot().getWeaponl();
                activeArmor = (Armor) playerInventory.getArmourSlot().getArmorl();
                if (activeWeapon == null) {
                    damage = 1;
                    radios = 1;
                }
                if (activeArmor == null) {
                    defence = 0;
                }
            }

            public void takeItem(Items items) {
                for (int i = 0; i < playerInventory.slotArrayList.size(); i++) {
                    if (playerInventory.slotArrayList.get(i).getIteml() == null) {
                        playerInventory.slotArrayList.get(i).setIteml(items);
                        break;
                    }
                }
            }
        }
