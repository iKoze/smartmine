package eu.floriware.minecraft.Smartmine;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdPrevent0r extends BasicProg
{
	public CmdPrevent0r(Plugin plugin, Player player, String n, String arg, boolean s)
	{
		super(plugin, player, n, arg, s);
	}

	public void handleCmd()
	{
		Prevent0r prevent0r = new Prevent0r(smartmine);
		String onoff = getArgByID(0);

		if(onoff.equalsIgnoreCase("on") || onoff.equalsIgnoreCase("an"))
		{
			prevent0r.setOn(Spieler, true);
			print(prevent0r.getOnMessage(Spieler));
		}
		else if (onoff.equalsIgnoreCase("off") || onoff.equalsIgnoreCase("aus"))
		{
			prevent0r.setOn(Spieler, false);
			print(prevent0r.getOffMessage(Spieler));
		}
		else
		{
			if(prevent0r.isOn(Spieler))
			{
				print(prevent0r.getOnMessage(Spieler));
			}
			else
			{
				print(prevent0r.getOffMessage(Spieler));
			}
		}
	}
}
