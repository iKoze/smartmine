package eu.floriware.minecraft.Smartmine;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BasicProg
{
	protected Player Spieler;
	protected String arguments;
	protected String args [];
	protected boolean show;
	protected Plugin smartmine;
	protected String name;
	
	public BasicProg (Plugin plugin, Player player, String n, String arg, boolean s)
	{
		Spieler = player;
		arguments = arg;
		args = arg.split(" ");
		show = s;
		smartmine = plugin;
		name = n;
		handleCmd();
	}
	
	public void handleCmd ()
	{
		
	}
	
	public boolean handleNextCmd (String cmd)
	{
		
		return false;
	}
	
	public void handleQuit()
	{
		
	}
	
	protected boolean noArgs()
	{
		// oder wenn 1.Eintrag leer oder null ist.
		if (args.length == 0 || (args.length == 1 && (args[0] == "" || args[0] == null)))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	protected String getArgByID(int i)
	{
		if (i < args.length)
		{
			return args[i];
		}
		else 
		{
			return "";
		}
	}
	
	protected String getArgByID(int i, String input)
	{
		String[]runargs = input.split(" ");
		if (i < runargs.length)
		{
			return runargs[i];
		}
		else 
		{
			return "";
		}
	}
	
	protected String getArgBySwitch(String schalter)
	{
		for (int i = 0; i < args.length; i++)
		{
			if(args[i].equals(schalter))
			{
				if (i+1 < args.length)
				{
					return args[i+1];
				}
			}
		}
		return "";
	}
	
	protected String getArgBySwitch(String schalter, String input)
	{
		String[]runargs = input.split(" ");
		for (int i = 0; i < runargs.length; i++)
		{
			if(runargs[i].equals(schalter))
			{
				if (i+1 < runargs.length)
				{
					return runargs[i+1];
				}
			}
		}
		return "";
	}
	
	protected boolean isSet(String schalter)
	{
		for (int i = 0; i < args.length; i++)
		{
			if(args[i].equals(schalter))
			{
				return true;
			}
		}
		return false;
	}
	
	protected boolean isSet(String schalter, String input)
	{
		String[]runargs = input.split(" ");
		for (int i = 0; i < runargs.length; i++)
		{
			if(runargs[i].equals(schalter))
			{
				return true;
			}
		}
		return false;
	}
	
	protected void print (String text)
	{
		String lines [] = text.split("\n");
		if(show == true){smartmine.getServer().broadcastMessage("<"+Spieler.getDisplayName()+">");}
		for(int i=0; i < lines.length; i++)
		{
			if (show == false)
			{
				Spieler.sendMessage(lines[i]);
			}
			else
			{
				smartmine.getServer().broadcastMessage(lines[i]);
			}
		}
	}
	
}
