package com.mygdx.game.ItemsPack;

public class Weapon extends Items{
    public int damage;
    public int radios;

    public Weapon(String text, TextButtonStyle style) {
        super(text, style);
    }

    public Weapon(String text, TextButtonStyle style, int damage, int radios) {
        super(text, style);
        this.damage = damage;
        this.radios = radios;
    }
}
