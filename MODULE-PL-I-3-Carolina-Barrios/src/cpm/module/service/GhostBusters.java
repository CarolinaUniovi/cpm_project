package cpm.module.service;

import java.util.List;

import cpm.module.game.Game;
import cpm.module.model.book.DiscountType;
import cpm.module.model.game.Cell;

public class GhostBusters {

	private Game game;

	public GhostBusters() {
		init();
	}

	public void init() {
		game = new Game();
	}

	public int rollDice() {
		return game.rollDice();
	}

	public List<List<Cell>> getBoard() {
		return game.getBoard().getBoard();
	}

	public int getRoundsLeft() {
		return game.getRoundsLeft();
	}

	public int getLastToss() {
		return game.getLastToss();
	}

	public void moveTo(int row, int column) {
		game.moveTo(row, column);
	}

	public DiscountType getDiscount() {
		return game.getDiscount();
	}

}
