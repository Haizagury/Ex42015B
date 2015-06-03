/**
 * 
 */
package view;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerator.Cell;
import algorithms.mazeGenerator.Maze;
import algorithms.search.Solution;

/**
 * @author HFL
 *
 */
public class MazeDisplay extends Canvas {

	Maze mazeData = null;
	boolean onlyShowHint = true;
	Solution mazeSolution;
	boolean Moved = false;
	 public GameCharecter gC;
	 MazeDisplay maze;
	 boolean Diagnoal = false;

	public Maze getMazeData() {
		return mazeData;
	}

	public void setMazeData(Maze mazeData) {
		this.mazeData = mazeData;
	}

	Timer timer;
	TimerTask myTask;
	GameCharecter gameCharecter;

	public MazeDisplay(Composite parent, int style/* ,Maze m */) {
		super(parent, style);

		// this.mazeData = m;
	}

	public void startMazeDisplay() {
		setBackground(new Color(null, 255, 255, 255));

		Cell c = new Cell(mazeData.getStartState());
		gameCharecter = new GameCharecter(c.getCol(), c.getRow());

		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {

				e.gc.setForeground(new Color(null, 0, 0, 0));
				e.gc.setBackground(new Color(null, 0, 0, 0));

				int width = getSize().x;
				int height = getSize().y;

				int w = width / mazeData.getCols();
				int h = height / mazeData.getRows();

				gameCharecter.x = gameCharecter.x * w;
				gameCharecter.y = gameCharecter.y * h;

				for (int i = 0; i < mazeData.getRows(); i++)
					for (int j = 0; j < mazeData.getCols(); j++) {

						int x = j * w;
						int y = i * h;
						Cell c = mazeData.getCell(i, j);

						if (mazeSolution != null && mazeSolution.contains(c)) {

							if (onlyShowHint) {
								Cell charCell = new Cell(
										mazeData.getStartState());

								Cell cCameFrom = fromStringToCell(c
										.getCameFrom());
								if (cCameFrom.getCol() == charCell.getCol()
										&& cCameFrom.getRow() == charCell.getRow()) {

									new GameCharecter(c.getCol(), c.getRow())
											.paint(e, w, h, x, y);
								}

							} else {
								new GameCharecter(c.getCol(), c.getRow())
										.paint(e, w, h, x, y);
							}
						}
						if (c.getHasTopWall()) {
							e.gc.drawLine(x, y, x + w, y);
						}
						if (c.getHasBottomWall()) {
							e.gc.drawLine(x, y + h, x + w, y + h);
						}
						if (c.getHasLeftWall()) {
							e.gc.drawLine(x, y, x, y + h);
						}
						if (c.getHasRightWall()) {
							e.gc.drawLine(x + w, y, x + w, y + h);
						}

					}

				gameCharecter.paint(e, w, h);
			}
		});

	}

	
	
	
	public void start() {
		myTask = new TimerTask() {

			@Override
			public void run() {

				getDisplay().syncExec(new Runnable() {

					@Override
					public void run() {
						Random r = new Random();
						gameCharecter.x = r.nextInt(500);
						gameCharecter.y = r.nextInt(500);
						redraw();
					}
				});
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(myTask, 0, 500);
	}

	public void move(int x, int y) {

		gameCharecter.x = y;
		gameCharecter.y = x;
		redraw();

	}

	public void draw(Solution s, boolean onlyHint) {
		// printSolution = true;
		mazeSolution = s;
		//ArrayList<GameCharecter> x = new ArrayList<>();

		// GameCharecter gameChar new GameCharecter(x, y);
		onlyShowHint = onlyHint;
		redraw();
	}

	public void stop() {
		myTask.cancel();
		timer.cancel();
	}

	private Cell fromStringToCell(String s) {
		String[] strXY = s.split(",");
		int x = Integer.parseInt(strXY[0]);
		int y = Integer.parseInt(strXY[1]);

		return new Cell(x, y);
	}

	public void checkWin(int x , int y){
		Maze maze =new Maze();
		if(x==maze.getCols()-1 && y==maze.getRows()-1)
			new WinnerPage("winner" , 270 , 240 , getDisplay()).run();
	}
	
	public void CharMoved(final int pos){			//1 for Right, 2 for Up, 3 for Left, 4 for Down 
		 											//5 for down-left,6 for down-Right,7 for up-left,8 for up-right 
		 		Moved=true; 
		 		getDisplay().syncExec(new Runnable() { 
		 			@Override 
		 			public void run() { 
		     			switch(pos){ 
		     			case 1: gC.setX((gC.getX()+1));		//Right 
		     					break; 
		     			case 2:	gC.setY((gC.getY()-1));		//Up 
		     					break; 
		     			case 3:	gC.setX((gC.getX()-1));		//Left 
		 						break; 
		     			case 4:	gC.setY((gC.getY()+1));		//Down 
		 						break; 
		     			case 5:	gC.setY((gC.getY()+1));		//down-left 
		     			        gC.setX((gC.getX()-1)); 
		 						break; 
		     			case 6:	gC.setY((gC.getY()+1));		//down-Right 
		     			        gC.setX((gC.getX()+1)); 
		 						break; 
		     			case 7:	gC.setY((gC.getY()-1));		//up-left 
		     			        gC.setX((gC.getX()-1)); 
		 						break; 
		     			case 8:	gC.setY((gC.getY()-1));		//up-right 
		     			        gC.setX((gC.getX()+1)); 
		 						break; 
		     			} 
		     		   redraw(); 
		 			} 
		 		}); 
		 	 
	
	
	  maze.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {

				if(arg0.keyCode==16777220){
					int currentX = maze.gameCharecter.getX();
					int currentY = maze.gameCharecter.getY();
					if(CheckMotion(currentX, currentY, currentX+1, currentY)){
						maze.CharMoved(1);
						maze.checkWin(currentX+1 , currentY);
					}	
				}
				
				if(arg0.keyCode==16777219){//left
					int currentX = maze.gameCharecter.getX();
					int currentY = maze.gameCharecter.getY();
					if(CheckMotion(currentX, currentY, currentX-1, currentY)){
						maze.CharMoved(3);
						maze.checkWin(currentX-1 , currentY);
					}	
				}
				
				if(arg0.keyCode==16777217){//up
					int currentX = maze.gameCharecter.getX();
					int currentY = maze.gameCharecter.getY();
					if(CheckMotion(currentX, currentY, currentX, currentY-1)){
						maze.CharMoved(2);
						maze.checkWin(currentX , currentY-1);
					}	
				}
				
				if(arg0.keyCode==16777219){//down
					int currentX = maze.gameCharecter.getX();
					int currentY = maze.gameCharecter.getY();
					if(CheckMotion(currentX, currentY, currentX, currentY+1)){
						maze.CharMoved(4);
						maze.checkWin(currentX , currentY+1);
					}	
				}
			}
		});
	}

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
