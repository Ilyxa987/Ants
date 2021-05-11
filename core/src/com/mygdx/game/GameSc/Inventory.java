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
import com.mygdx.game.ItemsPack.ArmourSlot;
import com.mygdx.game.ItemsPack.BronyaIzTravi;
import com.mygdx.game.ItemsPack.Items;
import com.mygdx.game.ItemsPack.KamushekMech;
import com.mygdx.game.ItemsPack.Slot;
import com.mygdx.game.ItemsPack.Weapon;
import com.mygdx.game.ItemsPack.WeaponSlot;

import java.util.ArrayList;

import javax.swing.JLayeredPane;


public class Inventory extends Stage {
    public ArrayList<Slot> slotArrayList;
    Items activeWeapon;
    Items activeArmour;
    WeaponSlot weaponSlot;
    ArmourSlot armourSlot;
    public TextButton inventoryHub;
    public boolean getitem;
    public Items between;

    public Inventory() {

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

        activeArmour = new BronyaIzTravi();
        activeWeapon = new KamushekMech();

        slotArrayList = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            slotArrayList.add(new Slot("", textButtonStyle, null, this));
            slotArrayList.get(i).setPosition(inventoryHub.getX() + 50 + 100 * i, inventoryHub.getY() + 50 + 100 * i);
            addActor(slotArrayList.get(i));
    }
        armourSlot = new ArmourSlot("", textButtonStyle, (Armor) activeArmour, this);
        armourSlot.setPosition(inventoryHub.getX() + 450, inventoryHub.getY() + 200);
        addActor(armourSlot);
        activeArmour.setPosition(armourSlot.getX(), armourSlot.getY());
        addActor(activeArmour);

        weaponSlot = new WeaponSlot("", textButtonStyle, (Weapon) activeWeapon, this);
        weaponSlot.setPosition(inventoryHub.getX() + 450, inventoryHub.getY() + 400);
        addActor(weaponSlot);
        activeWeapon.setPosition(weaponSlot.getX(), weaponSlot.getY());
        addActor(activeArmour);
    }

    @Override
    public void draw() {
        super.draw();
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        armourSlot.setPosition(inventoryHub.getX() + 450, inventoryHub.getY() + 200);
        weaponSlot.setPosition(inventoryHub.getX() + 450, inventoryHub.getY() + 400);
        activeArmour.setPosition(armourSlot.getX(), armourSlot.getY());
        activeWeapon.setPosition(weaponSlot.getX(), weaponSlot.getY());
        armourSlot.draw(batch, 1);
        weaponSlot.draw(batch, 1);
        for (int i = 0; i < 16; i++) {
            slotArrayList.get(i).setPosition(inventoryHub.getX() + 50 + 100 * (i % 4), inventoryHub.getY() + 50 + 100 * (i / 4));

        }
        batch.end();
    }

    public ArmourSlot getArmourSlot() {
        return armourSlot;
    }

    public WeaponSlot getWeaponSlot() {
        return weaponSlot;
    }
}