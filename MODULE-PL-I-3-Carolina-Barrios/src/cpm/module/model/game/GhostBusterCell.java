package cpm.module.model.game;

/**
 * 
 * This class represents a ghostBuster.
 * 
 * @author Carolina Barrios González UO275673
 *
 */
public class GhostBusterCell extends Cell {

	private int ghostLeftInRow;

	public GhostBusterCell(int code, int ghostLeftInRow) {
		super(code);
		this.ghostLeftInRow = ghostLeftInRow;
		setName("ghostBuster");
	}

	public int getGhostLeftInRow() {
		return ghostLeftInRow;
	}

	public void move(int moves) {
		ghostLeftInRow -= moves;
	}

}
