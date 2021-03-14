package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.ItemsPack.Armor;
import com.mygdx.game.ItemsPack.BronyaIzTravi;
import com.mygdx.game.ItemsPack.Items;
import com.mygdx.game.ItemsPack.KamushekMech;
import com.mygdx.game.ItemsPack.Weapon;


public class Inventory extends Stage {
    Items[][] itemsArr;
    Items activeWeapon;
    Items activeArmour;
    public TextButton inventoryHub;
    boolean getitem;
    Items between, zaglushka, zagWeapon, zagArmour;


    public Items getActiveWeapon() {
        return activeWeapon;
    }

    public Items getActiveArmour() {
        return activeArmour;
    }

    public Inventory(){

        Texture inventoryTexture = new Texture("inventary.png");
        Texture fone = new Texture("Fone.png");

        getitem = false;


        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();

        skin.add("inventory", inventoryTexture);
        skin.add("FONE", fone);

        TextButton.TextButtonStyle inventoryStyle = new TextButton.TextButtonStyle();
        inventoryStyle.font = font;
        inventoryStyle.up = skin.getDrawable("inventory");
        inventoryStyle.down = skin.getDrawable("inventory");
        inventoryStyle.checked = skin.getDrawable("inventory");

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("FONE");
        textButtonStyle.down = skin.getDrawable("FONE");
        textButtonStyle.checked = skin.getDrawable("FONE");

        inventoryHub = new TextButton("", inventoryStyle);
        inventoryHub.setPosition(-1000, -1000);
        addActor(inventoryHub);

        zaglushka = new Items("", textButtonStyle);
        zagWeapon = new Weapon("", textButtonStyle, 0, 0);
        zagArmour = new Armor("", textButtonStyle, 0);
        activeArmour = new BronyaIzTravi("", textButtonStyle);
        activeWeapon = new KamushekMech("", textButtonStyle);

        itemsArr = new Items[4][4];
        for (int i = 0; i < itemsArr.length; i++) {
            for (int j = 0; j < itemsArr[i].length; j++) {
                itemsArr[i][j] = zaglushka;
            }
            //TODO нужно повесить лисенер на каждый элемент массива
        }




        activeArmour.setPosition(inventoryHub.getX() + 450, inventoryHub.getY() / 2);
        addActor(activeWeapon);
        activeArmour.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("TOUCHARMOUR");
                if (!getitem && activeArmour != zagArmour) {
                    between = activeArmour;
                    activeArmour = zagArmour;
                    getitem = true;
                }
                else if (activeArmour == zagArmour){
                    activeArmour = between;
                    getitem = false;
                }
            }
        });

        activeWeapon.setPosition(inventoryHub.getX() + 450, inventoryHub.getY() / 4);
        addActor(activeArmour);
        activeWeapon.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!getitem && activeWeapon != zagWeapon) {
                    between = activeWeapon;
                    activeWeapon = zagWeapon;
                    getitem = true;
                }
                else if (activeWeapon == zagWeapon) {
                    activeWeapon = between;
                    getitem = false;
                }
            }
        });
    }

    @Override
    public void draw() {
        super.draw();
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        activeArmour.setPosition(inventoryHub.getX() + 450, inventoryHub.getY() + 200);
        activeWeapon.setPosition(inventoryHub.getX() + 450, inventoryHub.getY() + 400);
        activeWeapon.draw(batch, 1);
        activeArmour.draw(batch, 1);
        for (int i = 0; i <itemsArr.length ; i++) {
            for (int j = 0; j <itemsArr[i].length ; j++) {
                itemsArr[i][j].setPosition(inventoryHub.getX() + 50 + 100 * i, inventoryHub.getY() + 50 + 100 * j);
                itemsArr[i][j].draw(batch, 1);
            }
        }
        batch.end();
    }
}