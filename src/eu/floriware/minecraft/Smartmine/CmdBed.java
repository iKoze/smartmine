package eu.floriware.minecraft.Smartmine;

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
		Player [] players = Spieler.getServer().getOnlinePlayers();
		String output = ChatColor.YELLOW+"Folgende Spieler in Welt "+w.getName()+" schlafen:\n";
		for(int i = 0; i < players.length; i++)
		{
			if(players[i].getWorld().equals(w))
			{
				if(players[i].isSleeping())
				{
					output = output + ChatColor.GREEN+players[i].getDisplayName() + " - schläft.";
				}
				else
				{
					output = output + ChatColor.RED+players[i].getDisplayName() + " - schläft nicht!";
				}
			}
			if(isSet("-v"))
			{
				if(players[i].isSleepingIgnored())
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
			for(int i = 0; i < players.length; i++)
			{
				if(players[i].getWorld().equals(Spieler.getWorld()))
				{
					if(players[i].isSleeping()==false)
					{
						players[i].sendMessage(ChatColor.YELLOW+""+spielerName+" schläft und möchte, dass du auch ins Bett gehst!");
					}
				}
			}
		}
	}
}
