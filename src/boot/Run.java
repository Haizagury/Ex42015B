package boot;

import model.MyModel;
import presenter.Presnter;
import algorithms.mazeGenerator.Cell;
import algorithms.mazeGenerator.DFSMazeGenerator;
import algorithms.mazeGenerator.Maze;
import algorithms.mazeGenerator.MazeGenerator;
import view.MazeWindow;
import view.MyGuiView;
import view.MyView;

public class Run {

	public static void main(String[] args) {
		
		/*Maze m = new Maze();
		MazeGenerator mg = new DFSMazeGenerator();
		m = mg.generateMaze(10, 10, new Cell(2,4));
		MazeWindow mw = new MazeWindow("maze", 600, 600);
		mw.setMazeData(m);
		mw.run();*/
		
		MyGuiView myGuiView = new MyGuiView();
		MyModel myModel = new MyModel();
		Presnter presnter = new Presnter(myGuiView, myModel);
		myGuiView.addObserver(presnter);
		myModel.addObserver(presnter);
		myGuiView.start();
		
		
		/*
		MyView myView = new MyView(null, null);
		MyModel myModel = new MyModel();
		Presnter presnter = new Presnter(myView , myModel);
		myView.addObserver(presnter);
		myModel.addObserver(presnter);	
		myView.start();
		 */
		

	}

}
