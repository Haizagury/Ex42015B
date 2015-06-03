package model;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Properties.
 * @author haizagury and livna haim
 * @version 1.0
 * @since 17.05.15
 */
public class Properties implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The maze generate type. */
	private String mazeGenerateType;
	
	/** The search type. */
	private String searchType;
	
	/** The heuristic. */
	private String heuristic;
	
	/** The rows. */
	private Integer rows;
	
	/** The cols. */
	private Integer cols;
	
	/** The x start point. */
	private Integer xStartPoint;
	
	/** The y start point. */
	private Integer yStartPoint;
	
	/**
	 * Gets the maze generate type.
	 *
	 * @return the maze generate type
	 */
	public String getMazeGenerateType() {
		return mazeGenerateType;
	}

	/**
	 * Sets the maze generate type.
	 *
	 * @param mazeGenerateType the new maze generate type
	 */
	public void setMazeGenerateType(String mazeGenerateType) {
		this.mazeGenerateType = mazeGenerateType;
	}

	/**
	 * Gets the search type.
	 *
	 * @return the search type
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * Sets the search type.
	 *
	 * @param searchType the new search type
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	 * Gets the heuristic.
	 *
	 * @return the heuristic
	 */
	public String getHeuristic() {
		return heuristic;
	}

	/**
	 * Sets the heuristic.
	 *
	 * @param heuristic the new heuristic
	 */
	public void setHeuristic(String heuristic) {
		this.heuristic = heuristic;
	}
	
	/**
	 * Gets the rows.
	 *
	 * @return the rows
	 */
	public Integer getRows() {
		return rows;
	}

	/**
	 * Sets the rows.
	 *
	 * @param rows the new rows
	 */
	public void setRows(Integer rows) {
		this.rows = rows;
	}

	/**
	 * Gets the cols.
	 *
	 * @return the cols
	 */
	public Integer getCols() {
		return cols;
	}

	/**
	 * Sets the cols.
	 *
	 * @param cols the new cols
	 */
	public void setCols(Integer cols) {
		this.cols = cols;
	}

	/**
	 * Gets the x start point.
	 *
	 * @return the x start point
	 */
	public Integer getXStartPoint() {
		return xStartPoint;
	}


	/**
	 * Sets the x start point.
	 *
	 * @param xStartPoint the new x start point
	 */
	public void setXStartPoint(Integer xStartPoint) {
		this.xStartPoint = xStartPoint;
	}

	/**
	 * Gets the y start point.
	 *
	 * @return the y start point
	 */
	public Integer getYStartPoint() {
		return yStartPoint;
	}


	/**
	 * Sets the y start point.
	 *
	 * @param yStartPoint the new y start point
	 */
	public void setYStartPoint(Integer yStartPoint) {
		this.yStartPoint = yStartPoint;
	}

	/**
	 * Instantiates a new properties.
	 */
	public Properties() { }
}
