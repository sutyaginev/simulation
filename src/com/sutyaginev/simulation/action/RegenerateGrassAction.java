package com.sutyaginev.simulation.action;

import com.sutyaginev.simulation.utility.Generator;
import com.sutyaginev.simulation.world.WorldMap;

public class RegenerateGrassAction implements Action {

    private final Generator generator;

    public RegenerateGrassAction(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void execute(WorldMap worldMap) {
        generator.regenerateGrass();
    }
}
