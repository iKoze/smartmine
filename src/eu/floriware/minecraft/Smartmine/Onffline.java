package eu.floriware.minecraft.Smartmine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Onffline
{
	private Plugin smartmine;
	private String path = "";
	
	public Onffline (Plugin plugin)
	{
		smartmine = plugin;
		path = smartmine.getConfig().getString("onffline.path");
	}
	
	public void setOnline(Player Spieler)
	{
		String playerpath = parseString(Spieler,path);
		writeFile(playerpath, Spieler.getDisplayName());
	}
	
	public void setOffline(Player Spieler)
	{
		String playerpath = parseString(Spieler,path);
		deleteFile(playerpath);
	}
	
	public void setAllOffline()
	{
		Player players [] = smartmine.getServer().getOnlinePlayers();
		for (int i = 0; i < players.length; i++)
		{
			setOffline(players[i]);
		}
	}
	
	
	private String parseString (Player Spieler, String message)
	{
		String name = Spieler.getDisplayName();
		String ip = Spieler.getAddress().getAddress().toString();
		ip = ip.replace("/", "");
		message = message.replace("%u", name);
		message = message.replace("%i", ip);
		return message;
	}
	
	private boolean writeFile(String path, String content)
	{
		System.out.println("[SM][prevent0r]:writefile->"+path);
		FileOutputStream fout;
		try
		{
			fout = new FileOutputStream(path);
			new PrintStream(fout).println (content);
			fout.close();
		}
		catch (Exception e)
		{
			System.out.println("[SM][prevent0r]:writefile->"+path+"->exception: " + e.getMessage());
			return false;
		}
		File f = new File(path);
		f.setReadable(true, false);
		f.setWritable(true, false);
		return true;
	}
	
	//Datei löschen
	private void deleteFile(String path)
	{
		new File(path).delete();
	}
}

