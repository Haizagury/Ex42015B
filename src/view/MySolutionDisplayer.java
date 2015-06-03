package view;

import java.io.PrintStream;

import algorithms.search.Solution;
import algorithms.search.State;

// TODO: Auto-generated Javadoc
/**
 * The Class MySolutionDisplayer implements SolutionDisplayer.
 * @author haizagury and livna haim.
 * @version 1.0.
 * @since 17.05.15 .
 */
public class MySolutionDisplayer implements SolutionDisplayer {
	
	/* (non-Javadoc)
	 * @see view.SolutionDisplayer#solutionDisplayer(algorithms.search.Solution, java.io.PrintStream)
	 */
	@Override
	public void solutionDisplayer(Solution s, PrintStream out) {
		if (s != null){
			for(State st: s){
				out.println(st.getState());
			}
		}
	
		else{
			out.println("not found Solution");
		}
	}

}
