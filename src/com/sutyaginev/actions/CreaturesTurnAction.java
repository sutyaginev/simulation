package com.sutyaginev.actions;

import com.sutyaginev.Board;
import com.sutyaginev.entities.Creature;
import com.sutyaginev.pathfinder.PathFinder;

public class CreaturesTurnAction implements Action {

    private final PathFinder pathFinder;

    public CreaturesTurnAction(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }

    @Override
    public void execute(Board board) {
        for (Creature creature : board.getCreatures()) {
            if (creature != board.getEntity(creature.getCoordinate())) { // Существо могло быть съедено ранее
                continue;
            }

            creature.makeTurn(board, pathFinder);
        }
    }
}
