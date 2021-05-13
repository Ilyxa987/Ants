package com.mygdx.game.ItemsPack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.GameSc.Inventory;

public class WeaponSlot extends TextButton {

    Weapon weaponl;
    Inventory inventory;

    public WeaponSlot(String text, TextButtonStyle style, Weapon weapon, final Inventory inventory) {
        super(text, style);
        Pixmap pixmap = new Pixmap(70, 70, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BROWN);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();

        skin.add("t", texture);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("t");
        textButtonStyle.down = skin.getDrawable("t");
        textButtonStyle.checked = skin.getDrawable("t");

        setStyle(textButtonStyle);

        this.weaponl = weapon;
        this.inventory = inventory;

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Touchsome");
                if (!inventory.getitem && weaponl != null) {
                    inventory.between = weaponl;
                    weaponl = null;
                    inventory.getitem = true;
                }
                else if (weaponl == null && inventory.getitem && Weapon.class.isAssignableFrom(inventory.between.getClass())) {
                    weaponl = (Weapon) inventory.between;
                    inventory.getitem = false;
                }
            }
        });

        font.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (weaponl != null) {
            weaponl.setPosition(getX(), getY());
            weaponl.draw(batch, 1);
        }
    }

    public Weapon getWeaponl() {
        return weaponl;
    }
}
