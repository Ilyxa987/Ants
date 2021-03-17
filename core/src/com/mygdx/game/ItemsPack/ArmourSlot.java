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

import java.lang.annotation.Annotation;

public class ArmourSlot extends TextButton {

    Armor armorl;
    Inventory inventory;

    public ArmourSlot(String text, TextButtonStyle style, final Armor armor, final Inventory inventory) {
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

        this.armorl = armor;
        this.inventory = inventory;

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Touchsome");
                if (!inventory.getitem && armorl != null) {
                    inventory.between = armorl;
                    armorl = null;
                    inventory.getitem = true;
                }
                else if (armorl == null && inventory.getitem && Armor.class.isAssignableFrom(inventory.between.getClass())) {
                    armorl = (Armor) inventory.between;
                    inventory.getitem = false;
                }
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (armorl != null) {
            armorl.setPosition(getX(), getY());
            armorl.draw(batch, 1);
        }
    }

    public Armor getArmorl() {
        return armorl;
    }
}
