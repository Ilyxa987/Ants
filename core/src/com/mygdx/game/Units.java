package com.mygdx.game;

public class Units {
    public String name;
    public int hitpoints;
    int defence, damage;
    float x, y;

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
}
