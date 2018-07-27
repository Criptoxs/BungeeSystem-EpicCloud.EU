package net.CloudEXE.bungee.listeners;

import de.dytanic.cloudnet.api.CloudAPI;
import jdk.Exported;
import net.CloudEXE.bungee.methoden.Config;
import net.CloudEXE.bungee.mysql.Client;
import net.CloudEXE.bungee.mysql.Manager;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.awt.*;

public class Events implements Listener {

    @EventHandler
    public void onProxyPing(ProxyPingEvent e){
        if(!CloudAPI.getInstance().getProxyGroupData("Proxy").getProxyConfig().isMaintenance()){
            ServerPing ping = e.getResponse();
            ping.setVersion(new ServerPing.Protocol("§8►§c " + CloudAPI.getInstance().getOnlineCount() + "§7 Spieler §aOnline §8◄", 2));

        }
    }

    @EventHandler
    public void onPreLogin(PreLoginEvent e) {
        if(!Client.dataContainsUserCoins(e.getConnection().getName())) {
            Client.createUserCoins(e.getConnection().getName());
        }
        if(!Client.dataContainsStatus(e.getConnection().getName())){
            Client.createUserStatus(e.getConnection().getName(),"Playing on EpicCloud");
        }
    }

    @EventHandler
    public void onPostLogin(PostLoginEvent e){
        e.getPlayer().sendMessage("§8§m---------------------------");
        e.getPlayer().sendMessage("");
        e.getPlayer().sendMessage("  §8» §bNews§8:");
        e.getPlayer().sendMessage("  §8» §7" + Config.getUpdate1().replace("&","§"));
        e.getPlayer().sendMessage("  §8»§7 " + Config.getUpdate2().replace("&","§"));
        e.getPlayer().sendMessage("  §8»§7 " + Config.getUpdate3().replace("&","§"));
        e.getPlayer().sendMessage("");
        e.getPlayer().sendMessage("§8§m---------------------------");
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent e){
    }
}
