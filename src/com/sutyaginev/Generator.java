package com.sutyaginev;

import com.sutyaginev.entities.Entity;
import com.sutyaginev.entities.Grass;
import com.sutyaginev.entities.Herbivore;
import com.sutyaginev.entities.Predator;

import java.util.Random;
import java.util.function.Supplier;

public class Generator {

    public void generateStartEntitiesPositions(Board board) {
        int totalCells = board.getHeight() * board.getWidth();

        int targetGrassCount = Math.max(1, totalCells / 20);
        int targetHerbivoreCount = Math.max(1, totalCells / 40);
        int targetPredatorCount = Math.max(1, totalCells / 200);

        generateEntities(board, targetGrassCount, Grass.class, () -> new Grass(generateRandomEmptyCoordinate(board)));
        generateEntities(board, targetHerbivoreCount, Herbivore.class, () -> new Herbivore(generateRandomEmptyCoordinate(board),
                10,
                2));
        generateEntities(board, targetPredatorCount, Predator.class, () -> new Predator(generateRandomEmptyCoordinate(board),
                20,
                1,
                5));

    }

    public <T extends Entity> void generateEntities(Board board, int targetCount, Class<T> entityClass, Supplier<T> supplier) {
        int currentCount = board.getActualEntityCount(entityClass);
        for (int i = currentCount; i < targetCount; i++) {
            Entity entity = supplier.get();
            board.addEntity(entity);
        }
    }

    public Coordinate generateRandomEmptyCoordinate(Board board) {
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
