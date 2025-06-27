package com.sutyaginev.pathfinder;

import com.sutyaginev.Coordinate;
import com.sutyaginev.entities.Entity;

import java.util.List;
import java.util.function.Predicate;

public interface PathFinder {

    List<Coordinate> findPathToNearest(Coordinate start, Predicate<Entity> targetCondition);
}
