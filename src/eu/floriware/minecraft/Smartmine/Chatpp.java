package eu.floriware.minecraft.Smartmine;

import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Chatpp extends BasicProg
{
	public boolean executed;
	public String color;
	public Chatpp(Plugin plugin, Player player, String n, String arg, boolean s)
	{
		super(plugin, player, n, arg, s);
	}

	public void handleCmd()
	{
		color = ChatColor.AQUA+"";
		String playername = Spieler.getDisplayName();
		Server server = smartmine.getServer();
		if(name.equalsIgnoreCase("bye"))
		{
			show = true;
			print(color+playername + " waves his hand and says Goodbye!");
			executed = true;
			return;
		}
		//!blowjob
		else if(name.equalsIgnoreCase("blowjob"))
		{
			show = true;
			Player target = server.getPlayerExact(args[0]);
			String targetName = "";
			if (target != null)
			{
				targetName = target.getDisplayName();
			}
			else
			{
				targetName = playername;
			}
			print(color+"A sexy nice Bitch gives "+targetName+" a blowjob!");
			executed = true;
			return;
		}
		//!pizza
		else if(name.equalsIgnoreCase("pizza"))
		{
			show = true;
			Player target = server.getPlayerExact(args[0]);
			String targetName = "";
			if (target != null)
			{
				targetName = target.getDisplayName();
			}
			else
			{
				targetName = playername;
			}
			print(color+targetName+" bekommt von einer hübschen Kellnerin eine Pizza serviert!");
			executed = true;
			return;
		}
		//!bier
		else if(name.equalsIgnoreCase("bier"))
		{
			show = true;
			Player target = server.getPlayerExact(args[0]);
			String targetName = "";
			if (target != null)
			{
				targetName = target.getDisplayName();
				print(color+targetName + " bekommt ein Tragerl Freibier von "+playername+"!");
			}
			else
			{
				targetName = playername;
				print(color+targetName + " zieht sich ein schönes kühles Bier rein!");
			}
			executed = true;
			return;
		}
		//!time //!date
		else if(name.equalsIgnoreCase("time") || name.equalsIgnoreCase("date"))
		{
			Date dt = new Date();
			print(""+ChatColor.YELLOW+dt);
			executed = true;
			return;
		}
		//!save
		else if(name.equalsIgnoreCase("save"))
		{
			// Spieler auf Festplatte sichern
			Spieler.saveData();
			print(Spieler.getDisplayName()+" gespeichert.");
			executed = true;
			return;
		}
		//!loc
		else if(name.equalsIgnoreCase("loc"))
		{
			// Spieler aktuelle Position anzeigen
			Location loc = Spieler.getEyeLocation();
			int x,y,z;
			x = (int) loc.getX();
			y = (int) loc.getY();
			z = (int) loc.getZ();
			print(ChatColor.YELLOW+""+x+","+y+","+z);
			executed = true;
			return;
		}
		//!rail
		else if(name.equalsIgnoreCase("rail"))
		{
			ItemStack rails = new ItemStack(Material.RAILS, 64);
			Spieler.getInventory().addItem(rails);
			print(color+"Wir geben dir ein Stack Gleise");
			executed = true;
			return;
		}
		//!selbstmord //!suicide
		else if(name.equalsIgnoreCase("selbstmord") || name.equalsIgnoreCase("suicide"))
		{
			show = true;
			int Random = (int)(Math.random()*6);
			if(Random == 0)
			{
				// Spieler Brennt
				Spieler.setFireTicks(1000);
				server.broadcastMessage(color+playername + " fing Feuer!");
			}
			else if(Random == 1)
			{
				// Spieler bekommt Herzinfakt
				Spieler.damage(200000);
				server.broadcastMessage(color+playername + " hat einen Herzinfakt erlitten!");
			}
			else if(Random == 2)
			{
				// Spieler verhungert
				Spieler.setFoodLevel(0);
				Spieler.setHealth(15);
				server.broadcastMessage(color+playername + " ist am verhungern!");
			}
			else if(Random == 3)
			{
				// Spieler versucht zu fliegen
				Location loc = Spieler.getLocation();
				loc.setY(600);
				Spieler.teleport(loc);
				server.broadcastMessage(color+playername + " versucht zu fliegen!");
			}
			else if(Random == 4)
			{
				// Spiele fällt aus Map
				Location loc = Spieler.getLocation();
				loc.setY(-2);
				Spieler.teleport(loc);
				server.broadcastMessage(color+playername + " ist aus der Welt gefallen!");
			}
			else if(Random == 5)
			{
				// Spieler steckt in Bedrock Schicht
				Location loc = Spieler.getLocation();
				loc.setY(1);
				Spieler.teleport(loc);
				server.broadcastMessage(color+playername + " steckt in irgendeiner Wand!");
			}
			else
			{
				server.broadcastMessage(color+playername + " hat einen Selbstmordversuch überlebt!");
			}
			executed = true;
			return;
		}
		if(executed != true)
		{
			executed = false;
		}
		return;
	}

}
