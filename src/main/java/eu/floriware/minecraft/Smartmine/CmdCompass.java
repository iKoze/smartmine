package eu.floriware.minecraft.Smartmine;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdCompass extends BasicProg
{
	Server server;
	public CmdCompass(Plugin plugin, Player player, String n, String arg, boolean s)
	{
		super(plugin, player, n, arg, s);
	}
	
	public void handleCmd()
	{
		server = smartmine.getServer();
		Location loc = new Location(Spieler.getWorld(), 0, 0, 0);
		String spielerTarget = getArgBySwitch("-t");
		String coordLocation = getArgBySwitch("-l");
		if (noArgs())
		{
			setTarget(loc);
			return;
		}
		else if (isSet("-h"))
		{
			printUsage();
			return;
		}
		else if(isSet("-g"))
		{
			Location currentTarget = Spieler.getCompassTarget();
			int cx,cy,cz;
			cx = (int) currentTarget.getX();
			cy = (int) currentTarget.getY();
			cz = (int) currentTarget.getZ();
			print(ChatColor.YELLOW+"Compass is currently set to Coordinates "+cx+","+cy+","+cz);
			return;
		}
		else if(isSet("-b"))
		{
			try
			{
				loc = Spieler.getBedSpawnLocation();
				print(ChatColor.GREEN+"Set Compass to your bed.");
				setTarget(loc);
				return;
			}
			catch (Exception e)
			{
				print(ChatColor.RED+"Could not locate your bed!");
				return;
			}
		}
		else if(spielerTarget.equalsIgnoreCase("")==false)
		{
			Player target = null;
			target = server.getPlayerExact(spielerTarget);
			if(target == null)
			{
				print(ChatColor.RED+"Player '"+spielerTarget+"' was not found!");
				return;
			}
			else
			{
				loc = target.getEyeLocation();
				print(ChatColor.GREEN+"Compass set to "+target.getDisplayName()+"'s Location.");
				setTarget(loc);
				return;
			}
		}
		else if(coordLocation.equalsIgnoreCase("")==false)
		{
			int x,y,z;
			x = 0; // Fallback
			y = 0; // Fallback
			z = 0; // Fallback
			String [] coords = coordLocation.split(",");
			if(coords.length == 2)
			{
				// OK (x,z)
				try
				{
					x = Integer.parseInt(coords[0]);
					z = Integer.parseInt(coords[1]);
				}
				catch (Exception e)
				{
					// Fehler
					printUsage();
					return;
				}
			}
			else if (coords.length == 3)
			{
				// OK (x,y,z)
				try
				{
					x = Integer.parseInt(coords[0]);
					y = Integer.parseInt(coords[1]);
					z = Integer.parseInt(coords[2]);
				}
				catch (Exception e)
				{
					// Fehler
					printUsage();
					return;
				}
			}
			else
			{
				// Fehler
				printUsage();
				return;
			}
			loc = new Location(Spieler.getWorld(), x, y, z);
			setTarget(loc);
		}
		else
		{
			printUsage();
			return;
		}
	}
	
	private void setTarget(Location loc)
	{
		Spieler.setCompassTarget(loc);
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		int z = (int) loc.getZ();
		print(ChatColor.GREEN+"Compass is now set to Coordinates "+x+","+y+","+z);
	}
	
	public void printUsage()
	{
		String u = ChatColor.GRAY+"Usage:\n" +
				"no args : Sets Compass to 0,0,0\n" +
				"-b : Sets Compass to your Bed.\n" +
				"-t player : Sets Compass' target to player.\n" +
				"-l x,z : Sets Compass to location x,z (Comma seperated!)\n" +
				"-l x,y,z : Sets Compass to location x,y,z (Comma seperated!)\n" +
				"-g : Prints out the currend Compass target\n" +
				"-h : Prints out this help message.\n";
		print(u);
	}
}
