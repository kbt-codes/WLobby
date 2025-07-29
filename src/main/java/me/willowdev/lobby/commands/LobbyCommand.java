package me.willowdev.lobby.commands;

import me.willowdev.lobby.WLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class LobbyCommand implements CommandExecutor {
    private static final HashMap<UUID, Long> cooldowns = new HashMap<>();
    private static final int COOLDOWN_SECONDS = 10; // ajuste o tempo aqui

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("Apenas jogadores podem usar este comando.");
            return true;
        }
        long now = System.currentTimeMillis();
        if (cooldowns.containsKey(p.getUniqueId())) {
            long last = cooldowns.get(p.getUniqueId());
            long secondsLeft = (last + COOLDOWN_SECONDS * 1000 - now) / 1000;
            if (secondsLeft > 0) {
                p.sendMessage("§cAguarde " + secondsLeft + " segundos para usar o /wlobby novamente.");
                return true;
            }
        }
        cooldowns.put(p.getUniqueId(), now);

        p.teleport(WLobby.getInstance().getLobbyLocation());
        // Efeito de partículas
        p.getWorld().spawnParticle(org.bukkit.Particle.PORTAL, p.getLocation(), 100, 1, 1, 1, 0.2);
        // Som
        p.playSound(p.getLocation(), org.bukkit.Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
        String msg = WLobby.getInstance().getConfig().getString("messages.lobby");
        if (msg != null && !msg.isEmpty()) {
            String[] split = msg.replace("&", "§").split("\\|", 2);
            String title = split[0];
            String subtitle = split.length > 1 ? split[1] : "";
            p.sendTitle(title, subtitle, 10, 70, 20);
        }
        return true;
    }
}