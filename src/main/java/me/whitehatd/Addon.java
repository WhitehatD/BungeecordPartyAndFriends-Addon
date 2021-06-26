package me.whitehatd;

import com.google.common.io.ByteStreams;
import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.api.events.command.JumpToFriendEvent;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerClass;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

import java.io.*;

public class Addon extends PAFExtension implements Listener {

    public static Addon INSTANCE;

    public void onEnable(){

        INSTANCE = this;

        PAFPlayerClass.setServerConnector(new Hook());
        getProxy().getPluginManager().registerListener(this, this);

        if (!getConfigFolder().exists()) {
            getConfigFolder().mkdir();
        }
        File configFile = new File(getConfigFolder(), "config.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                try (InputStream is = getResourceAsStream("config.yml");
                     OutputStream os = new FileOutputStream(configFile)) {
                    ByteStreams.copy(is, os);
                }
            } catch (IOException e) {
                throw new RuntimeException("Unable to create configuration file", e);
            }
        }

        registerAsExtension();
        getProxy().getConsole().sendMessage(toColor("&aBungeeCordPartyAndFriends Jump Addon enabled! (Made by WhitehatD#6615)"));
    }

    public String toColor(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    @EventHandler
    public void onFriend(JumpToFriendEvent e){
        if(!e.getFriendToJumpTo().getServer().canAccess(e.getExecutor().getPlayer()))
            e.setCancelled(true);
    }

    public Configuration getConfig(){
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .load(new File(getConfigFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
