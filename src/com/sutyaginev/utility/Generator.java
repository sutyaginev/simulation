package com.sutyaginev.utility;

import com.sutyaginev.entities.*;
import com.sutyaginev.world.Coordinate;
import com.sutyaginev.world.WorldMap;

import java.util.Random;
import java.util.function.Supplier;

public class Generator {

    WorldMap worldMap;
    private final int totalCells;
    private final int treeCount;
    private final int rockCount;
    private final int grassCount;
    private final int herbivoreCount;
    private final int predatorCount;

    public Generator(WorldMap worldMap) {
        this.worldMap = worldMap;
        totalCells = worldMap.getWidth() * worldMap.getHeight();
        treeCount = Math.max(1, totalCells / 20);
        rockCount = Math.max(1, totalCells / 20);
        grassCount = Math.max(1, totalCells / 20);
        herbivoreCount = Math.max(1, totalCells / 40);
        predatorCount = Math.max(1, totalCells / 200);
    }

    public void generateStartEntitiesPositions() {
        generateEntities(worldMap, treeCount, Tree.class, () -> new Tree(generateRandomEmptyCoordinate()));
        generateEntities(worldMap, rockCount, Rock.class, () -> new Rock(generateRandomEmptyCoordinate()));
        generateEntities(worldMap, grassCount, Grass.class, () -> new Grass(generateRandomEmptyCoordinate()));
        generateEntities(worldMap, herbivoreCount, Herbivore.class, () -> new Herbivore(generateRandomEmptyCoordinate(),
                10,
                2));
        generateEntities(worldMap, predatorCount, Predator.class, () -> new Predator(generateRandomEmptyCoordinate(),
                20,
                1,
                5));

    }

    public void regenerateGrass() {
        generateEntities(worldMap, grassCount, Grass.class, () -> new Grass(generateRandomEmptyCoordinate()));
    }

    private <T extends Entity> void generateEntities(WorldMap worldMap, int targetCount, Class<T> entityClass, Supplier<T> supplier) {
        int currentCount = worldMap.getActualEntityCount(entityClass);
        for (int i = currentCount; i < targetCount; i++) {
            Entity entity = supplier.get();
            worldMap.addEntity(entity);
        }
    }

    private Coordinate generateRandomEmptyCoordinate() {
        Random random = new Random();

        while (true) {
            int x = random.nextInt(worldMap.getWidth());
            int y = random.nextInt(worldMap.getHeight());
            Coordinate coordinate = new Coordinate(x, y);

            if (worldMap.isCellEmpty(coordinate)) {
                return coordinate;
            }
        }
    }
}
