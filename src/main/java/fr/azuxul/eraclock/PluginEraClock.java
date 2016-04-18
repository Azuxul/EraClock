/*
 * Copyright (c) 2015-2016 Azuxul, All Rights Reserved.
 * https://azuxul.fr
 *
 * This software is published under the CeCILL-B license.
 */

package fr.azuxul.eraclock;

import org.bukkit.Server;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Plugin EraClock class
 *
 * @author Azuxul
 * @version 1.0
 */
public class PluginEraClock {

    private final Server server;
    private final Map<UUID, PlayerEraClock> players;

    public PluginEraClock(Server server) {

        this.server = server;
        this.players = new HashMap<>();
    }

    public Server getServer() {
        return server;
    }

    public Map<UUID, PlayerEraClock> getPlayers() {
        return players;
    }
}
