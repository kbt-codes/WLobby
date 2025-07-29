package me.willowdev.lobby.events;

import me.willowdev.lobby.WLobby;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WaterListener implements Listener {

    @EventHandler
    public void onWaterFall(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        // Verifica se está no mundo do lobby
        if (!p.getWorld().equals(WLobby.getInstance().getLobbyLocation().getWorld())) {
            return;
        }
        if (p.getLocation().getBlock().getType() == Material.WATER) {
            p.teleport(WLobby.getInstance().getLobbyLocation());

            // Efeito de partículas de gotas de água
            p.getWorld().spawnParticle(org.bukkit.Particle.SPLASH, p.getLocation(), 30, 0.5, 1, 0.5, 0.2);
            p.getWorld().spawnParticle(org.bukkit.Particle.DRIPPING_WATER, p.getLocation(), 20, 0.5, 1, 0.5, 0.1);

            // Som de sair da água
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1f, 1f);

            String msg = WLobby.getInstance().getConfig().getString("messages.water");
            if (msg != null && !msg.isEmpty()) {
                String[] split = msg.replace("&", "§").split("\\|", 2);
                String title = split[0];
                String subtitle = split.length > 1 ? split[1] : "";
                p.sendTitle(title, subtitle, 10, 70, 20);
            }
        }
    }
}
