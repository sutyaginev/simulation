package com.sutyaginev.actions;

import com.sutyaginev.world.WorldMap;
import com.sutyaginev.utility.Generator;

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
