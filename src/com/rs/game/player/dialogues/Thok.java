package com.rs.game.player.dialogues;

import com.rs.Settings;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.WorldTile;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;

public class Thok extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		/*if (Settings.ECONOMY) {
			player.getPackets().sendGameMessage("The Master of Bossing Teleports is in no mood to talk to you.");
			end();
			return;
		}*/
		npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,"Hello, I can teleport you to any boss, would you like to go somewhere?" }, IS_NPC, npcId, 9827);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "Sure, why not." },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 1;
		} else if (stage == 1) {
			sendOptionsDialogue("Where would you like to go?", "Bandos", "Armadyl", "Saradomin", "Zamorak", "More Options");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2864, 5354, 2));
				player.getControlerManager().startControler("GodWars");
			} else if (componentId == OPTION_2) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2839, 5296, 2));
				player.getControlerManager().startControler("GodWars");
			} else if (componentId == OPTION_3) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2907, 5265, 2));
				player.getControlerManager().startControler("GodWars");
			} else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2925, 5331, 2));
				player.getControlerManager().startControler("GodWars");
			} else if (componentId == OPTION_5) {
				stage = 3;
				sendOptionsDialogue("Where would you like to go?", "Nex", "Corporeal Beast", "Tormented Demons", "King Black Dragon", "More Options");
			}
		} else if (stage == 3) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2911, 5205, 0));
			} else if (componentId == OPTION_2)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2970, 4384, 0));
			else if (componentId == OPTION_3)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2604, 5748, 0));
			else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2273, 4682, 0));
			} else if (componentId == OPTION_5) {
				stage = 4;
				sendOptionsDialogue("Where would you like to go?", "Kalphite Queen", "Dagannoth kings", "More Options");
			}
		} } else if (stage == 4) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3508, 9493, 0));
				player.getControlerManager().startControler("DuelControler");
			} else if (componentId == OPTION_2)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2900, 4449, 0));
			else if (componentId == OPTION_3) {
				stage = 4;
				sendOptionsDialogue("Where would you like to go?", "Bandos", "Armadyl", "Saradomin", "Zamorak", "More Options");
			}
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