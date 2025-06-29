package com.sutyaginev.entities;

import com.sutyaginev.world.WorldMap;
import com.sutyaginev.world.Coordinate;

import java.util.function.Predicate;

public class Predator extends Creature {

    private final int attack;

    public Predator(Coordinate coordinate, int hp, int speed, int attack) {
        super(coordinate, hp, speed);
        this.attack = attack;
    }

    @Override
    protected Predicate<Entity> getTargetPredicate() {
        return entity -> entity instanceof Herbivore;
    }

    @Override
    protected void attack(WorldMap worldMap, Coordinate nextStep) {
        Entity entity = worldMap.getEntity(nextStep);

        if (!(entity instanceof Creature target)) {
            return;
        }

        target.setHp(target.getHp() - attack);

        if (!target.isAlive()) {
            move(worldMap, nextStep);
        }
    }
}
