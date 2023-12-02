package eu.floriware.minecraft.Smartmine;

import java.util.Collection;
import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
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
		Collection<? extends Player> players = Bukkit.getOnlinePlayers();
		output = ChatColor.YELLOW +""+ dt+" up  "+players.size()+" users\n";
		output = output + ChatColor.YELLOW+"ALV HEALTH FOOD NAME WORLD x,y,z TOOL\n";
		
		for (Player p: players)
		{
			String playername = p.getDisplayName();
			Location loc = p.getLocation();
			String world = loc.getWorld().getName();
			int x = (int)loc.getX();
			int y = (int)loc.getY();
			int z = (int)loc.getZ();
			String werkzeug = p.getInventory().getItemInMainHand().getType().name();
			werkzeug = werkzeug.toLowerCase();
			double health = p.getHealth();
			double maxHealth = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
			int percentHealth = (int) (health / maxHealth * 100);
			int food = p.getFoodLevel();
			int percentFood = (int) (food / 20.0 * 100);
			boolean dead = p.isDead();
			String ad;
			if (dead == true){ad = ChatColor.GRAY+"D";}else{ad = "A";}
			output = output + ad + " " + percentHealth + "% " + percentFood + "% " + playername + " " + world + " " + x+","+y+","+z + " " + werkzeug + "\n";
		}
		print(output);
	}
	
	

}
