/**
 * 
 */
package model;

import java.util.concurrent.Callable;

import algorithms.mazeGenerator.Cell;
import algorithms.mazeGenerator.DFSMazeGenerator;
import algorithms.mazeGenerator.Maze;
import algorithms.mazeGenerator.MazeGenerator;
import algorithms.mazeGenerator.RandomMazeGenerator;

/**
 * The Class CallableMaze implements Callable<>.
 *Generate a Maze with thread.
 * @author hai zagury and livna haim
 * @version 1.0
 * @since 17.05.2015
 */
public class CallableMaze implements Callable<Maze> {
	
	/** The rows. */
	private int rows;
	
	/** The cols. */
	private int cols;
	
	/** The x start point. */
	private int xStartPoint;
	
	/** The y start point. */
	private int yStartPoint;
	
	/** The maze generate type. */
	private String mazeGenerateType;

	/**
	 * Instantiates a new callable maze.
	 *
	 * @param mazeGenerateType the maze generate type
	 * @param rows the rows
	 * @param cols the cols
	 * @param xStartPoint the x start point
	 * @param yStartPoint the y start point
	 */
	public CallableMaze(String mazeGenerateType, int rows, int cols, int xStartPoint, int yStartPoint) {

		this.mazeGenerateType = mazeGenerateType;
		this.rows = rows;
		this.cols = cols;
		this.xStartPoint = xStartPoint;
		this.yStartPoint = yStartPoint;
	}
	
	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Maze call() throws Exception {
		MazeGenerator mg = null;
		if (mazeGenerateType.equals("DFS")){
			mg = new DFSMazeGenerator();
			
		}
		else if (mazeGenerateType.equals("RANDOM")){
			mg = new RandomMazeGenerator();
		}
		Maze maze = mg.generateMaze(rows,cols,new Cell(xStartPoint,yStartPoint));
		return maze;
	}

}
