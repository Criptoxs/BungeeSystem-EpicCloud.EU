package net.CloudEXE.bungee.core;

import net.CloudEXE.bungee.commands.Coins;
import net.CloudEXE.bungee.commands.Status;
import net.CloudEXE.bungee.listeners.Events;
import net.CloudEXE.bungee.methoden.Config;
import net.CloudEXE.bungee.mysql.Client;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.beans.EventHandler;

public class Core extends Plugin {
    public static String Prefix = "§8► §bEpic§3Cloud §8┃§7 ";

    @Override
    public void onEnable() {
        Client.connect();
        Config.setConfigs();
        System.out.println("Das BungeeSystem ist nun aktiviert!");
        Client.Update("CREATE TABLE IF NOT EXISTS Coins(name varchar(32),c int)");
        Client.Update("CREATE TABLE IF NOT EXISTS Status(name varchar(32),status varchar(32),ip varchar(100))");
        ProxyServer.getInstance().getPluginManager().registerListener(this,new Events());
        ProxyServer.getInstance().getPluginManager().registerCommand(this,new Coins());
        ProxyServer.getInstance().getPluginManager().registerCommand(this,new Status());
    }

    @Override
    public void onDisable() {
        Client.disconnect();
        System.out.println("Das BungeeSystem ist nun deaktiviert!");
    }
}
