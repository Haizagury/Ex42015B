/**
 * 
 */
package view;



//import javax.swing.text.MaskFormatter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import presenter.Presnter.Command;
import algorithms.mazeGenerator.Maze;
import algorithms.search.Solution;

/**
 * @author HFL
 *
 */


public class MazeWindow extends BasicWindow {

	private MyGuiView view; 
	Maze mazeData;
	private int mazeCounter = 0;
	Shell optionShell;
	MazeDisplay maze;
	boolean Diagnoal = false;	
	
	private String optionsMazeGenerateType;
	private String optionsSearchType;
	private String optionsHeuristic;
	private Integer optionsRows;
	private Integer optionsCols;
	private Integer optionsXStartPoint;
	private Integer optionsYStartPoint;
	
	public Maze getMazeData() {
		return mazeData;
	}

	public void setMazeData(Maze mazeData) {
		this.mazeData = mazeData;
	}

	public MazeWindow(String title, int width, int height, MyGuiView view/*,Maze m*/) {
		super(title, width, height);
		this.view = view;
		//this.mazeData = m;
		
	}

	/* (non-Javadoc)
	 * @see view.BasicWindow#initWidgets()
	 */
	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(2, false));
		uploadNewMaze();
				
		Command command1 = view.userCommands.get("getProperties");
		view.commandQueue.add(command1);
		view.Notify("Command getProperties");
		getOptions();
		
		
		/*------------------------------------------------------------*/
		//Close Shell with X-RED Button
		shell.addListener(SWT.Close, new Listener()
	    {
	        public void handleEvent(Event event)
	        {
	        	exit();		
	            event.doit = true;
	        }
	    });
		
		
		
		//Menu
		createMenu();
		
	    //Option Shell
	    createOptionShell();
	    
		//החלק של הכפתור ושני שדות הטקטס צריך למחוק בסוף הם לצורך נוחות בדיקה

	    Button moveButton = new Button(shell, SWT.PUSH);
		moveButton.setText("move");
		moveButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		
		maze=new MazeDisplay(shell, SWT.BORDER);
		maze.setMazeData(mazeData);
		maze.startMazeDisplay();
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,7));
//		maze.addMouseWheelListener(new MouseWheelListener() {
//			
//			@Override 
//			 			public void mouseScrolled(MouseEvent e)  
//			 			{ 
//			 				if((e.stateMask & SWT.CTRL) != 0) 
//							{ 
//			 					if(e.count > 0) 
//			 				maze.addMouseWheelListener(new MouseWheelListener() {
//			
//			@Override 
//			 			public void mouseScrolled(MouseEvent e)  
//			 			{ 
//			 				if((e.stateMask & SWT.CTRL) != 0) 
//							{ 
//			 					if(e.count > 0) 
//			 					{ 
//			 						if((int)((maze.getSize().x)*1.1)<= shell.getSize().x && (int)((maze.getSize().y)*1.1)<= shell.getSize().y) 
//			 						{ 
//			 							maze.setSize((int)((maze.getSize().x)*1.1),(int)((maze.getSize().y)*1.1)); 
//			 							maze.redraw(); 
//			 						} 
//			 					} 
//			 					else  
//			 					{ 
//			 						if((maze.getSize().x)>=0 && (maze.getSize().y)>=0) 
//			 						{ 
//			 							maze.setSize((int)((maze.getSize().x)/1.1),(int)((maze.getSize().y)/1.1)); 
//			 							maze.redraw(); 
//			 						} 
//			 					} 
//			 						 
//			 				} 
//			 				 
//			 			} 
//			 		}); 	{ 
//			 						if((int)((maze.getSize().x)*1.1)<= shell.getSize().x && (int)((maze.getSize().y)*1.1)<= shell.getSize().y) 
//			 						{ 
//			 							maze.setSize((int)((maze.getSize().x)*1.1),(int)((maze.getSize().y)*1.1)); 
//			 							maze.redraw(); 
//			 						} 
//			 					} 
//			 					else  
//			 					{ 
//			 						if((maze.getSize().x)>=0 && (maze.getSize().y)>=0) 
//			 						{ 
//			 							maze.setSize((int)((maze.getSize().x)/1.1),(int)((maze.getSize().y)/1.1)); 
//			 							maze.redraw(); 
//			 						} 
//			 					} 
//			 						 
//			 				} 
//			 				 
//			 			} 
//			 		}); 

		
		final Text xText = new Text(shell, SWT.BORDER);
		xText.setLayoutData(new GridData(SWT.FILL, SWT.None, false,false,1,1));
		
		final Text yText = new Text(shell, SWT.BORDER);
		yText.setLayoutData(new GridData(SWT.FILL, SWT.None, false,false,1,1));

		moveButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				maze.move((new Integer(xText.getText()).intValue()), (new Integer(yText.getText()).intValue()));
				//maze.draw((new Integer(xText.getText()).intValue()), (new Integer(yText.getText()).intValue()));
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		// כאן נגמר הקטע קוד שיש להוריד לפני ההגשה
		
		/*
		 * את החלק הזה צריך להוסיף במקום בקוד עם הכפתורים
		 
		maze=new MazeDisplay(shell, SWT.BORDER);
		maze.setMazeData(mazeData);
		maze.startMazeDisplay();
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
		*/
		
	}

	public void displayMsg(String s) {
		MessageBox mb = new MessageBox(shell);
		mb.setMessage(s);
		mb.open();
		
	}
	
	private void setOptions(){
		StringBuilder sb = new StringBuilder();
		sb.append(getOptionsMazeGenerateType());
		sb.append(",");
		sb.append(getOptionsSearchType());
		sb.append(",");
		sb.append(getOptionsHeuristic());
		sb.append(",");
		sb.append(getOptionsRows().toString());
		sb.append(",");
		sb.append(getOptionsCols().toString());
		sb.append(",");
		sb.append(getOptionsXStartPoint().toString());
		sb.append(",");
		sb.append(getOptionsYStartPoint().toString());
		view.runOptions = sb.toString();
	}
	
	private void getOptions() {
		String[] options = view.runOptions.split(",");
		setOptionsMazeGenerateType(options[0]);
		setOptionsSearchType(options[1]);
		setOptionsHeuristic(options[2]);
		setOptionsRows(Integer.valueOf(options[3]));
		setOptionsCols(Integer.valueOf(options[4]));
		setOptionsXStartPoint(Integer.valueOf(options[5]));
		setOptionsYStartPoint(Integer.valueOf(options[6]));
	}
	
	public String getOptionsMazeGenerateType() {
		return optionsMazeGenerateType;
	}

	public void setOptionsMazeGenerateType(String optionsMazeGenerateType) {
		this.optionsMazeGenerateType = optionsMazeGenerateType;
	}

	public String getOptionsSearchType() {
		return optionsSearchType;
	}

	public void setOptionsSearchType(String optionsSearchType) {
		this.optionsSearchType = optionsSearchType;
	}

	public String getOptionsHeuristic() {
		return optionsHeuristic;
	}

	public void setOptionsHeuristic(String optionsHeuristic) {
		this.optionsHeuristic = optionsHeuristic;
	}

	public Integer getOptionsRows() {
		return optionsRows;
	}

	public void setOptionsRows(Integer optionsRows) {
		this.optionsRows = optionsRows;
	}

	public Integer getOptionsCols() {
		return optionsCols;
	}

	public void setOptionsCols(Integer optionsCols) {
		this.optionsCols = optionsCols;
	}

	public Integer getOptionsXStartPoint() {
		return optionsXStartPoint;
	}

	public void setOptionsXStartPoint(Integer optionsXStartPoint) {
		this.optionsXStartPoint = optionsXStartPoint;
	}

	public Integer getOptionsYStartPoint() {
		return optionsYStartPoint;
	}

	public void setOptionsYStartPoint(Integer optionsYStartPoint) {
		this.optionsYStartPoint = optionsYStartPoint;
	}
	
	public void uploadNewMaze(){

		mazeCounter++;
		Command command = view.userCommands.get("generateMaze");
		view.commandQueue.add(command);
		view.Notify("Command maze" + mazeCounter);
		
		command = view.userCommands.get("displayMaze");
		view.commandQueue.add(command);
		view.Notify("Command maze" + mazeCounter);
		
	}
	
	public void exit(){
		Command command1 = view.userCommands.get("exit");
		view.commandQueue.add(command1);
		view.Notify("Command exit");
	}
	
	public void createMenu(){
		Menu menuBar = new Menu(shell, SWT.BAR);
	    MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    fileMenuHeader.setText("&Game");
	    
	    
	    
	    Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
	    fileMenuHeader.setMenu(fileMenu);

	    MenuItem newGameItem = new MenuItem(fileMenu, SWT.PUSH);
	    newGameItem.setText("&New Game\tF2");
	    newGameItem.setAccelerator(SWT.F2);
	    
	    
	    MenuItem optionsItem = new MenuItem(fileMenu, SWT.PUSH);
	    optionsItem.setText("&Options\tF5");
	    optionsItem.setAccelerator(SWT.F5);
	    
	    new MenuItem(fileMenu, SWT.SEPARATOR);

	    MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileExitItem.setText("E&xit");
	    
	    MenuItem helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    helpMenuHeader.setText("&Help");
	    

	    Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
	    helpMenuHeader.setMenu(helpMenu);
	    
	    MenuItem helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpGetHelpItem.setText("&Get Help\tF1");
	    helpGetHelpItem.setAccelerator(SWT.F1);
	    
	    MenuItem helpGetSolutionItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpGetSolutionItem.setText("&Get Solution\tF3");
	    helpGetSolutionItem.setAccelerator(SWT.F3);
	    
	    MenuItem helpGetHintItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpGetHintItem.setText("&Get Hint\tF4");
	    helpGetHintItem.setAccelerator(SWT.F4);
	    
	    helpGetSolutionItem.addSelectionListener(new  SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Command command1 = view.userCommands.get("solveMaze");
				view.commandQueue.add(command1);
				view.Notify("Command maze" + mazeCounter);
				
				command1 = view.userCommands.get("displaySolution");
				view.commandQueue.add(command1);
				view.Notify("Command maze" + mazeCounter);
				
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    helpGetHintItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Command command1 = view.userCommands.get("solveMaze");
				view.commandQueue.add(command1);
				view.Notify("Command maze" + mazeCounter);
				
				command1 = view.userCommands.get("displayHint");
				view.commandQueue.add(command1);
				view.Notify("Command maze" + mazeCounter);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    fileExitItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				exit();
				shell.dispose();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    newGameItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.setVisible(false);
				shell = new Shell();
				initWidgets();
				shell.open();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    optionsItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (!optionShell.getEnabled())
					optionShell.setEnabled(true);
				
				Command command1 = view.userCommands.get("getProperties");
				view.commandQueue.add(command1);
				view.Notify("Command getProperties");
				
				getOptions();
				
				optionShell.open();
				shell.setEnabled(false);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	    shell.setMenuBar(menuBar);

	}
	
	public void createOptionShell(){
		/*final Shell*/ optionShell = new Shell(shell);
	    optionShell.setText("Options");
	    optionShell.setSize(300, 400);
	    optionShell.setLayout(new GridLayout(2,false));	    										
										    
	    //Board Properties Group
	    Group boardGroup = new Group(optionShell, SWT.None);
	    boardGroup.setText("Board");
	    boardGroup.setLayout(new GridLayout(4,false));
	    boardGroup.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false,2,1));
	    
	    
	    Label boardWidthLbl = new Label(boardGroup,SWT.BOLD);
	    boardWidthLbl.setText("Width");
	    boardWidthLbl.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false, 1,1));
	   
	    final Spinner boardWidthSpn = new Spinner(boardGroup,SWT.None);
	    boardWidthSpn.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false, 1,1));
	    boardWidthSpn.setMaximum(100);
	    boardWidthSpn.setMinimum(5);
	    boardWidthSpn.setSelection(getOptionsRows());
	    
	    Label boardHeightLbl = new Label(boardGroup,SWT.BOLD);
	    boardHeightLbl.setText("Height");
	    boardHeightLbl.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false,1,1));
	    
	    final Spinner boardHeighSpn = new Spinner(boardGroup,SWT.None);
	    boardHeighSpn.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false, 1,1));
	    boardHeighSpn.setMaximum(100);
	    boardHeighSpn.setMinimum(5);
	    boardHeighSpn.setSelection(getOptionsCols());
	    
	    Group boardGenerateGrp = new Group(boardGroup, SWT.None);
	    boardGenerateGrp.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false,4,1));
	    boardGenerateGrp.setText("Board Type Generating");
	    boardGenerateGrp.setLayout(new RowLayout(SWT.HORIZONTAL/*.VERTICAL*/));
	    
	    final Button dfsGenerateBtn =  new Button(boardGenerateGrp, SWT.RADIO);
	    dfsGenerateBtn.setText("DFS");
	   
	    
	    final Button randomGenerateBtn = new Button(boardGenerateGrp, SWT.RADIO);
	    randomGenerateBtn.setText("Random");
	    
	    if (getOptionsMazeGenerateType().equals("DFS"))
	    	 dfsGenerateBtn.setSelection(true);
	    else
	    	randomGenerateBtn.setSelection(true);
	    
	    Group boardCharacterGrp = new Group(boardGroup, SWT.None);
	    boardCharacterGrp.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false,4,1));
	    boardCharacterGrp.setText("Character");
	    boardCharacterGrp.setLayout(new RowLayout(SWT.HORIZONTAL/*.VERTICAL*/));
	    
	    Button bambaCharacterBtn =  new Button(boardCharacterGrp, SWT.RADIO);
	    bambaCharacterBtn.setText("Bamba Baby");
	    bambaCharacterBtn.setSelection(true);
	    
	    Button starCharacterBtn = new Button(boardCharacterGrp, SWT.RADIO);
	    starCharacterBtn.setText("Star");
	    
	    Label startPointLbl = new Label(boardGroup,SWT.BOLD);
	    startPointLbl.setText("Start Point");
	    startPointLbl.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false, 4,1));
	    
	    Label xStartPointLbl = new Label(boardGroup,SWT.BOLD);
	    xStartPointLbl.setText("X");
	    xStartPointLbl.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false, 1,1));
	   
	    final Spinner xStartPointSpn = new Spinner(boardGroup,SWT.None);
	    xStartPointSpn.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false, 1,1));
	    xStartPointSpn.setMaximum(boardWidthSpn.getSelection()-1);
	    if (getOptionsXStartPoint() > boardWidthSpn.getSelection()-1)
	    	xStartPointSpn.setSelection(boardWidthSpn.getSelection()-1);
	    else
	    	xStartPointSpn.setSelection(getOptionsXStartPoint());
	    
	    Label yStartPointLbl = new Label(boardGroup,SWT.BOLD);
	    yStartPointLbl.setText("Y");
	    yStartPointLbl.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false,1,1));
	   
	    final Spinner yStartPointSpn = new Spinner(boardGroup,SWT.None);
	    yStartPointSpn.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false, 1,1));
	    yStartPointSpn.setMaximum(boardHeighSpn.getSelection()-1);
	    if (getOptionsYStartPoint() > boardHeighSpn.getSelection()-1)
	    	yStartPointSpn.setSelection(boardHeighSpn.getSelection()-1);
	    else
	    	yStartPointSpn.setSelection(getOptionsYStartPoint());
	    
	    boardWidthSpn.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				xStartPointSpn.setMaximum(boardWidthSpn.getSelection()-1);
				if (xStartPointSpn.getMaximum() > boardWidthSpn.getSelection()-1)
					xStartPointSpn.setSelection(boardWidthSpn.getSelection()-1);
				setOptionsRows(boardWidthSpn.getSelection());
				
			}
		});
	    
	    boardHeighSpn.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				yStartPointSpn.setMaximum(boardHeighSpn.getSelection()-1);
				if (yStartPointSpn.getMaximum() > boardHeighSpn.getSelection()-1)
					yStartPointSpn.setSelection(boardHeighSpn.getSelection()-1);
				setOptionsCols(boardHeighSpn.getSelection());
				
			}
		});
	    
	    dfsGenerateBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setOptionsMazeGenerateType(dfsGenerateBtn.getText());
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    randomGenerateBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setOptionsMazeGenerateType(randomGenerateBtn.getText());
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    xStartPointSpn.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				setOptionsXStartPoint(xStartPointSpn.getSelection());
				
			}
		});
	   
	    yStartPointSpn.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				setOptionsYStartPoint(yStartPointSpn.getSelection());
				
			}
		});
	    
	  //Solution Properties Group
	    Group solutiondGroup = new Group(optionShell, SWT.None);
	    solutiondGroup.setText("Solution");
	    solutiondGroup.setLayout(new GridLayout(2,false));
	    solutiondGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false,2,1));
	    
	    final Button astarSolutionBtn =  new Button(solutiondGroup, SWT.RADIO);
	    astarSolutionBtn.setText("A*");
	    
	    
	    final Button bfsSolutionBtn = new Button(solutiondGroup, SWT.RADIO);
	    bfsSolutionBtn.setText("BFS");
	    
	    if (getOptionsSearchType().equals("BFS"))
	    	bfsSolutionBtn.setSelection(true);
	    else
	    	astarSolutionBtn.setSelection(true);
	    
	    final Group astarHeuristicGrp = new Group(solutiondGroup, SWT.None);
	    astarHeuristicGrp.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false,2,1));
	    astarHeuristicGrp.setText("A* Heuristic");
	    astarHeuristicGrp.setLayout(new RowLayout(SWT.HORIZONTAL/*.VERTICAL*/));
	    
	    
	    
	    final Button airHeuristicBtn =  new Button(astarHeuristicGrp, SWT.RADIO);
	    airHeuristicBtn.setText("Air Distance");
	   
	    
	    final Button manhattanHeuristicBtn = new Button(astarHeuristicGrp, SWT.RADIO);
	    manhattanHeuristicBtn.setText("Manhattan Distance");
	    
	    if (getOptionsHeuristic().equals("AIR"))
	    	 airHeuristicBtn.setSelection(true);
	    else
	    	manhattanHeuristicBtn.setSelection(true);
	    
	    if (bfsSolutionBtn.getSelection()){
	    	astarHeuristicGrp.setEnabled(false);
		    airHeuristicBtn.setEnabled(false);
		    manhattanHeuristicBtn.setEnabled(false);
	    }
	    	
	    
	    bfsSolutionBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setOptionsSearchType("BFS");
				astarHeuristicGrp.setEnabled(false);
			    airHeuristicBtn.setEnabled(false);
			    manhattanHeuristicBtn.setEnabled(false);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    astarSolutionBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setOptionsSearchType("ASTAR");
				astarHeuristicGrp.setEnabled(true);
			    airHeuristicBtn.setEnabled(true);
			    manhattanHeuristicBtn.setEnabled(true);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    airHeuristicBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setOptionsHeuristic("AIR");
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    //MANHATTAN
	    manhattanHeuristicBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setOptionsHeuristic("MANHATTAN");
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    
	    Button optionOKButton = new Button(optionShell, SWT.PUSH);
	    optionOKButton.setText("OK");
	    optionOKButton.setLayoutData(new GridData(SWT.None, SWT.None, false, false, 1, 1));
	    
	    Button optionCancleButton = new Button(optionShell, SWT.PUSH);
	    optionCancleButton.setText("Cancle");
	    optionCancleButton.setLayoutData(new GridData(SWT.None, SWT.None, false, false, 1, 1));
	    
	    
	    
	    optionCancleButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				optionShell.setVisible(false);
				shell.setEnabled(true);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	  //Close optionShell with X-RED Button
	    optionShell.addListener(SWT.Close, new Listener()
	    {
	        public void handleEvent(Event event)
	        {
	        	event.doit = false;
	        	optionShell.setVisible(false);
	        	shell.setEnabled(true);
	        }
	    });
	    
	    optionOKButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setOptions();
				Command command1 = view.userCommands.get("setProperties");
				view.commandQueue.add(command1);
				view.Notify("Command " + view.runOptions); 
				
				uploadNewMaze();
				
				optionShell.setVisible(false);
				shell.setVisible(false);
				
				shell = new Shell();
				initWidgets();
				shell.open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	  
	}
	
	
	

	public void displaySolution(Solution s) {
		
		maze.draw(s, false);
				
	}

	
	public void displayHint(Solution s) {
		
		maze.draw(s, true);
	}

