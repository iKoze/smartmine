package eu.floriware.minecraft.Smartmine;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdMotd extends BasicProg
{
	public CmdMotd(Plugin plugin, Player player, String n, String arg, boolean s)
	{
		super(plugin, player, n, arg, s);
	}

	public void handleCmd()
	{
		String MOTD = smartmine.getConfig().getString("motd");
		MOTD = parseString(MOTD);
		print(MOTD);
	}
	
	private String parseString (String message)
	{
		String name = Spieler.getDisplayName();
		String ip = Spieler.getAddress().getAddress().toString();
		ip = ip.replace("/", "");
		message = message.replace("%u", name);
		message = message.replace("%i", ip);
		return message;
	}
}
