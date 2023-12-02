package eu.floriware.minecraft.Smartmine;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class Commander
{
	Plugin smartmine;
	Association assoc;
	BasicProg progs [];
	
	public Commander (Plugin plugin)
	{
		smartmine = plugin;
		int maxPlayers = smartmine.getServer().getMaxPlayers();
		assoc = new Association(maxPlayers, "");
		progs = new BasicProg[maxPlayers]; // Speichert die Objekte der offenen Programme
	}
	
	public boolean handleEvent(PlayerChatEvent event)
	{
		Player Spieler = event.getPlayer();
		String name = Spieler.getDisplayName();
		String message = event.getMessage();
		boolean cancelMessage = false;
		// Überprüfen, ob Spieler evtl. Programm am laufen hat
		int prog = assoc.getIDByIdentifier(name);
		if(prog != -1)
		{
			// Trouble Quit
			if(message.equalsIgnoreCase(":q"))
			{
				//Prog quit call
				progs[prog].handleQuit();
				progs[prog] = null;
				assoc.delAssociationByIdentifier(name); // Programm beenden
				Spieler.sendMessage(ChatColor.YELLOW + "[SM] Programm beendet.");
				return true;
			}
			
			// Spieler hat Programm (prog) offen
			// Do Programm handling here!
			
			//cancelMessage = progs[prog].handleNextCmd(message);
			cancelMessage =progs[prog].handleNextCmd(message);
			
		}
		
	/*	if(message.charAt(0) == 't' && message.length() == 1)
		{
			new CmdPanicRoom(smartmine,Spieler,"","",false);
		}*/
		// Überprüfen, ob Chat ein Befehl (!befehl) ist
		if(message.charAt(0) == '!' && message.length() > 1)
		{
			boolean show = false; // Ob das Ergebnis gebroadcastet wird
			message = message.replaceFirst("!", ""); // ! Entfernen
			String command[] = message.split(" ", 2); // command[0] = befehl ; command[1] = args;
			String args;
			try{args = command[1];}catch(Exception e){args = "";}
			// auf show prüfen
			if(command[0].equals("show"))
			{
				show = true;
				String splitarray[] = args.split(" ", 2); 
				command[0] = splitarray[0];
				args = args.replaceFirst(command[0], "");
				args = args.replaceFirst(" ", "");
			}
			String cmdname = command[0];

			// do Command matching here
			if(cmdname.equals("HelloWorld")){new CmdHelloWorld(smartmine,Spieler,cmdname,args,show);}
			else if(cmdname.equalsIgnoreCase("logout")){new CmdLogout(smartmine,Spieler,cmdname,args,show);}
			else if(cmdname.equalsIgnoreCase("prevent0r") || cmdname.equalsIgnoreCase("preventor")){new CmdPrevent0r(smartmine,Spieler,cmdname,args,show);}
			else if(cmdname.equalsIgnoreCase("bed") || cmdname.equalsIgnoreCase("bett")){new CmdBed(smartmine,Spieler,cmdname,args,show);}
			else if(cmdname.equalsIgnoreCase("w")){new CmdW(smartmine,Spieler,cmdname,args,show);}
			else if(cmdname.equalsIgnoreCase("connection") || cmdname.equalsIgnoreCase("conn")){ new CmdConn(smartmine,Spieler,cmdname,args,show);}
			else if(cmdname.equalsIgnoreCase("compass") || cmdname.equalsIgnoreCase("kompass")){ new CmdCompass(smartmine,Spieler,cmdname,args,show);}
			else if(cmdname.equalsIgnoreCase("sos") || cmdname.equalsIgnoreCase("panic-room")) { new CmdPanicRoom(smartmine,Spieler,cmdname,args,show); }
			else if(cmdname.equalsIgnoreCase("motd")) { new CmdMotd(smartmine,Spieler,cmdname,args,show); }
			else if(cmdname.equalsIgnoreCase("reload")) { new CmdReload(smartmine,Spieler,cmdname,args,show); }
			else if(cmdname.equalsIgnoreCase("tuerchen") || cmdname.equalsIgnoreCase("türchen")) { new CmdTuerchen(smartmine,Spieler,cmdname,args,show); }
			else if(cmdname.equalsIgnoreCase("GPS"))
			{
				int id = assoc.newAssociation(name, "GPS");
				if (id != -1)
				{
					Spieler.sendMessage(ChatColor.YELLOW+"[SM] Programm gestartet. Beenden mit ':q'");
					progs[id] = new CmdGPS(smartmine,Spieler,cmdname,args,show);
				}
				else
				{
					Spieler.sendMessage(ChatColor.RED+"[SM] Multitasking wird (noch) nicht unterstüzt!");
				}
			}
			else
			{
				// Chat foo
				Chatpp chatpp = new Chatpp(smartmine,Spieler,cmdname,args,show);
				if(chatpp.executed == false){Spieler.sendMessage(ChatColor.RED+"Unknown Command.");}
			}

			return true;
		}
		
		return cancelMessage;
	}	
}
