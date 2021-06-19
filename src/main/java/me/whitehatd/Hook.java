package me.whitehatd;

import de.simonsator.partyandfriends.api.friends.ServerConnector;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Hook implements ServerConnector {
    @Override
    public void connect(ProxiedPlayer proxiedPlayer, ServerInfo serverInfo) {
        if(serverInfo.canAccess(proxiedPlayer)) {
            proxiedPlayer.connect(serverInfo);
            proxiedPlayer.sendMessage(Addon.INSTANCE.toColor(Addon.INSTANCE.getConfig().getString("message")));
        }
        else proxiedPlayer.sendMessage(Addon.INSTANCE.toColor("&cYou cannot access this server!"));
    }
}
