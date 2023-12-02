package eu.floriware.minecraft.Smartmine;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdPanicRoom extends BasicProg
{
	String[] room; // y
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
		 * [0] ist die Höhe des Blocks unter dem Spieler.
		 * Blöcke werden Reihenweise angegeben und mit \n in die nächste Zeile geschrieben.
		 * 
		 * Blöcke:
		 * S = Stone
		 * A = Air
		 * D = Door
		 * M = Melon
		 * T = Torch
		 */
		
		assoc = new Association(6, ""); // Max. 5 Block-ID's; "" = leer
		
		// Datenwerte
		assoc.newAssociation("G", Material.GLASS.name());
		assoc.newAssociation("A", Material.AIR.name());
		assoc.newAssociation("S", Material.STONE.name());
		assoc.newAssociation("M", Material.MELON.name());
		assoc.newAssociation("T", Material.TORCH.name());
		assoc.newAssociation("C", Material.CHEST.name());
		//Fußboden
		room = new String [4]; // höhe
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
			// Startwert für den Raum
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
		
		for(int y = 0; y < room.length; y++)
		{
			String lines [] = room[y].split("\n");
			for (int z = 0; z < width; z++)
			{
				String column [] = lines[z].split(" ");
				for (int x = 0; x < width; x++)
				{
					String name = assoc.getContentByIdentifier(column[x]);
					loc.setX(lx + x);
					loc.setY(ly + y);
					loc.setZ(lz + z);
					Block b = loc.getWorld().getBlockAt(loc);
					if(b.getType().equals(Material.BEDROCK) == false)
					{
						b.setType(Material.getMaterial(name));
					}
				}
			}
		}
	}
}
