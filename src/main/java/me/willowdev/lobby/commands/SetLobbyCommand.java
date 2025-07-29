package me.willowdev.lobby.commands;

import me.willowdev.lobby.WLobby;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class SetLobbyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("Apenas jogadores podem usar este comando.");
            return true;
        }

        var loc = p.getLocation();
        var config = WLobby.getInstance().getConfig();

        config.set("lobby.world", loc.getWorld().getName());
        config.set("lobby.x", loc.getX());
        config.set("lobby.y", loc.getY());
        config.set("lobby.z", loc.getZ());
        config.set("lobby.yaw", loc.getYaw());
        config.set("lobby.pitch", loc.getPitch());

        WLobby.getInstance().saveConfig();
        p.sendMessage("§aLobby definido com sucesso!");
        return true;
    }
}
