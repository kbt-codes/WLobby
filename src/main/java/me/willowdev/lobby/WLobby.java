package me.willowdev.lobby;

import me.willowdev.lobby.commands.SetLobbyCommand;
import me.willowdev.lobby.commands.LobbyCommand;
import me.willowdev.lobby.commands.ScaleCommand;
import me.willowdev.lobby.commands.HelpCommand;
import me.willowdev.lobby.events.DeathListener;
import me.willowdev.lobby.events.JoinListener;
import me.willowdev.lobby.events.ScaleWorldListener;
import me.willowdev.lobby.events.WaterListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import java.util.HashMap;
import java.util.UUID;

public class WLobby extends JavaPlugin {

    private static WLobby instance;

    private final HashMap<UUID, Float> scaleMap = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        getCommand("wsetlobby").setExecutor(new SetLobbyCommand());
        getCommand("wlobby").setExecutor(new LobbyCommand());
        getCommand("wscale").setExecutor(new ScaleCommand(this));
        getCommand("whelp").setExecutor(new HelpCommand());
        Bukkit.getPluginManager().registerEvents(new WaterListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new ScaleWorldListener(), this);
    }

    public HashMap<UUID, Float> getScaleMap() {
        return scaleMap;
    }

    public static WLobby getInstance() {
        return instance;
    }

    public Location getLobbyLocation() {
        FileConfiguration config = getConfig();
        return new Location(
                Bukkit.getWorld(config.getString("lobby.world")),
                config.getDouble("lobby.x"),
                config.getDouble("lobby.y"),
                config.getDouble("lobby.z"),
                (float) config.getDouble("lobby.yaw"),
                (float) config.getDouble("lobby.pitch")
        );
    }
}