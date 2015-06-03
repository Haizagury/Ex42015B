package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import presenter.Presnter.Command;


/**
 * The Class NewCli.
 * @author haizagury and livna haim.
 * @version 1.0
 * @since 17.05.15 .
 */
public class NewCli {
	
	/** The in. */
	private BufferedReader in;
	
	/** The out. */
	private PrintStream out;
	
	/** The view. */
	private MyView view;
	
	/** The user commands. */
	HashMap<String, Command> userCommands = new HashMap<String, Command>();

	/**
	 * Instantiates a new new cli.
	 *
	 * @param userCommands the user commands
	 * @param view the view
	 * @param in the in
	 * @param out the out
	 */
	public NewCli(HashMap<String, Command> userCommands, MyView view, BufferedReader in, PrintStream out) {
		this.view = view;
		this.userCommands = userCommands;
		this.in = in;
		this.out = out;
		}

	/**
	 * Start.
	 */
	public void start() 
	 	{ 
	 		out.print("Enter command: "); 
	 
	 
	 		try { 
	 			String line = in.readLine(); 
	 			 
	 			while (!line.equals("exit")) 
	 			{ 
	 				String[] sp = line.split (" ", 2); 
	 								 
	 				String commandName = sp[0]; 
	 				String arg = null; 
	 				if (sp.length > 1) 
	 					arg = sp[1]; 
	 				// Invoke the command 
	 				Command command = userCommands.get(commandName);
	 				if(command == null) 
	 					out.println("command not found "+commandName); 
	 				else 
	 					if(arg==null) 
	 						out.println("No argument has been entered"); 
	 					else{ 
	 						view.commandQueue.isEmpty();
	 						view.commandQueue.add(command);
	 					    view.Notify("Command " + arg);
	 						
	 					}
	 				 
	 				out.print("Enter command: "); 
	 				line = in.readLine(); 
	 			} 
	 			out.println("Goodbye");
	 			view.commandQueue.add(userCommands.get("exit"));
	 			view.Notify("Command Exit");
	 						 
	 		} catch (IOException e) {			 
	 			System.out.println("can't read/write from/to in/out streams"); 
	 		} finally { 
	 			try { 
	 				in.close(); 
	 				out.close(); 
	 			} catch (IOException e) {				 
	 				System.out.println("can't close from in/out streams"); 
	 			}		 
	 		}	 
	 	} 

}
