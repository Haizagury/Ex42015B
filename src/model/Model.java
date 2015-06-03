/**
 * 
 */
package model;

import algorithms.mazeGenerator.Maze;
import algorithms.search.Solution;




/**
 * The Interface Model.
 * @author hai zagury and livna haim.
 * @version 1.0
 * @since 17.05.15
 */
public interface Model {

	/**
	 * Generate maze.
	 *
	 * @param mazeName the maze name
	 */
	void generateMaze(String mazeName);
	
	/**
	 * Gets the maze.
	 *
	 * @param mazeName the maze name
	 * @return the maze
	 */
	Maze getMaze(String mazeName);
	
	/**
	 * Solve maze.
	 *
	 * @param mazeName the maze name
	 */
	void solveMaze(String mazeName);
	
	
	
	
	/**
	 * Gets the solution.
	 *
	 * @param mazeName the maze name
	 * @return {@link Solution}
	 */
	Solution getSolution(String mazeName);
	
	/**
	 * Stop.
	 */
	void stop();
	
	/**
	 * Upaload solutions.
	 */
	void upaloadSolutions();
	
	/**
	 * Start.
	 */
	void start();
	
	/**
	 * getRunProperties
	 * @return {@link String}
	 */
	public String getRunProperties();
	
	/**
	 * setRunProperties
	 */
	public void setRunProperties(String runProperties);
}
