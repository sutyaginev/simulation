package com.sutyaginev.actions;

import com.sutyaginev.Board;
import com.sutyaginev.Generator;

public class RegenerateGrassAction implements Action {

    private final Generator generator;

    public RegenerateGrassAction(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void execute(Board board) {
        generator.regenerateGrass();
    }
}
