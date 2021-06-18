package me.whitehatd;

import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerClass;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

public class Addon extends Plugin {

    public static Addon INSTANCE;

    public void onEnable(){

        INSTANCE = this;

        PAFPlayerClass.setServerConnector(new Hook());

        getProxy().getConsole().sendMessage(toColor("&aBungeeCordPartyAndFriends Jump Addon enabled! (Made by WhitehatD#6615)"));
    }

    public String toColor(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
