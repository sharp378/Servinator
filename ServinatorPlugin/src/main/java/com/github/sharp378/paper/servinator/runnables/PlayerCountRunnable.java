package com.github.sharp378.paper.servinator.runnables;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerCountRunnable implements Runnable {

    private JavaPlugin plugin;

    public PlayerCountRunnable(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private final static boolean isPlayerOnline() {
        return (Bukkit.getOnlinePlayers()).size() != 0;
    }

    @Override
    public void run() {
	if (isPlayerOnline()) {
	    return;
	}

	plugin.getLogger().info("No active players, shutting down server");
	Bukkit.shutdown();
    }
}

