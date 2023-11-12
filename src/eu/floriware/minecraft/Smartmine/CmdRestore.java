package eu.floriware.minecraft.Smartmine;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdRestore extends BasicProg
{
	public CmdRestore(Plugin plugin, Player player, String n, String arg, boolean s)
	{
		super(plugin, player, n, arg, s);
	}

	public void handleCmd()
	{
		// Spieler neu laden.
	/*	if(Spieler.getInventory().contains(Material.DIAMOND))
		{
			Location loc = Spieler.getLocation();
			Spieler.loadData();
			Spieler.teleport(loc);
			print(Spieler.getDisplayName()+" geladen.");
		}
		else
		{
			print("Dein Inventar wiederherzustellen kostet dich 1 Diamanten!");
		}
		*/
		print(ChatColor.RED+"Dieses Programm überschreibt dein aktuelles Inventar!\nAbbrechen mit ':q'");
	}
	
	public void handleNextCmd()
	{

	}
	
	public void handleQuit()
	{
		print("Restore beendet.");
	}
}
