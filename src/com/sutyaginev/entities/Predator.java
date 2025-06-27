package com.sutyaginev.entities;

import com.sutyaginev.Coordinate;

import java.util.function.Predicate;

public class Predator extends Creature {

    private final int attack;

    public Predator(Coordinate coordinate, int hp, int speed, int attack) {
        super(coordinate, hp, speed);
        this.attack = attack;
    }

    @Override
    Predicate<Entity> getTargetPredicate() {
        return entity -> entity instanceof Herbivore;
    }
}
