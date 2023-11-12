package eu.floriware.minecraft.Smartmine;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

//Logout Smartmine Programm
//Logs out Player who issued the command
//by Florian Schiessl (17.2.2012)

public class CmdLogout extends BasicProg
{
	public CmdLogout(Plugin plugin, Player player, String n, String arg, boolean s)
	{
		super(plugin, player, n, arg, s);
	}

	public void handleCmd()
	{
		Prevent0r prevent0r = new Prevent0r(smartmine);
		prevent0r.logoutPlayer(Spieler);
	}
	
}
