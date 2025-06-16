import com.sutyaginev.Board;
import com.sutyaginev.Coordinate;
import com.sutyaginev.Renderer;
import com.sutyaginev.entities.*;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(10, 10, new HashMap<>());
        Renderer renderer = new Renderer();
        Coordinate grassCoordinate = new Coordinate(1, 1);
        Coordinate treeCoordinate = new Coordinate(2, 4);
        Coordinate rockCoordinate = new Coordinate(5, 8);
        Coordinate herbivoreCoordinate = new Coordinate(2, 7);
        Coordinate predatorCoordinate = new Coordinate(7, 3);

        board.addEntity(grassCoordinate, new Grass(grassCoordinate));
        board.addEntity(treeCoordinate, new Tree(treeCoordinate));
        board.addEntity(rockCoordinate, new Rock(rockCoordinate));
        board.addEntity(herbivoreCoordinate, new Herbivore(herbivoreCoordinate, 10, 2));
        board.addEntity(predatorCoordinate, new Predator(predatorCoordinate, 20, 1, 5));

        renderer.render(board);
    }
}