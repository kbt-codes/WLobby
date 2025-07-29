package me.willowdev.lobby.events;

import me.willowdev.lobby.WLobby;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        var msg = WLobby.getInstance().getConfig().getString("messages.join");
        if (msg != null && !msg.isEmpty()) {
            String[] split = msg.replace("&", "§").split("\\|", 2);
            String title = split[0];
            String subtitle = split.length > 1 ? split[1] : "";
            e.getPlayer().sendTitle(title, subtitle, 10, 70, 20);
        }
    }
}
