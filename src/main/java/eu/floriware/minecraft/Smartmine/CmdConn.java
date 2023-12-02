package eu.floriware.minecraft.Smartmine;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdConn extends BasicProg
{
	public CmdConn(Plugin plugin, Player player, String n, String arg, boolean s)
	{
		super(plugin, player, n, arg, s);
	}
	
	public void handleCmd()
	{
		String ip = Spieler.getAddress().getAddress().toString();
		ip = ip.replace("/", "");
		String hostname = Spieler.getAddress().getHostName();
		String fqdn = Spieler.getAddress().getAddress().getCanonicalHostName();
		int port = Spieler.getAddress().getPort();
		String output = ChatColor.YELLOW+"Connection Information for "+Spieler.getDisplayName()+"\n";
		output = output + "IP-Address: " + ip + "\n";
		output = output + "Remote-Port: " + port + "\n";
		output = output + "Host: " + hostname + "\n";
		output = output + "FQDN: " + fqdn + "\n";
		print(output);
	}
}
