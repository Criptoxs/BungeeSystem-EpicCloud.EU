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

import java.util.UUID;

public class Coins extends Command {
    public Coins() {
        super("coins");
    }

    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer)sender;
        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());

        PermissionGroup permissionGroup = CloudAPI.getInstance().getPermissionGroup(cloudPlayer.getPermissionEntity().getGroups().iterator().next().getGroup());
        if (!p.hasPermission("bungee.coins")) {
            p.sendMessage(Core.Prefix + "§7Deine Coins§8: §e" + Manager.getCoins(p.getName()));
        } else {
            if (args.length == 3) {
                int coins = Integer.valueOf(args[2]);
                ProxiedPlayer target = BungeeCord.getInstance().getPlayer(args[1]);
                if (args[0].equalsIgnoreCase("add")) {
                    if (cloudPlayer == null) {
                        p.sendMessage(Core.Prefix + "§7Dieser Spieler ist §cnicht§7 Online§8!");
                    } else {
                        target.sendMessage(Core.Prefix + "Du hast §e" + args[2] + " Coins §7bekommen§8!");
                        p.sendMessage(Core.Prefix + "Du hast §b" + args[1] + " §e" + args[2] + "§7 Coins gegeben§8!");
                        Manager.addCoins(target.getName(), coins);
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (cloudPlayer == null){
                        p.sendMessage(Core.Prefix + "§7Dieser Spieler ist §cnicht§7 Online§8!");
                    } else {
                        Manager.removeCoins(target.getName(), coins);
                        p.sendMessage(Core.Prefix + "Du hast §b" + args[1] + " §e" + args[2] + " Coins§7 weggenommen§8!");
                    }
                }
            } else if (args != null) {
                p.sendMessage(Core.Prefix + "§7Deine Coins§8: §e" + Manager.getCoins(p.getName()));
            }

        }
    }
}
