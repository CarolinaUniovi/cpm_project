package cpm.module.game;

import cpm.module.model.book.DiscountType;
import cpm.module.model.game.Board;
import cpm.module.model.game.Cell;
import cpm.module.model.game.Dice;

/**
 * 
 * This class is the one that knows the rules of the game.
 * 
 * @author Carolina Barrios González UO275673
 *
 */
public class Game {

	public static int points;
	private Board board;
	private int roundsLeft;
	private int lastToss;

	public Game() {
		board = new Board();
		points = 0;
		roundsLeft = 7;
		lastToss = 0;
	}

	public Board getBoard() {
		return board;
	}

	public String getImageFor(int row, int column) {
		return board.getBoard().get(row).get(column).getName();
	}

	public int rollDice() {
		if (roundsLeft > 0) {
			roundsLeft--;
			lastToss = Dice.toss();
			return lastToss;
		}
		return 0;
	}

	public int getRoundsLeft() {
		return roundsLeft;
	}

	public void moveTo(int row, int column) {
		board.moveTo(row, column, lastToss);
	}

	public int getLastToss() {
		return lastToss;
	}

	/**
	 * If at least one of each of the ghost had been captured, discount is EXTRA10.
	 * If the leader had also been captured, discount is EXTRA25.
	 * 
	 * @return
	 */
	public DiscountType getDiscount() {
		boolean discount = true;
		int[][] counter = board.getCounter();

		if (counter[Cell.GHOST_TYPE_0][0] < 0 || counter[Cell.GHOST_TYPE_1][0] < 0 || counter[Cell.GHOST_TYPE_2][0] < 0
				|| counter[Cell.GHOST_TYPE_3][0] < 0 || counter[Cell.GHOST_TYPE_4][0] < 0) {
			discount = false;
		}

		if (discount && counter[Cell.GHOST_TYPE_LEADER][0] > 0)
			return DiscountType.EXTRA25;
		if (discount) {
			return DiscountType.EXTRA10;
		}
		return DiscountType.NONE;
	}

}
