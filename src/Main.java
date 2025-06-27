import com.sutyaginev.Board;
import com.sutyaginev.Generator;
import com.sutyaginev.Renderer;
import com.sutyaginev.entities.Grass;
import com.sutyaginev.entities.Herbivore;
import com.sutyaginev.entities.Rock;
import com.sutyaginev.entities.Tree;
import com.sutyaginev.pathfinder.BreadthFirstSearch;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Board board = new Board(20, 20, new HashMap<>());
        Renderer renderer = new Renderer();
        Generator generator = new Generator();
        BreadthFirstSearch pathFinder = new BreadthFirstSearch(board);

        generator.generateEntities(board, 10, Tree.class, () -> new Tree(generator.generateRandomEmptyCoordinate(board)));
        generator.generateEntities(board, 10, Rock.class, () -> new Rock(generator.generateRandomEmptyCoordinate(board)));
        generator.generateEntities(board, 20, Grass.class, () -> new Grass(generator.generateRandomEmptyCoordinate(board)));

        Herbivore herbivore = new Herbivore(generator.generateRandomEmptyCoordinate(board), 10, 2);
        board.addEntity(herbivore);

        while (board.getActualEntityCount(Grass.class) > 0) {
            renderer.render(board);
            herbivore.makeTurn(board, pathFinder);
            System.out.println();
        }
        renderer.render(board);
    }
}