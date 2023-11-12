package eu.floriware.minecraft.Smartmine;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

// Hello World Smartmine Programm
// Prints Helloworld and arguments
// by Florian Schiessl (16.2.2012)

public class CmdHelloWorld extends BasicProg
{
	public CmdHelloWorld(Plugin plugin, Player player, String n, String arg, boolean s)
	{
		super(plugin, player, n, arg, s);
	}

	public void handleCmd()
	{
		print("Hello World from " + Spieler.getDisplayName() + "\n" + arguments);
	}
	
}
