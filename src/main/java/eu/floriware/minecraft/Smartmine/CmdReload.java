package eu.floriware.minecraft.Smartmine;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdReload extends BasicProg
{

	public CmdReload(Plugin plugin, Player player, String n, String arg, boolean s)
	{
		super(plugin, player, n, arg, s);
	}
	
	public void handleCmd()
	{
		if(Spieler.isOp())
		{
			smartmine.reloadConfig();
			print(ChatColor.GREEN+"Config reloaded.");
		}
		else
		{
			print(ChatColor.RED+"Only operators can do that!");
		}
	}

}
