package com.mygdx.game.ItemsPack;



public class Armor extends Items {

    public int defence;

    public Armor(String text, TextButtonStyle style) {
        super(text, style);
    }

    public Armor(String text, TextButtonStyle style, int defence) {
        super(text, style);
        this.defence = defence;
    }
}
