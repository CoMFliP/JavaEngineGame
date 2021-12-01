package com.comflip.game.lists;

import com.comflip.game.LoaderManager;
import com.comflip.game.lists.sprite.BlackChecker;
import com.comflip.game.lists.sprite.WhiteChecker;

public abstract class Sprites extends LoaderManager {
	protected boolean pickUp;

	protected int idTileBoard;
	public static WhiteChecker whiteChecker;
	public static BlackChecker blackChecker;

	public boolean isPickUp() {
		return pickUp;
	}

	public void setIdTileBoard(int idTileBoard) {
		this.idTileBoard = idTileBoard;
	}
	public int getIdTileBoard() {
		return this.idTileBoard;
	}
}
