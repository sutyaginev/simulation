package com.sutyaginev.actions;

import com.sutyaginev.world.WorldMap;
import com.sutyaginev.entities.Creature;
import com.sutyaginev.pathfinder.PathFinder;

public class CreaturesTurnAction implements Action {

    private final PathFinder pathFinder;

    public CreaturesTurnAction(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }

    @Override
    public void execute(WorldMap worldMap) {
        for (Creature creature : worldMap.getCreatures()) {
            if (creature != worldMap.getEntity(creature.getCoordinate())) { // Существо могло быть съедено ранее
                continue;
            }

            creature.makeTurn(worldMap, pathFinder);
        }
    }
}
