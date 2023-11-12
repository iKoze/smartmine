package eu.floriware.minecraft.Smartmine;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdPanicRoom extends BasicProg
{
	String[] room; // y
	int [][][] blockId; // x,y,z
	Association assoc;
	int lx,ly,lz;
	Location loc;
	public CmdPanicRoom(Plugin plugin, Player player, String n, String arg, boolean s)
	{
		super(plugin, player, n, arg, s);
	}
	
	private void draw()
	{
		/* 
		 * Hier wird der Panik-Raum gezeichnet
		 * Die Felder des Arrays room entsprechen dem Y-Layer.
		 * [0] ist die H�he des Blocks unter dem Spieler.
		 * Bl�cke werden Reihenweise angegeben und mit \n in die n�chste Zeile geschrieben.
		 * 
		 * Bl�cke:
		 * S = Stone
		 * A = Air
		 * D = Door
		 * M = Melon
		 * T = Torch
		 */
		
		assoc = new Association(6, ""); // Max. 5 Block-ID's; "" = leer
		
		// Datenwerte
		assoc.newAssociation("G", "20");
		assoc.newAssociation("A", "0");
		assoc.newAssociation("S", "1");
		assoc.newAssociation("M", "103");
		assoc.newAssociation("T", "50");
		assoc.newAssociation("C", "54");
		//Fu�boden
		room = new String [4]; // h�he
		room[0] = "" +
				"G G G G G\n" +
				"G G G G G\n" +
				"G G G G G\n" +
				"G G G G G\n" +
				"G G G G G\n" +
				"";
		room[1] = "" +
				"G G S G G\n" +
				"G M C C G\n" +
				"S A A A S\n" +
				"G A A A G\n" +
				"G G S G G\n" +
				"";
		room[2] = "" +
				"G G S G G\n" +
				"G A T A G\n" +
				"S A A A S\n" +
				"G A A A G\n" +
				"G G S G G" +
				"";
		//Dach
		room[3] = "" +
				"G G G G G\n" +
				"G G G G G\n" +
				"G G G G G\n" +
				"G G G G G\n" +
				"G G G G G\n" +
				"";
		drawBlocks();
	}
	
	public void handleCmd()
	{
		boolean enabled = smartmine.getConfig().getBoolean("panic-room.enabled");
		int cost = smartmine.getConfig().getInt("panic-room.cost");
		String message = smartmine.getConfig().getString("panic-room.message");
		String failmessage = smartmine.getConfig().getString("panic-room.failmessage");
		if(enabled == false)
		{
			return;
		}
		int level = Spieler.getLevel();
		if(level >= cost)
		{
			// Startwert f�r den Raum
			loc = Spieler.getLocation();
			lx = (int) loc.getX();
			ly = (int) loc.getY();
			lz = (int) loc.getZ();
			lx = lx -2;
			ly = ly -1;
			lz = lz -3;
			
			draw();
			print(message);
			Spieler.setLevel(level - cost);
		}
		else
		{
			print(failmessage);
		}
	}
	
	private void drawBlocks()
	{
		int width = room[0].split("\n").length;
		blockId = new int [width][width][width];
		
		for(int y = 0; y < room.length; y++)
		{
			String lines [] = room[y].split("\n");
			for (int z = 0; z < width; z++)
			{
				String column [] = lines[z].split(" ");
				for (int x = 0; x < width; x++)
				{
					int id = Integer.parseInt(assoc.getContentByIdentifier(column[x]));
					blockId[x][y][z] = id;
					loc.setX(lx + x);
					loc.setY(ly + y);
					loc.setZ(lz + z);
					Block b = loc.getWorld().getBlockAt(loc);
					if(b.getType().equals(Material.BEDROCK) == false)
					{
						b.setTypeId(id);
					}
				}
			}
		}
	}
}
