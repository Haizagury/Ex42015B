package view;

import java.io.PrintStream;

import algorithms.search.Solution;

// TODO: Auto-generated Javadoc
/**
 * The Interface SolutionDisplayer.
 * @author haizagury and livna haim.
 * @version 1.0
 * @since 17.05.15 .
 */
public interface SolutionDisplayer {

	/**
	 * Solution displayer.
	 *
	 * @param s the s
	 * @param out the out
	 */
	public void solutionDisplayer(Solution s , PrintStream out);
}
