package net.CloudEXE.bungee.commands;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.permission.PermissionGroup;
import net.CloudEXE.bungee.core.Core;
import net.CloudEXE.bungee.mysql.Manager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Status extends Command {
    public Status() {
        super("status");
    }

    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer)sender;
        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());

        PermissionGroup permissionGroup = CloudAPI.getInstance().getPermissionGroup(cloudPlayer.getPermissionEntity().getGroups().iterator().next().getGroup());

            if (args.length == 2) {
                ProxiedPlayer target = BungeeCord.getInstance().getPlayer(args[1]);
                if (args[0].equalsIgnoreCase("set")) {
                    if (cloudPlayer == null) {
                        p.sendMessage(Core.Prefix + "§7Dieser Spieler ist §cnicht§7 Online§8!");
                    } else if(args.length == 2){
                        p.sendMessage(Core.Prefix + "Dein neuer Status ist nun §b" + args[1] + "§8!");
                        Manager.setStatus(p.getName(),args[1]);
                    }else if(args.length == 3){
                        p.sendMessage(Core.Prefix + "Dein neuer Status ist nun §b" + args[1] + args[2] + "§8!");
                        Manager.setStatus(p.getName(),args[1]);
                    }else if(args.length == 4){
                        p.sendMessage(Core.Prefix + "Dein neuer Status ist nun §b" + args[1] + args[2] + args[3] +"§8!");
                        Manager.setStatus(p.getName(),args[1]);
                    }else{
                        p.sendMessage(Core.Prefix + "Dein neuer Status ist nun §b" + args[1] + args[2] + args[3] + args[4] +"§8!");
                        Manager.setStatus(p.getName(),args[1]);
                    }
                }
            } else if (args != null) {
                p.sendMessage(Core.Prefix + "§7Dein Status§8: §e" + Manager.getStatus(p.getName()));
            }
    }
}
