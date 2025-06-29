package com.sutyaginev.actions;

import com.sutyaginev.world.WorldMap;
import com.sutyaginev.utility.Generator;

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
