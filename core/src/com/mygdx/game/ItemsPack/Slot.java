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

public class Slot extends TextButton {

    Items iteml;
    Inventory inventory;

    public Slot(String text, TextButtonStyle style, final Items item, final Inventory inventory) {
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

        this.iteml = item;
        this.inventory = inventory;

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Touchsome");
                if (!inventory.getitem && iteml != null) {
                    inventory.between = iteml;
                    iteml = null;
                    inventory.getitem = true;
                }
                else if (iteml == null && inventory.getitem) {
                    iteml = inventory.between;
                    inventory.getitem = false;
                }
            }
        });

        font.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (iteml != null) {
            iteml.setPosition(getX(), getY());
            iteml.draw(batch, 1);
        }
    }

    public Items getIteml() {
        return iteml;
    }

    public void setIteml(Items iteml) {
        this.iteml = iteml;
    }
}
