package com.sutyaginev;

import com.sutyaginev.entities.*;

import java.util.Random;
import java.util.function.Supplier;

public class Generator {

    Board board;
    private final int totalCells;
    private final int treeCount;
    private final int rockCount;
    private final int grassCount;
    private final int herbivoreCount;
    private final int predatorCount;

    public Generator(Board board) {
        this.board = board;
        totalCells = board.getWidth() * board.getHeight();
        treeCount = Math.max(1, totalCells / 20);
        rockCount = Math.max(1, totalCells / 20);
        grassCount = Math.max(1, totalCells / 20);
        herbivoreCount = Math.max(1, totalCells / 40);
        predatorCount = Math.max(1, totalCells / 200);
    }

    public void generateStartEntitiesPositions() {
        generateEntities(board, treeCount, Tree.class, () -> new Tree(generateRandomEmptyCoordinate()));
        generateEntities(board, rockCount, Rock.class, () -> new Rock(generateRandomEmptyCoordinate()));
        generateEntities(board, grassCount, Grass.class, () -> new Grass(generateRandomEmptyCoordinate()));
        generateEntities(board, herbivoreCount, Herbivore.class, () -> new Herbivore(generateRandomEmptyCoordinate(),
                10,
                2));
        generateEntities(board, predatorCount, Predator.class, () -> new Predator(generateRandomEmptyCoordinate(),
                20,
                1,
                5));

    }

    public void regenerateGrass() {
        generateEntities(board, grassCount, Grass.class, () -> new Grass(generateRandomEmptyCoordinate()));
    }

    private  <T extends Entity> void generateEntities(Board board, int targetCount, Class<T> entityClass, Supplier<T> supplier) {
        int currentCount = board.getActualEntityCount(entityClass);
        for (int i = currentCount; i < targetCount; i++) {
            Entity entity = supplier.get();
            board.addEntity(entity);
        }
    }

    private Coordinate generateRandomEmptyCoordinate() {
        Random random = new Random();

        while (true) {
            int x = random.nextInt(board.getWidth());
            int y = random.nextInt(board.getHeight());
            Coordinate coordinate = new Coordinate(x, y);

            if (board.isCellEmpty(coordinate)) {
                return coordinate;
            }
        }
    }
}
