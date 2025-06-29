package com.sutyaginev.actions;

import com.sutyaginev.Board;
import com.sutyaginev.Generator;

public class GenerateEntitiesAction implements Action {

    private final Generator generator;

    public GenerateEntitiesAction(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void execute(Board board) {
        generator.generateStartEntitiesPositions();
    }
}
