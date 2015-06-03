package view;

import java.io.PrintStream;

import algorithms.mazeGenerator.Cell;
import algorithms.mazeGenerator.Maze;

// TODO: Auto-generated Javadoc
/**
 * The Class MyMazeDisplayer implements MazeDisplayer.
 * @author haizagury and livna haim.
 * @version 1.0.
 * @since 17.05.15 .
 */
public class MyMazeDisplayer implements MazeDisplayer {

	/* (non-Javadoc)
	 * @see view.MazeDisplayer#mazeDisplayer(algorithms.mazeGenerator.Maze, java.io.PrintStream)
	 */
	@Override
	public void mazeDisplayer(Maze m, PrintStream out) {
		
		int cols = m.getCols();
		int rows = m.getRows();

		for (int j = 0; j < cols; j++)
			out.print("__");
		out.println("_");
		
		for (int i = 0; i < rows; i++) {
			out.print("|");
			for (int j = 0; j < cols; j++) {				
				Cell cell =  m.getCell(i, j);
				if (cell.getHasBottomWall())
					out.print("_");
				else
					out.print(" ");
				
				if (cell.getHasRightWall())
					out.print("|");
				else
					out.print(" ");	
								
			}
			out.println();
		}
	}
}
