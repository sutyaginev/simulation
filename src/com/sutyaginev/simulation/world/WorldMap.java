package com.sutyaginev.simulation.world;

import com.sutyaginev.simulation.entity.Creature;
import com.sutyaginev.simulation.entity.Entity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WorldMap {

    private final int width;
    private final int height;
    private final Map<Coordinate, Entity> entities;

    public WorldMap(int width, int height, Map<Coordinate, Entity> entities) {
        this.width = width;
        this.height = height;
        this.entities = entities;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Map<Coordinate, Entity> getEntities() {
        return entities;
    }

    public List<Creature> getCreatures() {
        return entities.values().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity)
                .collect(Collectors.toList());
    }

    public boolean isInBounds(Coordinate coordinate) {
        Integer x = coordinate.getX();
        Integer y = coordinate.getY();

        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public boolean isCellEmpty(Coordinate coordinate) {
        return !entities.containsKey(coordinate);
    }

    public Entity getEntity(Coordinate coordinate) {
        return entities.get(coordinate);
    }

    public void addEntity(Entity entity) {
        entities.put(entity.getCoordinate(), entity);
    }

    public void removeEntity(Coordinate coordinate) {
        entities.remove(coordinate);
    }

    public <T extends Entity> int getActualEntityCount(Class<T> entityClass) {
        return (int) entities.values().stream()
                .filter(entityClass::isInstance)
                .count();
    }
}
