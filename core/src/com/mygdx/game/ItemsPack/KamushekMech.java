package com.mygdx.game.ItemsPack;

import com.badlogic.gdx.graphics.Texture;

public class KamushekMech extends Weapon {
    Texture sword;

    public KamushekMech() {
        sword = new Texture("sword.png");
        damage = 30;
        radios = 30;

    }
}
