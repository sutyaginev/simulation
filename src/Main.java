import com.sutyaginev.Board;
import com.sutyaginev.Generator;
import com.sutyaginev.Renderer;
import com.sutyaginev.entities.Creature;
import com.sutyaginev.entities.Herbivore;
import com.sutyaginev.pathfinder.BreadthFirstSearch;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Board board = new Board(20, 20, new HashMap<>());
        Renderer renderer = new Renderer();
        Generator generator = new Generator(board);
        BreadthFirstSearch pathFinder = new BreadthFirstSearch(board);

        generator.generateStartEntitiesPositions();

        while (board.getActualEntityCount(Herbivore.class) > 0) {
            renderer.render(board);

            for (Creature creature : board.getCreatures()) {
                if (creature != board.getEntity(creature.getCoordinate())) { // Существо могло быть съедено ранее
                    continue;
                }

                creature.makeTurn(board, pathFinder);
            }

            generator.regenerateGrass();
            System.out.println();
        }
        renderer.render(board);
    }
}