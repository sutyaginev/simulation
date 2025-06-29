package com.sutyaginev;

import com.sutyaginev.actions.Action;
import com.sutyaginev.actions.CreaturesTurnAction;
import com.sutyaginev.actions.GenerateEntitiesAction;
import com.sutyaginev.actions.RegenerateGrassAction;
import com.sutyaginev.entities.Herbivore;
import com.sutyaginev.pathfinder.BreadthFirstSearch;
import com.sutyaginev.utility.Generator;
import com.sutyaginev.utility.Renderer;
import com.sutyaginev.world.WorldMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Simulation {

    private final WorldMap worldMap;
    private final Renderer renderer;
    private final Generator generator;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private int turnCounter;
    private boolean isRunning;

    public Simulation(int width, int height) {
        worldMap = new WorldMap(width, height, new HashMap<>());
        renderer = new Renderer();
        generator = new Generator(worldMap);
        turnCounter = 0;
        isRunning = false;
        initActions = new ArrayList<>();
        turnActions = new ArrayList<>();
        setupDefaultActions();
    }

    public void startSimulation() {
        if (isRunning) {
            return;
        }

        isRunning = true;
        executeInitActions();

        while (isRunning && worldMap.getActualEntityCount(Herbivore.class) > 0) {
            nextTurn();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Все травоядные съедены. Симуляция остановлена.");
    }

    public void pauseSimulation() {
        isRunning = false;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public boolean isRunning() {
        return isRunning;
    }

    private void nextTurn() {
        System.out.println("Turn: " + (++turnCounter));
        executeTurnActions();
        renderer.render(worldMap);
        System.out.println();
    }

    private void addInitAction(Action action) {
        initActions.add(action);
    }

    private void addTurnAction(Action action) {
        turnActions.add(action);
    }

    private void setupDefaultActions() {
        addInitAction(new GenerateEntitiesAction(generator));
        addTurnAction(new CreaturesTurnAction(new BreadthFirstSearch(worldMap)));
        addTurnAction(new RegenerateGrassAction(generator));
    }

    private void executeInitActions() {
        for (Action initAction : initActions) {
            initAction.execute(worldMap);
        }
    }

    private void executeTurnActions() {
        for (Action turnAction : turnActions) {
            turnAction.execute(worldMap);
        }
    }
}
