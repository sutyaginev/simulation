package com.sutyaginev;

import com.sutyaginev.entities.Entity;

import java.util.Map;

public class Board {

    private final int height;
    private final int width;
    private final Map<Coordinate, Entity> entities;

    public Board(int height, int width, Map<Coordinate, Entity> entities) {
        this.height = height;
        this.width = width;
        this.entities = entities;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Map<Coordinate, Entity> getEntities() {
        return entities;
    }

    public boolean isInBounds(Coordinate coordinate) {
        Integer x = coordinate.getX();
        Integer y = coordinate.getY();

        return x >= 0 && x < height && y >= 0 && y < width;
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
