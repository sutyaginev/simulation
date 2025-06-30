package com.sutyaginev.simulation.pathfinder;

import com.sutyaginev.simulation.world.Coordinate;
import com.sutyaginev.simulation.entity.Entity;

import java.util.List;
import java.util.function.Predicate;

public interface PathFinder {

    List<Coordinate> findPathToNearest(Coordinate start, Predicate<Entity> targetCondition);
}
