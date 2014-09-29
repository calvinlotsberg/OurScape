package com.rs.tools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.rs.cache.Cache;
import com.rs.utils.Utils;

public class ItemBonusesPacker {

	public static final void main(String[] args) throws IOException {
		log("Packer Started.");
		try {
			Cache.init();
			log("Cache Initiated.");
		}
		catch (IOException e) {
			log("Exception in initializing cache." + e.getMessage());
		}
		DataOutputStream out = new DataOutputStream(new FileOutputStream("data/items/bonuses.ib"));
		for (int itemId = 0; itemId < Utils.getItemDefinitionsSize(); itemId++) {			
			File file = new File("data/items/bonuses/" + itemId + ".txt");			
			if (file.length() != 0) {
				log("Writing Values for item " + itemId);
				out.writeShort(itemId);
				BufferedReader reader = new BufferedReader(new FileReader(file));				
				reader.readLine(); // att bonuses
				reader.readLine(); // stab
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // slash
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // crush
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // magic
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // range
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // def bonuses
				reader.readLine(); // stab
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // slash
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // crush
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // magic
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // range
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // summoning
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // Damage absorption
				reader.readLine(); // melee
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // magic
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // range
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // Other bonuses
				reader.readLine(); // strength
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // ranged strength
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // prayer
				out.writeShort(Integer.valueOf(reader.readLine()));
				reader.readLine(); // magic damage %
				out.writeShort(Integer.valueOf(reader.readLine()));
				if (reader.readLine() != null)
					throw new RuntimeException("Should be null line" + itemId);
			}			
		}
		out.flush();
		out.close();
	}
	
	private static void log(String s) {
		System.out.println(s);
	}

}
