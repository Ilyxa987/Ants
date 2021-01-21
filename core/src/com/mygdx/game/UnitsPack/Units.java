package com.mygdx.game.UnitsPack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ItemsPack.Items;
import com.mygdx.game.SkillsPack.Skills;

public abstract class Units {
    public String name;
    public int hitpoints;
    public int defence, damage;
    public float x, y;
    public Items items[];
    public Skills skills[];
    public Texture img;

    public Units(String name, float x, float y, Texture img) {
        this.img = img;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Units(String name, float x, float y) {
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getDefence() {
        return defence;
    }

    public int getDamage() {
        return damage;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void Move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void Attack(Units a) {
        int d;
        if (this.damage - a.defence <= 0)
            d = 0;
        else
            d = this.damage - a.defence;
        a.hitpoints -= d;
    }

    public void draw(SpriteBatch batch){}
    public void update(){}
}