package eu.floriware.minecraft.Smartmine;


import java.util.logging.Logger;
import org.bukkit.ChatColor;
//import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
//import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Smartmine extends JavaPlugin
{
	public Logger log = Logger.getLogger("Minecraft");
	PlayerListener plistener;
	
	public void onEnable()
	{
		log.info("Smartmine has been enabled!");
		// Player Listener
		plistener = new PlayerListener(this);
	}
	
	public void onDisable()
	{
		Onffline onffline = new Onffline(this);
		onffline.setAllOffline();
		log.info("Smartmine has been disabled!");
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String [] args)
	{
		if(cmd.getName().equalsIgnoreCase("smreloadconf"))
		{
			if(sender.isOp())
			{
				this.reloadConfig();
				sender.sendMessage(ChatColor.GREEN+"Config reloaded.");
			}
			else
			{
				sender.sendMessage(ChatColor.RED+"Only operators can do that!");
			}
			return true;
		}
		/*Player player = null;
		// Überprüfen, ob CommandSender ein Spieler ist
		if (sender instanceof Player)
		{
			player = (Player) sender;
		}
		
		if (cmd.getName().equals("IP"))
		{
			if (player == null)
			{
				sender.sendMessage("this command can only be run by a player");
				return true;
			}
			String ip = player.getAddress().getAddress().toString();
			ip = ip.replace("/", "");
			sender.sendMessage("Deine IP: "+ ChatColor.GREEN + ip);
			return true;
		}
	    if(cmd.getName().equals("ignite"))
	    {
	    	// Setzt Spieler in Brand ;)
	        Player [] players = Bukkit.getOnlinePlayers();
	        for(int i = 0; i < players.length; i++)
	        {
	        	if (players[i].getName().equalsIgnoreCase(args[0]))
	        	{
	        		players[i].setFireTicks(10000);
	        		return true;
	        	}
	        }
	        return false;
	    }
	    if(cmd.getName().equals("DIAMONDS!"))
	    {
	    	log.info("diamond called!");
			PlayerInventory inventar = player.getInventory();
			ItemStack diamonds = new ItemStack(Material.DIAMOND, 64);
			inventar.addItem(diamonds);
			player.sendMessage("Wir geben dir 64 Diamanten");
			return true;
	    }
		if (cmd.getName().equals("logout"))
		{
			if (player == null)
			{
				sender.sendMessage("this command can only be run by a player");
				return true;
			}
			Prevent0r prevent0r = new Prevent0r(this);
			prevent0r.logoutPlayer(player);
			return true;
		}
		if (cmd.getName().equals("HEADSHOT!"))
		{
	        Player [] players = Bukkit.getOnlinePlayers();
	        for(int i = 0; i < players.length; i++)
	        {
	        	if (players[i].getName().equalsIgnoreCase(args[0]))
	        	{
	        		players[i].getWorld().createExplosion(players[i].getLocation(), (float) 4F);
	        		return true;
	        	}
	        }
			return false;
		}*/

		return false;
	}
	
}
