package cpm.module.model.game;

public class GhostTypeCell extends Cell {

	public GhostTypeCell(int code) {
		super(code);
		if (code != Cell.GHOST_TYPE_LEADER)
			setName("ghostType" + code);
		if (code == Cell.GHOST_TYPE_LEADER) {
			setName("leaderGhost");
		}
	}

}
