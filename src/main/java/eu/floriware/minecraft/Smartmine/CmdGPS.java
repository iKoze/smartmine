package eu.floriware.minecraft.Smartmine;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class CmdGPS extends BasicProg implements Listener
{
	Player target;
	Location lastLoc;
	boolean output, compass;
	Server server;
	public CmdGPS(Plugin plugin, Player player, String n, String arg, boolean s)
	{
		super(plugin, player, n, arg, s);
		smartmine.getServer().getPluginManager().registerEvents(this, smartmine);
	}
	
	public void handleCmd()
	{
		server = smartmine.getServer();
		target = Spieler;
		String dest = getArgBySwitch("-t");
		compass = isSet("-c");
		output = isSet("-s");
		if(isSet("-h"))
		{
			printUsage();
		}
		if(show == true)
		{
			show = false;
			print(ChatColor.RED+"Show ist für dieses Programm nicht verfügbar!");
		}
		print(ChatColor.GREEN+"GPS auf Ziel "+setTarget(dest)+" aktiviert!");
	}
	
	public boolean handleNextCmd (String cmd)
	{
		if(isSet(":c",cmd))
		{
			if(compass)
			{
				compass=false;
				print(ChatColor.YELLOW+"Compass off");
			}
			else
			{
				compass=true;
				print(ChatColor.YELLOW+"Compass on");
			}
			return true;
		}
		if(isSet(":s",cmd))
		{
			if(output)
			{
				output=false;
				print(ChatColor.YELLOW+"Show coords off");
			}
			else
			{
				output=true;
				print(ChatColor.YELLOW+"Show coords on");
			}
			return true;
		}
		if(isSet(":h",cmd))
		{
			printLiveUsage();
			return true;
		}
		String newTarget = getArgBySwitch(":t", cmd);
		if(newTarget.equals("")==false)
		{
			print(ChatColor.GREEN+"GPS auf neues Ziel "+setTarget(newTarget)+ " umgestellt!");
			return true;
		}
		return false;
	}
	
	public void handleQuit()
	{
			target = null;
			print(ChatColor.GREEN+"GPS beendet.");
	}
	
	private String setTarget(String newTarget)
	{
		target = server.getPlayerExact(newTarget);

		if(target == null)
		{
			target = Spieler;
		}
		lastLoc = target.getEyeLocation();
		target.sendMessage(ChatColor.YELLOW+"[SM] "+Spieler.getDisplayName()+" hat dich mit seinem GPS erfasst!");
		return target.getDisplayName();
	}
	
	//@SuppressWarnings("unused")
	@EventHandler
	private void onPlayerMove(PlayerMoveEvent event)
	{
		if(event.getPlayer().equals(target))
		{
			Location loc = target.getEyeLocation();
			// Nur Neu ausgeben, wenn Sich die Position mindestens um 1 geändert hat.
			if (
					(int)loc.getX() != (int)lastLoc.getX() ||
					(int)loc.getY() != (int)lastLoc.getY() ||
					(int)loc.getZ() != (int)lastLoc.getZ()
				)
			{
				String world = loc.getWorld().getName();
				int x = (int)loc.getX();
				int y = (int)loc.getY();
				int z = (int)loc.getZ();
				if(output)
				{
					print(""+ChatColor.GRAY+x+","+y+","+z+" "+world);
				}
				if(compass)
				{
					Spieler.setCompassTarget(loc);
				}
				lastLoc = loc;
			}
		}
	}
	
	public void printUsage()
	{
		String u = ChatColor.GRAY+"Usage:\n" +
				"no args : Sets GPS to yourself.\n" +
				"-t player : Sets GPS' target to player.\n" +
				"-s : Enables Messages\n" +
				"-c : Sets Compass to target\n" +
				"-h : Prints this help message.\n";
		print(u);
	}
	
	public void printLiveUsage()
	{
		String u = ChatColor.GRAY+"Usage:\n" +
				":q : quit programm\n" +
				":t player : Sets GPS' target to player.\n" +
				":s : toggle Messages\n" +
				":c : toggle Compass\n" +
				":h : Prints this help message.\n";
		print(u);
	}

}
