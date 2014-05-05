package info.omgwtfhax.blank.bukkit;

import info.omgwtfhax.blank.vault.Permissions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class acts as a bridge between the plugin and the server software. When possible, anything that involves
 * referencing bukkit should have to pass through this plugin to make for optimal portability.
 * */
public class BukkitPlugin extends JavaPlugin
{
	
	Permissions p;
	
	public void onEnable()
	{
		//TODO setup plugin.yml
		
		p = new Permissions(this);
		
		if(p.setupPermissions())
			this.getLogger().info("Permissions setup successful.");
		else
			this.getLogger().warning("Permissions setup failed, falling back to OP-only for any permissions");
	}
	
	public void onDisable()
	{
		this.saveConfig();
	}
	
	public static void sendMessage(String player, String message)
	{
		Bukkit.getPlayer(player).sendMessage(message);
	}
	
	public static void broadcast(String message)
	{
		Bukkit.broadcastMessage(message);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(p.has(sender, cmd, false))
		{
			
		}
		return false;
	}
}
