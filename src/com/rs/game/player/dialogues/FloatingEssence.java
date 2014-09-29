package com.rs.game.player.dialogues;

public class FloatingEssence extends Dialogue {

	@Override
	public void start() {
		sendDialogue("You obtain 250 rune essence!");
		player.getInventory().addItem(24227, 250);
	}

	@Override
	public void run(int interfaceId, int componentId) {

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}