package eu.floriware.minecraft.Smartmine;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class CmdTuerchen extends BasicProg
{
	public CmdTuerchen(Plugin plugin, Player player, String n, String arg, boolean s) 
	{
		super(plugin, player, n, arg, s);
	}

	public void handleCmd()
	{
		FileConfiguration config = smartmine.getConfig();
		Calendar cal = new GregorianCalendar();
		if(cal.get(Calendar.MONTH) == Calendar.DECEMBER)
		{
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int id = config.getInt("tuerchen."+day+".id",-1);
			int count = config.getInt("tuerchen."+day+".count",1);
			String message = config.getString("tuerchen."+day+".message");
			if(id != -1 && count > 0)
			{
				if(config.getBoolean("tuerchen."+day+"."+Spieler.getDisplayName(),true))
				{
					ItemStack content = new ItemStack(Material.getMaterial(id),count);
					Spieler.getInventory().addItem(content);
					print(parseString(message));
					config.set("tuerchen."+day+"."+Spieler.getDisplayName(),false);
				}
				else
				{
					print(parseString(config.getString("tuerchen.hasalready")));
				}

			}
			else
			{
				String nothing = config.getString("tuerchen.nothing");
				print(parseString(nothing));
			}
		}
		else
		{
			String message = config.getString("tuerchen.notdecember");
			print(parseString(message));
		}
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
