package com.mygdx.game.UnitsPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.GameSc.GameMap;
import com.mygdx.game.GameSc.HealthBar;
import com.mygdx.game.ItemsPack.Armor;
import com.mygdx.game.ItemsPack.BronyaIzTravi;
import com.mygdx.game.ItemsPack.KamushekMech;
import com.mygdx.game.ItemsPack.Weapon;


public class Enemy extends Units {
    Player player;


    public Enemy(String name, float x, float y, GameMap gameMap, final Player player) {
        super(name, x, y, gameMap);
        Texture fone = new Texture("Fone.png");

        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();

        skin.add("FONE", fone);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("FONE");
        textButtonStyle.down = skin.getDrawable("FONE");
        textButtonStyle.checked = skin.getDrawable("FONE");

        this.player = player;
        this.setBounds(x, y, 64, 64);
        hitpoints = 40;
        healthBar = new HealthBar(hitpoints, this.x, this.y);
        img = new Texture("enemy.png");
        Move = true;
        activeWeapon = new KamushekMech("", textButtonStyle);
        activeArmor = new BronyaIzTravi(" ", textButtonStyle);
        damage = activeWeapon.damage;
        defence = activeArmor.defence;
        Attack = true;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (alive) {
            batch.draw(img, x, y);
            healthBar.draw(batch, 1, x, y);
        }
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
        if (x + img.getWidth() >= units.getX() && y >= units.getY() - img.getHeight() && y <= units.getY() + units.img.getHeight() && x <= units.getX() + units.img.getWidth()
        && alive == true) {
            units.Stop();
            Stop();
        }
    }

    @Override
    public void attack(Units a) {
        super.attack(a);
        Attack = true;
    }
}
