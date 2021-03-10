package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.ItemsPack.Armor;
import com.mygdx.game.ItemsPack.BronyaIzTravi;
import com.mygdx.game.ItemsPack.Items;
import com.mygdx.game.ItemsPack.KamushekMech;
import com.mygdx.game.ItemsPack.Weapon;

public class Inventory extends Stage {
    Items[][] itemsArr;
    Weapon activeWeapon;
    Armor activeArmour;



    public Inventory(){

        itemsArr = new Items[4][4];
        activeWeapon = new KamushekMech();
        activeArmour = new BronyaIzTravi();

        activeArmour.setPosition(Gdx.graphics.getWidth()+10, Gdx.graphics.getHeight()/2);
        addActor(activeWeapon);
        activeWeapon.setPosition(Gdx.graphics.getWidth()+10, Gdx.graphics.getHeight()/4);
        addActor(activeArmour);
        /*skillHubImage = new Texture("enemy.png");
        at = new Texture("Attack.png");

        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();

        skin.add("skillHub", skillHubImage);
        skin.add("Attack", at);

        TextButton.TextButtonStyle skillHubStyle = new TextButton.TextButtonStyle();
        skillHubStyle.font = font;
        skillHubStyle.up = skin.getDrawable("skillHub");
        skillHubStyle.down = skin.getDrawable("skillHub");
        skillHubStyle.checked = skin.getDrawable("skillHub");

        TextButton.TextButtonStyle atStyle = new TextButton.TextButtonStyle();
        atStyle.font = font;
        atStyle.up = skin.getDrawable("Attack");
        atStyle.down = skin.getDrawable("Attack");
        atStyle.checked = skin.getDrawable("Attack");

        armour = new TextButton("", skillHubStyle);
        armour.setPosition(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        addActor(armour);

        weapon = new TextButton("", atStyle);
        weapon.setPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-50);
        addActor(weapon);*/


    }

    @Override
    public void draw() {
        super.draw();
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        for (int i = 0; i <itemsArr.length ; i++) {
            for (int j = 0; j <itemsArr[i].length ; j++) {

            }
        }

        batch.end();
    }
}