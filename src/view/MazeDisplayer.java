package view;

import java.io.PrintStream;

import algorithms.mazeGenerator.Maze;

// TODO: Auto-generated Javadoc
/**
 * The Interface MazeDisplayer.
 * @author haizagury and livna haim.
 * @version 1.0.
 * @since 17.05.15 .
 */
public interface MazeDisplayer {

	/**
	 * Maze displayer.
	 *
	 * @param m the m
	 * @param out the out
	 */
	public void mazeDisplayer(Maze m , PrintStream out);
}
