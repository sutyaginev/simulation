package com.sutyaginev.simulation.entity;

import com.sutyaginev.simulation.world.Coordinate;
import com.sutyaginev.simulation.world.WorldMap;

import java.util.function.Predicate;

public class Herbivore extends Creature {

    public Herbivore(Coordinate coordinate, int hp, int speed) {
        super(coordinate, hp, speed);
    }

    @Override
    protected Predicate<Entity> getTargetPredicate() {
        return entity -> entity instanceof Grass;
    }

    @Override
    protected void attack(WorldMap worldMap, Coordinate nextStep) {
        setHp(getHp() + 1);
        move(worldMap, nextStep);
    }
}
