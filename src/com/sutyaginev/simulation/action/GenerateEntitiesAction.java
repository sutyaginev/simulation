package com.sutyaginev.simulation.action;

import com.sutyaginev.simulation.utility.Generator;
import com.sutyaginev.simulation.world.WorldMap;

public class GenerateEntitiesAction implements Action {

    private final Generator generator;

    public GenerateEntitiesAction(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void execute(WorldMap worldMap) {
        generator.generateStartEntitiesPositions();
    }
}
