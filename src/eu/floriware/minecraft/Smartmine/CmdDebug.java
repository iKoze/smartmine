package eu.floriware.minecraft.Smartmine;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

//Data Debug Programm
//
//by Florian Schiessl (11.9.2012)

public class CmdDebug extends BasicProg
{

	public CmdDebug(Plugin plugin, Player player, String n, String arg, boolean s)
	{
		super(plugin, player, n, arg, s);
	}

	public void handleCmd()
	{
		if(smartmine.getConfig().getBoolean("debug.enabled"))
		{
			Location loc = Spieler.getLocation();
			loc.setY(loc.getY() - 1.0);
			Block block = loc.getBlock();
			Byte data = block.getData();
			if(isSet("-d"))
			{
				String newData = getArgBySwitch("-d");
				try
				{
					data = Byte.parseByte(newData);
					block.setData(data);
				}
				catch(Exception e)
				{
					print(e.getMessage());
				}
				finally
				{
					print("Data was set.");
				}
			}
			else if(isSet("-u"))
			{
				try
				{
					block.getState().update(true);
				}
				catch(Exception e)
				{
					print(e.getMessage());
				}
				finally
				{
					print("successfull");
				}
			}
			print("DATA: "+data);
			print("POWER: "+block.getBlockPower());
		}
		else
		{
			print(ChatColor.RED + "This function is disabled in the configuration!");
		}
	}
}
