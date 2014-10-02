package com.rs.game.player.dialogues;

import com.rs.Settings;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.WorldTile;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;

public class Korasi extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		/*if (Settings.ECONOMY) {
			player.getPackets().sendGameMessage("The Master of Minigame Teleports is in no mood to talk to you.");
			end();
			return;
		}*/
		npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "Hello, I can teleport you to any minigame, would you like to go somewhere?" }, IS_NPC, npcId, 9827);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "Sure, why not." },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 1;
		} else if (stage == 1) {
			sendOptionsDialogue("Where would you like to go?", "Duel Arena",
					"Clan Wars", "Castle Wars", "Dominion Tower", "More Options");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3365, 3275, 0));
			else if (componentId == OPTION_2)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2993, 9679, 0));
			else if (componentId == OPTION_3)
				Magic.sendNormalTeleportSpell(player, 0, 0, CastleWars.LOBBY);
			else if (componentId == OPTION_4)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3366, 3083, 0));
			else if (componentId == OPTION_5) {
				stage = 3;
				sendOptionsDialogue("Where would you like to go?", "Fight Pits", "Fight Caves", "Fight Kiln", "Barrows", "More Options");
			}
		} else if (stage == 3) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4608, 5061, 0));
				player.getControlerManager().startControler("DuelControler");
			} else if (componentId == OPTION_2)
				Magic.sendNormalTeleportSpell(player, 0, 0, FightCaves.OUTSIDE);
			else if (componentId == OPTION_3)
				Magic.sendNormalTeleportSpell(player, 0, 0, FightKiln.OUTSIDE);
			else if (componentId == OPTION_4) 
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3565, 3289, 0));
			else if (componentId == OPTION_5){
				stage = 2;
				sendOptionsDialogue("Where would you like to go?", "Duel Arena",
					"Barrows", "Castle Wars", "Dominion Tower", "More Options");
			}
	}

	private void teleportPlayer(int x, int y, int z) {
		player.setNextWorldTile(new WorldTile(x, y, z));
		player.stopAll();
		player.getControlerManager().startControler("GodWars");
	}

	@Override
	public void finish() {

	}
}