/**
 * 
 */
package view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import presenter.Presnter.Command;
import algorithms.mazeGenerator.Maze;
import algorithms.search.Solution;

/**
 * @author HFL
 *
 */
public class MyGuiView extends Observable implements View {

	/** The command queue. */
	Queue<Command> commandQueue;
	

	/** The user commands. */
	HashMap<String, Command> userCommands;
	
	
	MazeWindow mazeWindow ;
	String runOptions;
	
	public MyGuiView() {
		super();
		userCommands = new HashMap<String, Command>();
		commandQueue = new LinkedList<Command>();
		runOptions = null;
		
	}
	
	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start() {
		setChanged();
		notifyObservers("Start");
		mazeWindow = new MazeWindow("maze", 600, 600,this);
		mazeWindow.run();
	}

	/* (non-Javadoc)
	 * @see view.View#setCommands(java.lang.String, presenter.Presnter.Command)
	 */
	@Override
	public void setCommands(String commandName, Command command) {
		userCommands.put(commandName, command);

	}

	/* (non-Javadoc)
	 * @see view.View#getUserCommand()
	 */
	@Override
	public Command getUserCommand() {
		return commandQueue.poll();
	}

	/* (non-Javadoc)
	 * @see view.View#displayMaze(algorithms.mazeGenerator.Maze)
	 */
	@Override
	public void displayMaze(Maze m) {
		if (m != null){
			
			mazeWindow.setMazeData(m);
		}
		else{
			display("Maze not found" );
		}

	}

	/* (non-Javadoc)
	 * @see view.View#displaySolution(algorithms.search.Solution)
	 */
	@Override
	public void displaySolution(Solution s) {
		mazeWindow.displaySolution(s);

	}

	/* (non-Javadoc)
	 * @see view.View#display(java.lang.String)
	 */
	@Override
	public void display(String s) {
		mazeWindow.displayMsg(s);

	}
	
	public void Notify(String arg){
		setChanged();
		notifyObservers(arg);
	}

	@Override
	public String getRunOptions() {
		// TODO Auto-generated method stub
		return runOptions;
	}

	@Override
	public void setRunOptions(String runProperties) {
		this.runOptions = runProperties;
		
	}

	@Override
	public void displayHint(Solution s) {
		mazeWindow.displayHint(s);
		
	}

}
