/**
 * 
 */
package view;


import presenter.Presnter.Command;
import algorithms.mazeGenerator.Maze;
import algorithms.search.Solution;



/**
 * The Interface View.
 *
 * @author hai zaguey and livna haim.
 * @version 1.0
 * @since 17.05.15 .
 */
public interface View {
	
	/**
	 * Start.
	 */
	void start();
	
	/**
	 * Sets the commands.
	 *
	 * @param commandName the command name
	 * @param command the command
	 */
	void setCommands(String  commandName, Command command);
	
	/**
	 * Gets the user command.
	 *
	 * @return the user command
	 */
	Command getUserCommand();
	
	/**
	 * Display maze.
	 *
	 * @param m the m
	 */
	void displayMaze(Maze m);
	
	/**
	 * Display solution.
	 *
	 * @param s the s
	 */
	void displaySolution(Solution s);
	

	/**
	 * Display solution.
	 *
	 * @param s the s
	 */
	void displayHint(Solution s);
	
	/**
	 * Display.
	 *
	 * @param s the s
	 */
	void display(String s);
	
	/**
	 * getRunOptions
	 * @return {@link String}
	 */
	public String getRunOptions();
	
	/**
	 * setRunOptions
	 */
	public void setRunOptions(String runProperties);

}


