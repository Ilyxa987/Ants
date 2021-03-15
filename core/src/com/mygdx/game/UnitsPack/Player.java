package com.mygdx.game.UnitsPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameSc.GameMap;
import com.mygdx.game.GameSc.Games;
import com.mygdx.game.GameSc.HealthBar;
import com.mygdx.game.GameSc.Inventory;
import com.mygdx.game.ItemsPack.Armor;
import com.mygdx.game.ItemsPack.Weapon;


public class Player extends Units {

    Games playerGame;
    Inventory playerInventory;


    public Player(String name, float x, float y, GameMap gameMap) {
        super(name, x, y, gameMap);
        hitpoints = 60;
        healthBar = new HealthBar(hitpoints, this.x, this.y);
        playerGame = gameMap.getGames();
        playerInventory = playerGame.getInventory(); //Подключение инвентаря к игроку
        activeWeapon = (Weapon) playerInventory.getActiveWeapon();
        activeArmor = (Armor) playerInventory.getActiveArmour();
        damage = activeWeapon.damage;
        defence = activeArmor.defence;
        img = new Texture("icon.png");
        actionPoint = 4;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img, x, y);
        healthBar.draw(batch, 1, x, y);
    }

    @Override
    public void update(Units units) {
        if (x + 32 > Gdx.graphics.getWidth()) {
            x = Gdx.graphics.getWidth() - 32;
        }
        if (x < 0) {
            x = 0;
        }
        if (y + 32 > Gdx.graphics.getHeight()) {
            y = Gdx.graphics.getHeight() - 32;
        }
        if (y < 0) {
            y = 0;
        }
    }

    @Override
    public void actionListener() {
        if (actionPoint <= 0) {
            Move = false;
            stepMetr = 0;
            if (gameMap.unitsArray.indexOf(this) + 1 < gameMap.unitsArray.size()) {
                gameMap.activeUnit = gameMap.unitsArray.get(gameMap.unitsArray.indexOf(this) + 1);
                gameMap.activeUnit.actionPoint = 4;
                gameMap.activeUnit.Move = true;
            }
            else {
                gameMap.activeUnit = gameMap.unitsArray.get(0);
                gameMap.activeUnit.actionPoint = 4;
            }
        }
    }

    // Замена активных предметов. Не работает
    public void changeActiveItems() {
        activeWeapon = (Weapon) playerInventory.getActiveWeapon();
        activeArmor = (Armor) playerInventory.getActiveArmour();
    }
}

