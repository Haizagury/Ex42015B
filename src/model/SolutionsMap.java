/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.HashMap;

import algorithms.mazeGenerator.Maze;
import algorithms.search.Solution;

// TODO: Auto-generated Javadoc
/**
 * The Class SolutionsMap.
 *
 * @author haizagury and livna haim.
 * @version 1.0.
 * @since 17.05.15 .
 */
public class SolutionsMap extends HashMap<Maze,Solution> implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new solutions map.
	 */
	public SolutionsMap() {
		
	}

}
