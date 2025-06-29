package com.sutyaginev;

import com.sutyaginev.actions.Action;
import com.sutyaginev.actions.CreaturesTurnAction;
import com.sutyaginev.actions.GenerateEntitiesAction;
import com.sutyaginev.actions.RegenerateGrassAction;
import com.sutyaginev.entities.Herbivore;
import com.sutyaginev.pathfinder.BreadthFirstSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Simulation {

    private final Board board;
    private final Renderer renderer;
    private final Generator generator;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private int turnCounter;
    private boolean isRunning;

    public Simulation(int width, int height) {
        board = new Board(width, height, new HashMap<>());
        renderer = new Renderer();
        generator = new Generator(board);
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

        while (isRunning && board.getActualEntityCount(Herbivore.class) > 0) {
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
        renderer.render(board);
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
        addTurnAction(new CreaturesTurnAction(new BreadthFirstSearch(board)));
        addTurnAction(new RegenerateGrassAction(generator));
    }

    private void executeInitActions() {
        for (Action initAction : initActions) {
            initAction.execute(board);
        }
    }

    private void executeTurnActions() {
        for (Action turnAction : turnActions) {
            turnAction.execute(board);
        }
    }
}
