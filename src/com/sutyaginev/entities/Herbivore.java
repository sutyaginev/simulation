package com.sutyaginev.entities;

import com.sutyaginev.Coordinate;

import java.util.function.Predicate;

public class Herbivore extends Creature {

    public Herbivore(Coordinate coordinate, int hp, int speed) {
        super(coordinate, hp, speed);
    }

    @Override
    Predicate<Entity> getTargetPredicate() {
        return entity -> entity instanceof Grass;
    }
}