//	public void checkWin(int x , int y){
//		Maze maze =new Maze();
//		if(x==maze.getCols()-1 && y==maze.getRows()-1)
//			new WinnerPage("winner" , 270 , 240 , getDisplay()).run();
//	}

	
	public boolean CheckMotion(int CurrentX,int CurrentY,int nextX,int nextY){ 
		 		boolean flag=true; 
		 		//System.out.println(); 
		 		if(nextX<0 || nextY<0 || nextX==mazeData.getCols() || nextY==mazeData.getRows()) 
		 			return false; 
		 		if(!(nextX!=CurrentX &&CurrentY!=nextY)){ 
		 			if(CurrentX+1==nextX) 
		 				flag=flag&&(!mazeData.getCell(CurrentY, CurrentX).getHasRightWall() == true); 
		 			if(CurrentX-1==nextX) 
		 				flag=flag&&(!mazeData.getCell(nextY, nextX).getHasRightWall()== true); 
		 			if(CurrentY+1==nextY) 
		 				flag=flag&&(!mazeData.getCell(CurrentY, CurrentX).getHasBottomWall()== true); 
		 			if(CurrentY-1==nextY) 
		 				flag=flag&&(!mazeData.getCell(nextY, nextX).getHasBottomWall()== true); 
		 			return flag; 
		 		}else{ 
		 			if (Diagnoal==true){ 
		 				int col=CurrentX; 
		 				int row=CurrentY; 
		 				//right-up 
		 				if(CurrentX+1==nextX && CurrentY-1==nextY){								  	 
		 					if(mazeData.getCell(row-1, col).getHasRightWall()== true &&      // |_	 
		 							mazeData.getCell(row-1, col+1).getHasBottomWall()== true)//. 
		 						flag=false; 
		 					if(mazeData.getCell(row, col).getHasRightWall()== true &&			//  | 
		 							mazeData.getCell(row-1, col).getHasRightWall()== true)		// .| 
		 						flag=false; 
		 					if(mazeData.getCell(row-1, col).getHasBottomWall()== true &&			//_ _ 
		 							mazeData.getCell(row-1, col+1).getHasBottomWall()== true)   //. 
		 						flag=false; 
		 					if(mazeData.getCell(row-1, col).getHasBottomWall()== true &&   ///corner like ._| 
		 							mazeData.getCell(row, col).getHasRightWall()== true) 
		 						flag=false; 
		 					return flag; 
		 				} 
		 				//right-down 
		 				if(CurrentX+1==nextX && CurrentY+1==nextY){								  	 
		 					if(mazeData.getCell(row+1, col).getHasRightWall()== true &&    //. 
		 							mazeData.getCell(row, col+1).getHasBottomWall()== true)// |- 
		 						flag=false; 
		 					if(mazeData.getCell(row, col).getHasRightWall()== true &&			// . | 
		 							mazeData.getCell(row+1, col).getHasRightWall()== true )		//   | 
		 						flag=false; 
		 					if(mazeData.getCell(row, col).getHasBottomWall()== true &&			//. 
		 							mazeData.getCell(row, col+1).getHasBottomWall()== true)   //-- 
		 						flag=false; 
		 					if(mazeData.getCell(row, col).getHasBottomWall()== true&&   ///corner like ._| 
		 							mazeData.getCell(row, col).getHasRightWall()== true) 
		 						flag=false; 
		 					return flag; 
		 				} 
		 				//left-down 
		 				if(CurrentX-1==nextX && CurrentY+1==nextY){ 									  	 
		 					if(mazeData.getCell(row+1, col-1).getHasRightWall()== true &&    //   . 
		 							mazeData.getCell(row, col-1).getHasBottomWall()== true)  // -| 
		 						flag=false; 
		 					if(mazeData.getCell(row, col-1).getHasRightWall()== true&&			//   |. 
		 							mazeData.getCell(row+1, col-1).getHasRightWall()== true)	//   | 
		 						flag=false; 
		 					if(mazeData.getCell(row, col).getHasBottomWall()== true&&			// . 
		 							mazeData.getCell(row, col-1).getHasBottomWall()== true)     //-- 
		 						flag=false; 
		 					if(mazeData.getCell(row, col).getHasBottomWall()== true &&   ///corner like ._| 
		 							mazeData.getCell(row, col-1).getHasRightWall()== true) 
		 						flag=false; 
		 					return flag; 
		 				} 
		 				//left-up 
		 				if(CurrentX-1==nextX && CurrentY-1==nextY){								  	 
		 					if(mazeData.getCell(row-1, col-1).getHasRightWall()== true &&      // _|	 
		 							mazeData.getCell(row-1, col-1).getHasBottomWall()== true )  //   . 
		 						flag=false; 
		 					if(mazeData.getCell(row, col-1).getHasRightWall()== true &&				//  | 
		 							mazeData.getCell(row-1, col-1).getHasRightWall()== true )		//  |. 
		 						flag=false; 
	     					if(mazeData.getCell(row-1, col).getHasBottomWall()== true &&			//_ _ 
		 							mazeData.getCell(row-1, col-1).getHasBottomWall()== true )   //  . 
		 						flag=false; 
		 					if(mazeData.getCell(row-1, col).getHasBottomWall()== true &&   ///corner like ._| 
		 							mazeData.getCell(row, col-1).getHasRightWall()== true ) 
	 						flag=false; 
		 					return flag; 
		 				} 
		 			}else 
		 				return false; 
		 		} 
		 		return false; 
		 
		 
		 	} 

}
