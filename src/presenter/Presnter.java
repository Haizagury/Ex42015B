package presenter;

import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;


/**
 * The Class Presnter.
 * @author haizagury and livna haim.
 * @version 1.0 .
 * @since 17.05.15 .
 */
public class Presnter implements Observer {

	/** The view. */
	View view;
	
	/** The model. */
	Model model;
	
	/**
	 * Instantiates a new presnter.
	 *
	 * @param view the view
	 * @param model the model
	 */
	public Presnter(View view, Model model) {
		this.view = view;
		this.model = model;
		setUserCommand();
	}
	
	/**
	 * Sets the user command.
	 */
	public void setUserCommand(){
		view.setCommands("generateMaze" ,new generateMazeCommand());
		view.setCommands("displayMaze" ,new displayMazeCommand());
		view.setCommands("solveMaze" ,new solveMazeCommand());
		view.setCommands("displaySolution" ,new displaySolutionCommand());
		view.setCommands("displayHint" ,new displayHintCommand());
		view.setCommands("exit" ,new exitCommand());
		view.setCommands("getProperties", new getProperties());
		view.setCommands("setProperties", new setProperties());
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		String[] Name_params = ((String)arg).split(" ");
		if (o == view){
			if (Name_params[0].equals("Start")){
				start();
			}
			else if(Name_params[0].equals("Command")){
				Command command = view.getUserCommand();
				command.doCommand(Name_params[1]);
			}
		}
		else if (o == model){
					
					if (Name_params[0].equals("generateMazeComplete")){
//						view.display("Maze " + Name_params[1] + " is ready");
						
					}
					else if(Name_params[0].equals("solveMazeCompleted")){
//						view.display("Solution for " + Name_params[1] + " ready");
						
					}
					//solveMazeCompletedErorr
					else if(Name_params[0].equals("solveMazeCompletedErorr")){
						view.display("Maze " + Name_params[1] + " not exist");
						
					}
		}
					
	}
	
	
	/**
	 * Start.
	 */
	private void start() {
		model.start();
		
	}


	/**
	 * The Interface Command.
	 */
	public interface Command {
			
		/**
		 * Do command.
		 *
		 * @param arg the arg
		 */
		void doCommand(String arg);
	}
	

	/**
	 * The Class generateMazeCommand.
	 */
	public class generateMazeCommand implements Command {

		/* (non-Javadoc)
		 * @see presenter.Presnter.Command#doCommand(java.lang.String)
		 */
		@Override
		public void doCommand(String arg) {
			model.generateMaze(arg/*, 0, 0*/);

		}
		
	}
	
		/**
		 * The Class displayMazeCommand.
		 */
		public class displayMazeCommand implements Command {
			
			/* (non-Javadoc)
			 * @see presenter.Presnter.Command#doCommand(java.lang.String)
			 */
			@Override
			public void doCommand(String arg) {
				if(model.getMaze(arg) != null)
					view.displayMaze(model.getMaze(arg));
				else
					view.display("Maze " + arg + "  not found");
			}
		}	
     	
	     /**
	      * The Class solveMazeCommand.
	      */
	     public class solveMazeCommand implements Command {
				
				/* (non-Javadoc)
				 * @see presenter.Presnter.Command#doCommand(java.lang.String)
				 */
				@Override
				public void doCommand(String arg) {
					model.solveMaze(arg);
				}	
     	}			
			
     	/**
	      * The Class displaySolutionCommand.
	      */
	     public class displaySolutionCommand implements Command {
    		
		    /* (non-Javadoc)
		     * @see presenter.Presnter.Command#doCommand(java.lang.String)
		     */
		    @Override
    		public void doCommand(String arg) {
    			if(model.getSolution(arg) != null)
    				view.displaySolution(model.getSolution(arg));
    			else
    				view.display("Solution for maze : " + arg + " not found");
    			
    		}
     	}
	     
	     /**
	      * The Class displaySolutionCommand.
	      */
	     public class displayHintCommand implements Command {
    		
		    /* (non-Javadoc)
		     * @see presenter.Presnter.Command#doCommand(java.lang.String)
		     */
		    @Override
    		public void doCommand(String arg) {
    			if(model.getSolution(arg) != null)
    				view.displayHint(model.getSolution(arg));
    			else
    				view.display("Solution for maze : " + arg + " not found");
    			
    		}
     	}
     	
	     /**
     	 * The Class exitCommand.
     	 */
     	public class exitCommand implements Command {
	    		
		    	/* (non-Javadoc)
		    	 * @see presenter.Presnter.Command#doCommand(java.lang.String)
		    	 */
		    	@Override
	    		public void doCommand(String arg) {
	    			model.stop();
	    		}  
	     }

     	 /**
     	 * The Class getProperties.
     	 */
     	public class getProperties implements Command {
	    		
		    	/* (non-Javadoc)
		    	 * @see presenter.Presnter.Command#doCommand(java.lang.String)
		    	 */
		    	@Override
	    		public void doCommand(String arg) {
		    		view.setRunOptions(model.getRunProperties());
	    			
	    		}  
	     }
	
     	 /**
     	 * The Class setProperties.
     	 */
     	public class setProperties implements Command {
	    		
		    	/* (non-Javadoc)
		    	 * @see presenter.Presnter.Command#doCommand(java.lang.String)
		    	 */
		    	@Override
	    		public void doCommand(String arg) {
	    			model.setRunProperties(arg);
	    		}  
	     }
	
}
