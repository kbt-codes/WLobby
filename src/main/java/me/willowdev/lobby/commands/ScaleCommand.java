package me.willowdev.lobby.commands;

import me.willowdev.lobby.WLobby;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ScaleCommand implements CommandExecutor {

    private final WLobby plugin;

    public ScaleCommand(WLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cApenas jogadores podem usar este comando.");
            return true;
        }

        if (args.length != 1) {
            p.sendMessage("§cUso correto: /wscale <tamanho | reset>");
            return true;
        }

        UUID uuid = p.getUniqueId();

        if (args[0].equalsIgnoreCase("reset")) {
            plugin.getScaleMap().remove(uuid);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    "attribute " + p.getName() + " minecraft:scale base set 1");
            p.sendMessage("§eSeu tamanho foi redefinido para o normal (§f1.0§e).");
            return true;
        }

        try {
            float scale = Float.parseFloat(args[0]);
            if (scale < 0.1f || scale > 10.0f) {
                p.sendMessage("§cO tamanho deve estar entre 0.1 e 10.0.");
                return true;
            }

            plugin.getScaleMap().put(uuid, scale);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    "attribute " + p.getName() + " minecraft:scale base set " + scale);
            p.sendMessage("§aSeu tamanho foi definido para §f" + scale + "§a! Este valor será salvo.");
        } catch (NumberFormatException e) {
            p.sendMessage("§cPor favor, insira um número válido.");
        }

        return true;
    }
}
