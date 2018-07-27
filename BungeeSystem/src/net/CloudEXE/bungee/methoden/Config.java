package net.CloudEXE.bungee.methoden;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    public static void setConfigs() {

        File ord = new File("plugins/BungeeSystem");

        if(!ord.exists()) {
            ord.mkdir();
        }

        File cfge = new File("plugins/BungeeSystem/config.yml");

        if(!cfge.exists()) {
            try {
                cfge.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Configuration cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(cfge);

            try {
                if(!cfg.getBoolean("update.set")) {
                    cfg.set("update.1", "1");
                    cfg.set("update.2", "2");
                    cfg.set("update.3", "3");
                    cfg.set("update.set", true);
                }
            } catch (Exception ex) {
                cfg.set("update.1", "1");
                cfg.set("update.2", "2");
                cfg.set("update.3", "3");
                cfg.set("update.set", true);
            }

            ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, cfge);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getUpdate1() {

        String host = "";

        File mysql = new File("plugins/BungeeSystem/config.yml");

        try {
            Configuration cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(mysql);

            host = cfg.getString("update.1");

        } catch (IOException e) {
            e.printStackTrace();
        }


        return host;
    }

    public static String getUpdate2() {

        String port = "";

        File mysql = new File("plugins/BungeeSystem/config.yml");

        try {
            Configuration cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(mysql);

            port = cfg.getString("update.2");

        } catch (IOException e) {
            e.printStackTrace();
        }


        return port;
    }


    public static String getUpdate3() {

        String pw = "";

        File mysql = new File("plugins/BungeeSystem/config.yml");

        try {
            Configuration cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(mysql);

            pw = cfg.getString("update.3");

        } catch (IOException e) {
            e.printStackTrace();
        }


        return pw;
    }
}
