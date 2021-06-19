package me.whitehatd;

import de.simonsator.partyandfriends.api.events.command.JumpToFriendEvent;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerClass;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class Addon extends Plugin implements Listener {

    public static Addon INSTANCE;

    public void onEnable(){

        INSTANCE = this;

        PAFPlayerClass.setServerConnector(new Hook());
        getProxy().getPluginManager().registerListener(this, this);

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
}
