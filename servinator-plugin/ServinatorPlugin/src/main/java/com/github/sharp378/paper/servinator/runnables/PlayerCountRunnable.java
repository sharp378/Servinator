package com.github.sharp378.paper.servinator.runnables;

import org.bukkit.Bukkit;

import java.util.logging.Logger; 

public class PlayerCountRunnable implements Runnable {

    private final static boolean isPlayerOnline() {
        return (Bukkit.getOnlinePlayers()).size() != 0;
    }

    private final static Logger logger = Bukkit.getLogger();

    @Override
    public void run() {
	if (isPlayerOnline()) {
	    return;
	}

	logger.info("No active players, shutting down server");
	Bukkit.shutdown();
    }
}

