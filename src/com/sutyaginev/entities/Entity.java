package com.sutyaginev.entities;

import com.sutyaginev.world.Coordinate;

public class Entity {

    private Coordinate coordinate;

    public Entity(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
    public Coordinate getCoordinate() {
        return coordinate;
    }
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
