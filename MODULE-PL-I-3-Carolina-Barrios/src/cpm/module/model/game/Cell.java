package cpm.module.model.game;

/**
 * 
 * This class represents a cell of the board. Has a code indicating the type of
 * cell it is.
 * 
 * @author Carolina Barrios González UO275673
 *
 */
public class Cell {

	public final static int EMPTY_CELL = -1;
	public final static int GHOST_BUSTER_CELL = 100;
	public static final int GHOST_TYPE_LEADER = 10;
	public static final int GHOST_TYPE_0 = 0;
	public static final int GHOST_TYPE_1 = 1;
	public static final int GHOST_TYPE_2 = 3;
	public static final int GHOST_TYPE_3 = 3;
	public static final int GHOST_TYPE_4 = 4;

	private int code;
	private String name;

	public Cell(int code) {
		this.code = code;
		name = "emptyCell";
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

}
