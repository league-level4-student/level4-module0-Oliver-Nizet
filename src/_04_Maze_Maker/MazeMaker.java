package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		Random r = new Random();
		int cx = r.nextInt(w);
		int cy = r.nextInt(h);

		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.cells[cx][cy]);

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.hasBeenVisited();
		// B. check for unvisited neighbors using the cell
		boolean up = false;
		boolean down = false;
		boolean left = false;
		boolean right = false;
		if (maze.getCell(currentCell.getX(), currentCell.getY() - 1).hasBeenVisited()) {
			up = true;
		}
		if (maze.getCell(currentCell.getX(), currentCell.getY() + 1).hasBeenVisited()) {
			down = true;
		}
		if (maze.getCell(currentCell.getX() - 1, currentCell.getY()).hasBeenVisited()) {
			left = true;
		}
		if (maze.getCell(currentCell.getX() + 1, currentCell.getY()).hasBeenVisited()) {
			right = true;
		}
		// C. if has unvisited neighbors,
		if (up || down || left || right) {
			boolean done = false;
			while (!done) {
				// C1. select one at random.
				Random r = new Random();
				int ran = r.nextInt(4);
				if (ran == 0) {
					if (up) {
						uncheckedCells.push(maze.getCell(currentCell.getX(), currentCell.getY() - 1));
					}
				}
				if (ran == 1) {
					if (down) {
						uncheckedCells.push(maze.getCell(currentCell.getX(), currentCell.getY() + 1));
					}
				}
				if (ran == 2) {
					if (left) {
						uncheckedCells.push(maze.getCell(currentCell.getX() - 1, currentCell.getY()));
					}
				}
				if (ran == 3) {
					if (right) {
						uncheckedCells.push(maze.getCell(currentCell.getX() + 1, currentCell.getY()));
					}
				}
				// C2. push it to the stack

				// C3. remove the wall between the two cells

				// C4. make the new cell the current cell and mark it as visited

				// C5. call the selectNextPath method with the current cell
			}
		}
		// D. if all neighbors are visited

		// D1. if the stack is not empty

		// D1a. pop a cell from the stack

		// D1b. make that the current cell

		// D1c. call the selectNextPath method with the current cell

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {

	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		return null;
	}
}
