package me.willowdev.lobby.events;

import me.willowdev.lobby.WLobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Location lobbyLoc = WLobby.getInstance().getLobbyLocation();

        // Só executa se o jogador estiver no mesmo mundo do lobby
        if (!player.getWorld().getName().equals(lobbyLoc.getWorld().getName())) {
            return;
        }

        Bukkit.getScheduler().runTaskLater(WLobby.getInstance(), () -> {
            player.spigot().respawn();
            player.teleport(lobbyLoc);

            String msg = WLobby.getInstance().getConfig().getString("messages.death");
            if (msg != null && !msg.isEmpty()) {
                String[] split = msg.replace("&", "§").split("\\|", 2);
                String title = split[0];
                String subtitle = split.length > 1 ? split[1] : "";
                player.sendTitle(title, subtitle, 10, 70, 20);
            }
        }, 2L); // Delay para garantir que o jogador tenha renascido
    }
}
