package com.sutyaginev.entities;

import com.sutyaginev.Board;
import com.sutyaginev.Coordinate;
import com.sutyaginev.pathfinder.PathFinder;

import java.util.List;
import java.util.function.Predicate;

public abstract class Creature extends Entity {

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

    public void makeTurn(Board board, PathFinder pathFinder) {
        List<Coordinate> pathToTarget = pathFinder.findPathToNearest(getCoordinate(), getTargetPredicate());

        if (pathToTarget == null || pathToTarget.isEmpty()) {
            return;
        }

        for (int i = 0; i < speed; i++) {
            Coordinate nextStep = pathToTarget.get(i);
            Entity entity = board.getEntity(nextStep);

            if (entity == null) {
                move(board, nextStep);
            } else {
                attack(board, nextStep);
                return;
            }
        }
    }

    private void move(Board board, Coordinate nextStep) {
        board.removeEntity(getCoordinate());
        setCoordinate(nextStep);
        board.addEntity(this);
    }

    private void attack(Board board, Coordinate nextStep) {
        move(board, nextStep);
    }

    abstract Predicate<Entity> getTargetPredicate();


}
