package view;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import presenter.Presnter.Command;
import algorithms.mazeGenerator.Maze;
import algorithms.search.Solution;



/**
 * The Class MyView extends Observable implements View.
 * @author haizagury and livna haim.
 * @version 1.0
 * @since 17.05.15 .
 */
public class MyView extends Observable implements View{
	
	/** The in. */
	BufferedReader in;
	
	/** The out. */
	PrintStream out;
	
	/** The cli. */
	NewCli cli;
	
	/** The command queue. */
	Queue<Command> commandQueue;
	

	/** The user commands. */
	HashMap<String, Command> userCommands = new HashMap<String, Command>();

	/**
	 * Instantiates a new my view.
	 *
	 * @param in the in
	 * @param out the out
	 */
	public MyView(BufferedReader in, PrintStream out) {
	
		super();
		
		commandQueue = new LinkedList<Command>();
		
		this.in = new BufferedReader(new InputStreamReader(System.in));
		this.out = System.out;
	}
	


	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start() {
		
		setChanged();
		notifyObservers("Start");
		Thread t = new Thread(new CLIRunnable(cli) );
		t.start();
		
	}

	/* (non-Javadoc)
	 * @see view.View#setCommands(java.lang.String, presenter.Presnter.Command)
	 */
	@Override
	public void setCommands(String commandName, Command command) {
		userCommands.put(commandName, command);
		cli = new NewCli(userCommands, this, in, out);
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
			MyMazeDisplayer disMaze = new MyMazeDisplayer();
			disMaze.mazeDisplayer(m, out);
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
		if (s != null){		
			MySolutionDisplayer disSol = new MySolutionDisplayer();
			disSol.solutionDisplayer(s, out);
		}
		else{
			display("Solution not found" );
		}
		
	}

	@Override
	public void displayHint(Solution s) {
		if (s != null){		
			MySolutionDisplayer disSol = new MySolutionDisplayer();
			disSol.solutionDisplayer(s, out);
		}
		else{
			display("Solution not found" );
		}
	}

	/**
	 * Notify.
	 *
	 * @param arg the arg
	 */
	public void Notify(String arg){
		setChanged();
		notifyObservers(arg);
	}
	
	/* (non-Javadoc)
	 * @see view.View#display(java.lang.String)
	 */
	@Override
	public void display(String s) {
		System.out.println(s);
		
	    }
	
	
	
	@Override
	public String getRunOptions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@Override
	public void setRunOptions(String runProperties) {
		// TODO Auto-generated method stub
		
	}


}
