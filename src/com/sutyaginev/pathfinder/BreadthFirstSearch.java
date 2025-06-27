package com.sutyaginev.pathfinder;

import com.sutyaginev.Board;
import com.sutyaginev.Coordinate;
import com.sutyaginev.entities.Entity;

import java.util.*;
import java.util.function.Predicate;

public class BreadthFirstSearch implements PathFinder {

    private final Board board;

    public BreadthFirstSearch(Board board) {
        this.board = board;
    }

    @Override
    public List<Coordinate> findPathToNearest(Coordinate start, Predicate<Entity> targetCondition) {
        Queue<Coordinate> queue = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();
        Map<Coordinate, Coordinate> parents = new HashMap<>();

        // вправо, вверх, влево, вниз
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        queue.add(start);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            visited.add(current);

            for (int[] direction : directions) {
                Coordinate neighbour = new Coordinate(current.getX() + direction[0], current.getY() + direction[1]);

                if (!board.isInBounds(neighbour)) {
                    continue;
                }

                Entity entity = board.getEntity(neighbour);

                if (entity != null && targetCondition.test(entity)) {
                    parents.put(neighbour, current);
                    return getPathToTarget(parents, start, neighbour);
                }

                if (!isVisited(visited, neighbour) && board.isCellEmpty(neighbour)) {
                    parents.put(neighbour, current);
                    queue.add(neighbour);
                }
            }
        }

        return Collections.emptyList();
    }

    private List<Coordinate> getPathToTarget(Map<Coordinate, Coordinate> parents, Coordinate start, Coordinate target) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate current = target;

        while (current != start) {
            path.add(current);
            current = parents.get(current);
        }

        Collections.reverse(path);
        return path;
    }

    private boolean isVisited(Set<Coordinate> visited, Coordinate coordinate) {
        return visited.contains(coordinate);
    }
}
