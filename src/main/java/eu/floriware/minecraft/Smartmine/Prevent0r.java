package eu.floriware.minecraft.Smartmine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Prevent0r
{
	private Plugin smartmine;
	private String path = "";
	private String okmessage = "";
	private String errmessage = "";
	private String logoutmessage ="";
	private String kickmessage = "";
	private String exitmessage = "";
	private String onmessage = "";
	private String offmessage = "";
	
	public Prevent0r(Plugin plugin)
	{
		smartmine = plugin;
		path = smartmine.getConfig().getString("prevent0r.path");
		okmessage = smartmine.getConfig().getString("prevent0r.okmessage");
		errmessage = smartmine.getConfig().getString("prevent0r.errmessage");
		logoutmessage = smartmine.getConfig().getString("prevent0r.logoutmessage");
		kickmessage = smartmine.getConfig().getString("prevent0r.kickmessage");
		exitmessage = smartmine.getConfig().getString("prevent0r.exitmessage");
		onmessage = smartmine.getConfig().getString("prevent0r.onmessage");
		offmessage = smartmine.getConfig().getString("prevent0r.offmessage");
	}
	
	public boolean isOn(Player Spieler)
	{
		// Wenn die ip Datei existiert, ist Prevent0r an.
		File f = new File(parseString(Spieler, path));
		return f.exists();
	}
	
	public boolean isAllowed(Player Spieler)
	{
		// Gibt zurück, ob der Spieler authorisiert ist
		if(isOn(Spieler) == true)
		{
			if(getIPfromPlayer(Spieler).equals(getIPfromFile(Spieler)))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return true;
		}
	}
	
	public void logoutPlayer(Player Spieler)
	{
		if(isOn(Spieler))
		{
			writeFile(parseString(Spieler, path), "");
			Spieler.kickPlayer(parseString(Spieler, logoutmessage));
		}
		else
		{
			Spieler.kickPlayer(parseString(Spieler, exitmessage));
		}
	}
	
	// set functions
	public boolean setOn(Player Spieler, boolean on)
	{
		if (on == true)
		{
			// Prevent0r mit aktueller IP einschalten
			return writeFile(parseString(Spieler, path),getIPfromPlayer(Spieler));
		}
		else
		{
			deleteFile(parseString(Spieler, path));
			return false;
		}
	}
	
	// get functions
	
	public String getIPfromFile(Player Spieler)
	{
		// gibt den Inhalt der ip Datei wieder.
		return readFile(parseString(Spieler, path));
	}
	
	public String getIPfromPlayer(Player Spieler)
	{
		String ip = Spieler.getAddress().getAddress().toString();
		ip = ip.replace("/", "");
		return ip;
	}
	
	
	// getMessages
	
	public String getOkMessage (Player Spieler)
	{
		return parseString(Spieler, okmessage);
	}
	
	public String getErrMessage (Player Spieler)
	{
		return parseString(Spieler, errmessage);
	}	
	
	public String getLogoutMessage (Player Spieler)
	{
		return parseString(Spieler, logoutmessage);
	}
	
	public String getKickMessage (Player Spieler)
	{
		return parseString(Spieler, kickmessage);
	}
	
	public String getExitMessage (Player Spieler)
	{
		return parseString(Spieler, exitmessage);
	}
	
	public String getOnMessage (Player Spieler)
	{
		return parseString(Spieler, onmessage);
	}
	
	public String getOffMessage (Player Spieler)
	{
		return parseString(Spieler, offmessage);
	}
	
	
	// parseMessage
	private String parseString (Player Spieler, String message)
	{
		String name = Spieler.getDisplayName();
		String ip = Spieler.getAddress().getAddress().toString();
		ip = ip.replace("/", "");
		message = message.replace("%u", name);
		message = message.replace("%i", ip);
		return message;
	}
	
	// Datei lesen
	private String readFile(String path)
	{
		String output = "";
		try
		{
			FileReader input = new FileReader(path);
			BufferedReader bufRead = new BufferedReader(input);
			String line;
			do
			{
				line = bufRead.readLine();
				if (line != null){output = output + line;}
			}
			while (line != null);
			bufRead.close();
		}
		catch (Exception e)
		{
			System.out.println("[SM][prevent0r]:readfile->"+path+"->exception: " + e.getMessage());
			return null;
		}
		return output;
	}
	
	// Datei schreiben
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
