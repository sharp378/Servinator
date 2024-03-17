package com.github.sharp378.paper.servinator;

import com.github.sharp378.paper.servinator.runnables.PlayerCountRunnable;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.Long;
import java.lang.System;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ServinatorPlugin extends JavaPlugin {
    
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
	final long ticks = TimeUnit.MINUTES.toSeconds(interval) * 20;
	
	getLogger().info("Checking for server inactivity every " + interval + " minutes");

        getServer().getScheduler().runTaskTimer(this,
	    new PlayerCountRunnable(this),
	    ticks,
	    ticks);	
    }
}

