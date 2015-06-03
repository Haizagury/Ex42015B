package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
 
 
 
 
 
 
 public class WinnerPage extends BasicWindow { 
 
 
 	//public int steps; 
 	 
 	public WinnerPage(String title, int width, int height,Display display) { 
 		super(title,width,height);
 		
 		 
 	} 
 
 
 	@Override 
 	protected void initWidgets() { 
 		shell.setLayout(new GridLayout(1,false)); 
 		shell.setBackgroundImage(new Image(null, "lib\\winner.jpg")); 
 		shell.setBackgroundMode(SWT.INHERIT_FORCE); 
 		Label LBUseDef = new Label(shell, SWT.FILL); 
 		LBUseDef.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1)); 
 		 
 	} 
 	@Override 
 	public void run() { 
 		initWidgets(); 
 		shell.open(); 
 		// main event loop 
 		 while(!shell.isDisposed()){ // while window isn't closed 
 
 
 		    // 1. read events, put then in a queue. 
 		    // 2. dispatch the assigned listener 
 		    if(!display.readAndDispatch()){ 	// if the queue is empty 
 		       display.sleep(); 			// sleep until an event occurs  
 		    } 
 
 
 		 } // shell is disposed 
 	} 
 
 
 } 