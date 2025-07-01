package com.sutyaginev.simulation.utility;

import com.sutyaginev.simulation.entity.Entity;
import com.sutyaginev.simulation.world.WorldMap;
import com.sutyaginev.simulation.world.Coordinate;

public class Renderer {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CELL_BACKGROUND = "\u001B[40m";
    private static final String ANSI_EMPTY_CELL_SPRITE = "\u2005".repeat(8); // 🏿 🏾 🏽 🏼 🏻

    public void render(WorldMap worldMap) {
        for (int y = worldMap.getHeight() - 1; y >= 0; y--) {
            StringBuilder line = new StringBuilder(ANSI_CELL_BACKGROUND);
            for (int x = 0; x < worldMap.getWidth(); x++) {
                Coordinate coordinate = new Coordinate(x, y);

                if (worldMap.isCellEmpty(coordinate)) {
                    line.append(getEmptyCellSprite());
                } else {
                    line.append(getEntitySprite(worldMap.getEntity(coordinate)));
                }
            }

            line.append(ANSI_RESET);
            System.out.println(line);
        }
    }

    private String getEmptyCellSprite() {
        return ANSI_CELL_BACKGROUND + ANSI_EMPTY_CELL_SPRITE;
    }

    private String getEntitySprite(Entity entity) {
        switch (entity.getClass().getSimpleName()) {
            case "Rock":
                return "\u2005" + "🪨" + "\u2005";
            case "Tree":
                return "\u2005" + "🌳" + "\u2005"; // 🌵
            case "Grass":
                return "\u2005" + "🌾" + "\u2005"; // 🌿
            case "Herbivore":
                return "\u2005" + "🐤" + "\u2005"; // 🐰
            case "Predator":
                return "\u2005" + "🦊" + "\u2005";
            default:
                return "";
        }
    }
}
