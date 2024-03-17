package com.github.sharp378.paper.servinator;

import com.github.sharp378.paper.servinator.runnables.PlayerCountRunnable;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.Long;
import java.lang.System;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class ServinatorPlugin extends JavaPlugin {
    
    private final static ThreadFactory playerCountThreadFactory = new ThreadFactoryBuilder()
        .setNameFormat("PlayerCount")
        .build();

    private ScheduledExecutorService playerCountExecutor;

    private static long getInterval(FileConfiguration config) {
        long interval = config.getLong("interval", 5);
        Long env = Long.parseLong(System.getenv("SERVINATOR_INTERVAL"));
      
	// env has priority
        return env != null
	    ? env
            : interval;	    
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

	final long interval = getInterval(getConfig());
	getLogger().info("Checking for server inactivity every " + interval + " minutes");
	playerCountExecutor = Executors.newSingleThreadScheduledExecutor(playerCountThreadFactory);
        playerCountExecutor.scheduleAtFixedRate(new PlayerCountRunnable(), interval, interval, TimeUnit.MINUTES);
    }

    @Override
    public void onDisable() {
	if (playerCountExecutor != null) {
	    playerCountExecutor.shutdown();
	}
    }
}

