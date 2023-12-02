package eu.floriware.minecraft.Smartmine;

import java.util.Collection;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

//Bed Smartmine Programm
//Betthilfe
//by Florian Schiessl (17.2.2012)

public class CmdBed extends BasicProg
{

	public CmdBed(Plugin plugin, Player player, String n, String arg, boolean s)
	{
		super(plugin, player, n, arg, s);
	}
	
	public void handleCmd()
	{
		String spielerName = Spieler.getDisplayName();
		World w = Spieler.getWorld();
		Collection<? extends Player> players = Spieler.getServer().getOnlinePlayers();
		String output = ChatColor.YELLOW+"Folgende Spieler in Welt "+w.getName()+" schlafen:\n";
		for(Player p: players)
		{
			if(p.getWorld().equals(w))
			{
				if(p.isSleeping())
				{
					output = output + ChatColor.GREEN+p.getDisplayName() + " - schläft.";
				}
				else
				{
					output = output + ChatColor.RED+p.getDisplayName() + " - schläft nicht!";
				}
			}
			if(isSet("-v"))
			{
				if(p.isSleepingIgnored())
				{
					output = output + ChatColor.GREEN + " (IGNORED)";
				}
				else
				{
					output = output + ChatColor.RED + " (NOT IGNORED)";
				}
			}
			output = output + "\n";
		}
		print(output);
		if(Spieler.isSleeping()==true)
		{
			Spieler.sendMessage(ChatColor.GREEN+"Du schläfst... Spieler werden benachrichtigt.");
			for(Player p: players)
			{
				if(p.getWorld().equals(Spieler.getWorld()))
				{
					if(p.isSleeping()==false)
					{
						p.sendMessage(ChatColor.YELLOW+""+spielerName+" schläft und möchte, dass du auch ins Bett gehst!");
					}
				}
			}
		}
	}
}
