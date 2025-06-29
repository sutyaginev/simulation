package com.sutyaginev.entities;

import com.sutyaginev.world.WorldMap;
import com.sutyaginev.world.Coordinate;
import com.sutyaginev.pathfinder.PathFinder;

import java.util.List;
import java.util.function.Predicate;

public abstract class Creature extends Entity {

    private int hp;
    private final int maxHp;
    private final int speed;

    public Creature(Coordinate coordinate, int hp, int speed) {
        super(coordinate);
        this.hp = hp;
        maxHp = hp;
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.min(hp, maxHp);
    }

    public void makeTurn(WorldMap worldMap, PathFinder pathFinder) {
        List<Coordinate> pathToTarget = pathFinder.findPathToNearest(getCoordinate(), getTargetPredicate());

        if (pathToTarget == null || pathToTarget.isEmpty()) {
            return;
        }

        for (int i = 0; i < speed; i++) {
            Coordinate nextStep = pathToTarget.get(i);
            Entity entity = worldMap.getEntity(nextStep);

            if (entity == null) {
                move(worldMap, nextStep);
            } else {
                attack(worldMap, nextStep);
                return;
            }
        }
    }

    protected void move(WorldMap worldMap, Coordinate nextStep) {
        worldMap.removeEntity(getCoordinate());
        setCoordinate(nextStep);
        worldMap.addEntity(this);
    }

    protected abstract void attack(WorldMap worldMap, Coordinate nextStep);

    protected abstract Predicate<Entity> getTargetPredicate();

    protected boolean isAlive() {
        return hp > 0;
    }
}
