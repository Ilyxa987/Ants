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
    public TextButton inventoryHub;
    Texture fone;



    public Inventory(){

        Texture inventoryTexture = new Texture("inventary.png");

        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();

        skin.add("inventory", inventoryTexture);

        TextButton.TextButtonStyle inventoryStyle = new TextButton.TextButtonStyle();
        inventoryStyle.font = font;
        inventoryStyle.up = skin.getDrawable("inventory");
        inventoryStyle.down = skin.getDrawable("inventory");
        inventoryStyle.checked = skin.getDrawable("inventory");

        inventoryHub = new TextButton("", inventoryStyle);
        inventoryHub.setPosition(-1000, -1000);
        addActor(inventoryHub);

        itemsArr = new Items[4][4];
        for (int i = 0; i < itemsArr.length; i++) {
            for (int j = 0; j < itemsArr[i].length; j++) {
                itemsArr[i][j] = null;
            }
        }
        fone = new Texture("Fone.png");
        activeWeapon = new KamushekMech();
        activeArmour = new BronyaIzTravi();

        activeArmour.setPosition(inventoryHub.getX() + 450, inventoryHub.getY() / 2);
        addActor(activeWeapon);
        activeWeapon.setPosition(inventoryHub.getX() + 450, inventoryHub.getY() / 4);
        addActor(activeArmour);
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
                if (itemsArr[i][j] == null)
                    batch.draw(fone, inventoryHub.getX() + 50 + 100 * i, inventoryHub.getY() + 50 + 100 * j);
            }
        }
        batch.end();
        System.out.println("POSITION INVENTORY " + inventoryHub.getX());
    }
}