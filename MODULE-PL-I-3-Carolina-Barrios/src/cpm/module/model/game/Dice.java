package cpm.module.model.game;

public class Dice {

	public static int toss() {
		return ((int) ((Math.random() * 2) + 1));
	}
}
