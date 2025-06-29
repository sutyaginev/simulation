package com.sutyaginev.entities;

import com.sutyaginev.Board;
import com.sutyaginev.Coordinate;

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
    protected void attack(Board board, Coordinate nextStep) {
        setHp(getHp() + 1);
        move(board, nextStep);
    }
}
