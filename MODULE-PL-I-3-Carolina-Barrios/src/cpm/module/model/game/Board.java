package cpm.module.model.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * This class is in charge of setting the board and moving the ghostBusters as
 * the user plays.
 * 
 * @author Carolina Barrios González UO275673
 *
 */
public class Board {

	private List<List<Cell>> board;
	private List<Cell> gang;
	private int[][] counter = new int[11][3];

	public Board() {
		initBoard();
		initGang();
		initGhostBusters();
		setGangInBoard();
		for (int i = 0; i < counter.length; i++) {
			Arrays.fill(counter[i], -1);
		}
	}

	private void setGangInBoard() {
		int blankSpaces = 2;
		for (int i = 1; i < board.size() - 1; i++) {
			for (int j = 0; j < board.get(i).size(); j++) {
				if (j >= blankSpaces && j < board.get(i).size() - blankSpaces) {
					int position = ((int) (Math.random() * 15));
					if (gang.get(position).getCode() != Cell.EMPTY_CELL) {
						board.get(i).set(j, gang.get(position));
						gang.set(position, new Cell(Cell.EMPTY_CELL));
					} else
						j--;
				}

			}
			blankSpaces--;
		}
	}

	private void initGhostBusters() {
		int ghostLeft = 1;
		for (int i = 0; i < 7; i++) {
			board.get(4).add(new GhostBusterCell(Cell.GHOST_BUSTER_CELL, ghostLeft));
			if (i < 3)
				ghostLeft++;
			else
				ghostLeft--;
		}
	}

	private void initGang() {
		gang = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			gang.add(new GhostTypeCell(i % 5));
		}
	}

	private void initBoard() {
		board = new ArrayList<>();
		List<Cell> leader = getEmptyListOf(7);
		leader.set(3, new GhostTypeCell(Cell.GHOST_TYPE_LEADER));
		board.add(leader);
		board.add(getEmptyListOf(7));
		board.add(getEmptyListOf(7));
		board.add(getEmptyListOf(7));
		board.add(new ArrayList<>());
	}

	private List<Cell> getEmptyListOf(int size) {
		List<Cell> list = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			list.add(new Cell(Cell.EMPTY_CELL));
		}
		return list;
	}

	public List<List<Cell>> getBoard() {
		return board;
	}

	public int[][] getCounter() {
		return counter;
	}

	/**
	 * Moves the ghostBuster in the column to the specified position, removing the
	 * ghost. The last ghost eliminated is saved in the counter.
	 * 
	 * @param row
	 * @param column
	 * @param moves
	 */
	public void moveTo(int row, int column, int moves) {
		if (board.get(row + moves).get(column).getCode() == Cell.GHOST_BUSTER_CELL) {
			GhostBusterCell ghostBuster = (GhostBusterCell) board.get(row + moves).get(column);
			GhostTypeCell ghost = (GhostTypeCell) board.get(row).get(column);
			for (int i = 0; i < counter[ghost.getCode()].length; i++) {
				if (counter[ghost.getCode()][i] < 0) {
					counter[ghost.getCode()][i] = 1;
					break;
				}
			}
			board.get(row + moves).set(column, new Cell(Cell.EMPTY_CELL));
			board.get(row).set(column,
					new GhostBusterCell(Cell.GHOST_BUSTER_CELL, ghostBuster.getGhostLeftInRow() - moves));
		}
		System.out.println();
	}
}
