package info.insomniax.owhblank.vault;

import info.insomniax.owhblank.core.BukkitPlugin;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Permissions {

	static String BASE_NODE;
	BukkitPlugin myPlugin;
	public static Permission permission = null;
	public static Economy economy = null;
	
	public Permissions(BukkitPlugin instance)
	{
		myPlugin = instance;
		BASE_NODE = instance.getName().replace(" ", "");
	}
	
	public boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = myPlugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
	
	public boolean has(CommandSender sender, String node, boolean allowConsole)
	{
		if(sender instanceof CommandSender)
			return allowConsole;
		
		return permission.has(sender, node);
	}
	
	public boolean has(CommandSender sender, Command command, boolean allowConsole)
	{
		if(sender instanceof CommandSender)
			return allowConsole;
		
		return permission.has(sender, getBaseNode() + ".commands." + command.getName());
	}
	
	public boolean has(CommandSender sender, Command command, String[] args, boolean allowConsole)
	{
		if(sender instanceof CommandSender)
			return allowConsole;
		
		return permission.has(sender, getBaseNode() + ".commands." + command.getName() + "." + StringUtils.join(args).toLowerCase());
	}
	
	public static String getBaseNode()
	{
		return BASE_NODE;
	}
}
