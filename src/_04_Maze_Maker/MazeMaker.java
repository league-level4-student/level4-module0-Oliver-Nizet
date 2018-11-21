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

		// C. if has unvisited neighbors,
		if (!getUnvisitedNeighbors(currentCell).isEmpty()) {
			// C1. select one at random.
			Random r = new Random();
			int ran = r.nextInt(getUnvisitedNeighbors(currentCell).size());
			// C2. push it to the stack
			uncheckedCells.push(getUnvisitedNeighbors(currentCell).get(ran));
			// C3. remove the wall between the two cells
			removeWalls(currentCell, getUnvisitedNeighbors(currentCell).get(ran));
			// C4. make the new cell the current cell and mark it as visited
			currentCell = getUnvisitedNeighbors(currentCell).get(ran);
			currentCell.hasBeenVisited();
			// C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		// D. if all neighbors are visited
		if (getUnvisitedNeighbors(currentCell).isEmpty()) {
			// D1. if the stack is not empty
			if (!uncheckedCells.isEmpty()) {
				// D1a. pop a cell from the stack
				// D1b. make that the current cell
				currentCell = uncheckedCells.pop();
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() == c2.getX()) {
			if (c1.getY() > c2.getY()) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			} else {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
		} else {
			if (c1.getX() > c2.getX()) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			} else {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> cells = new ArrayList<Cell>();
		if (c.getY() > 0 && !maze.getCell(c.getX(), c.getY() - 1).hasBeenVisited()) {
			cells.add(maze.getCell(c.getX(), c.getY() - 1));
		}
		if (c.getY() < width && !maze.getCell(c.getX(), c.getY() + 1).hasBeenVisited()) {
			cells.add(maze.getCell(c.getX(), c.getY() + 1));
		}
		if (c.getX() > 0 && !maze.getCell(c.getX() - 1, c.getY()).hasBeenVisited()) {
			cells.add(maze.getCell(c.getX() - 1, c.getY()));
		}
		if (c.getX() < height && !maze.getCell(c.getX() + 1, c.getY()).hasBeenVisited()) {
			cells.add(maze.getCell(c.getX() + 1, c.getY()));
		}
		return cells;
	}
}
