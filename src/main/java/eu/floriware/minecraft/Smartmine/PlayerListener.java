package eu.floriware.minecraft.Smartmine;

import java.util.Collection;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class PlayerListener implements Listener
{
	private Logger log;
	private Plugin smartmine;
	private Prevent0r prevent0r;
	private Commander commander;
	private Onffline onffline;
	
	public PlayerListener (Smartmine plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		log = plugin.log;
		smartmine = plugin;
		commander = new Commander(smartmine);
		onffline = new Onffline(smartmine);
	}
	
	//
	// Player Login und Logout
	//
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		//smartmine.reloadConfig();
		prevent0r = new Prevent0r(smartmine);
		// Spieler loggt sich ein
		Player Spieler = event.getPlayer();
		
		// Prevent0r handling
		if(prevent0r.isAllowed(Spieler) == false)
		{
			event.setJoinMessage(ChatColor.GRAY+"Prevent0r prevented login for "+Spieler.getDisplayName());
			Spieler.kickPlayer(prevent0r.getKickMessage(Spieler));
		}
		else
		{
			// Player finally logged in
			onffline.setOnline(Spieler);
			log.info("[SM] Player " + Spieler.getName() + " logged in");
			
			// MOTD Senden
			new CmdMotd(smartmine,Spieler,"","",false);
			
			// Prevent0r Message
			if(prevent0r.isOn(Spieler) == true)
			{
				Spieler.sendMessage(prevent0r.getOkMessage(Spieler));
			}
			else
			{
				Spieler.sendMessage(prevent0r.getErrMessage(Spieler));
			}
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		// Spieler loggt sich aus
		Player Spieler = event.getPlayer();
		onffline.setOffline(Spieler);
		log.info("[SM] Player " + Spieler.getName() + " logged out");
	}
	
	//
	// Player Chat
	//
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event)
	{
		// Commander einbinden
		event.setCancelled(commander.handleEvent(event));
	}
	
	//
	// Sensenmann Plugin (11.9.2012)
	//
	@ EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		if(smartmine.getConfig().getBoolean("sensenmann.enabled"))
		{
			Player player = event.getPlayer();
			ItemStack sense = new ItemStack(Material.DIAMOND_HOE, 1);
			String message = smartmine.getConfig().getString("sensenmann.message");
			if (!message.equalsIgnoreCase(""))
			{
				message = message.replace("%u", player.getDisplayName());
			}
			
			// Sensenmann Finden
			Collection<? extends Player> players = smartmine.getServer().getOnlinePlayers();
			for(Player p: players)
			{
				if(! p.getInventory().getItemInMainHand().isSimilar(sense)) continue;
				event.setRespawnLocation(p.getLocation());
				message = message.replace("%s", p.getDisplayName());
				smartmine.getServer().broadcastMessage(message);
			}
		}
	}
	// Erweiterung (25.11.2012)
	@EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent evt)
	{
		if(smartmine.getConfig().getBoolean("sensenmann.enabled"))
		{
			
			if(evt.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_HOE)
			{
				//maximal distance between player and thunder is 200 blocks
				evt.getPlayer().getWorld().strikeLightning(evt.getPlayer().getTargetBlock(null, 200).getLocation());
			}
		}
	}
}
