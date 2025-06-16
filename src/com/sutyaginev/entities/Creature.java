package com.sutyaginev.entities;

import com.sutyaginev.Coordinate;

public class Creature extends Entity {

    private final int hp;
    private final int speed;

    public Creature(Coordinate coordinate, int hp, int speed) {
        super(coordinate);
        this.hp = hp;
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }
}
