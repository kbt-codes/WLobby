package me.willowdev.lobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cApenas jogadores podem usar este comando.");
            return true;
        }

        p.sendMessage("§6§l===== Ajuda WLobby =====");
        p.sendMessage("§e/wscale <número|reset> §f- Altera o tamanho do jogador");
        p.sendMessage("§e/whelp §f- Mostra essa mensagem de ajuda");
        p.sendMessage("§e/wlobby §f- Teleporta você para o lobby");
        p.sendMessage("§e/wsetlobby §f- Define a localização do lobby");
        // Adicione aqui mais comandos que quiser listar

        return true;
    }
}
