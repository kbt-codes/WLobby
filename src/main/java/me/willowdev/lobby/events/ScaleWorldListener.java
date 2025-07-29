package me.willowdev.lobby.events;

import me.willowdev.lobby.WLobby;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.util.List;
import java.util.UUID;

public class ScaleWorldListener implements Listener {
    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName();
        FileConfiguration config = WLobby.getInstance().getConfig();
        List<String> blacklist = config.getStringList("scale-blacklist");
        UUID uuid = player.getUniqueId();

        if (blacklist.contains(worldName)) {
            // Remove do mapa e reseta o scale
            WLobby.getInstance().getScaleMap().remove(uuid);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    "attribute " + player.getName() + " minecraft:scale base set 1");
            player.sendMessage("§eSeu tamanho foi redefinido para o normal neste mundo (§f1.0§e).");
        }
    }
}