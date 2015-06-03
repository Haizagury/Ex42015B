
package model;

import java.util.concurrent.Callable;

import algorithms.mazeGenerator.Maze;
import algorithms.search.AStar;
import algorithms.search.BFS;
import algorithms.search.Heuristic;
import algorithms.search.MazaManhattanHeuristic;
import algorithms.search.MazeAirHeuristic;
import algorithms.search.Solution;




/** 
11 * The MazeSolveCallable class implements Callable. 
12 * Generates a solution for a maze with threads. 
13 * @author  hai zagury and livna haim 
14 * @version 1.0 
15 * @since 17.5.2015 
16 */ 

public class CallableSolution implements Callable<Solution> {

	/** The maze. */
	private Maze maze;
	
	/** The searcht type. */
	private String searchtType;
	
	/** The heuristic. */
	private String heuristic;
	
	/**
	 * Instantiates a new callable solution.
	 *
	 * @param maze the maze
	 * @param searchtType the searcht type
	 * @param heuristic the heuristic
	 */
	public CallableSolution(Maze maze, String searchtType, String heuristic) {
		this.maze = maze;
		this.searchtType = searchtType;
		this.heuristic = heuristic;
		}
	
	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Solution call() throws Exception {
		Solution solution = new Solution();
		if (this.searchtType.equals("BFS")){
			BFS bfsSearch = new BFS();
			solution = bfsSearch.search(maze);
		}
		else if (this.searchtType.equals("ASTAR")){
			Heuristic heur = null;;
			if (this.heuristic.equals("AIR")){
				heur = new MazeAirHeuristic();
				
			}
			else if (this.heuristic.equals("MANHATTAN")){
				heur = new MazaManhattanHeuristic();
			}
			AStar aStarSearch = new AStar(heur);
			solution = aStarSearch.search(maze);
		}
		
		return solution;
	}

}
