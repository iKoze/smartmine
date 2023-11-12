package eu.floriware.minecraft.Smartmine;

import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdW extends BasicProg
{
	public CmdW(Plugin plugin, Player player, String n, String arg, boolean s)
	{
		super(plugin, player, n, arg, s);
	}
	
	public void handleCmd()
	{
		String output;
		Date dt = new Date();
		Player [] players = Bukkit.getOnlinePlayers();
		output = ChatColor.YELLOW +""+ dt+" up  "+players.length+" users\n";
		output = output + ChatColor.YELLOW+"ALV HEALTH FOOD NAME WORLD x,y,z TOOL\n";
		
		for (int i = 0; i < players.length; i++)
		{
			String playername = players[i].getDisplayName();
			Location loc = players[i].getEyeLocation();
			String world = loc.getWorld().getName();
			int x = (int)loc.getX();
			int y = (int)loc.getY();
			int z = (int)loc.getZ();
			String werkzeug = players[i].getItemInHand().getType().name();
			werkzeug = werkzeug.toLowerCase();
			int health = players[i].getHealth();
			int maxHealth = players[i].getMaxHealth();
			int percentHealth = (int) (health / (double)maxHealth * 100);
			int food = players[i].getFoodLevel();
			int percentFood = (int) (food / 20.0 * 100);
			boolean dead = players[i].isDead();
			String ad;
			if (dead == true){ad = ChatColor.GRAY+"D";}else{ad = "A";}
			output = output + ad + " " + percentHealth + "% " + percentFood + "% " + playername + " " + world + " " + x+","+y+","+z + " " + werkzeug + "\n";
		}
		print(output);
	}
	
	

}
