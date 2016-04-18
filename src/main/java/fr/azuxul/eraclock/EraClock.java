/*
 * Copyright (c) 2015-2016 Azuxul, All Rights Reserved.
 * https://azuxul.fr
 *
 * This software is published under the CeCILL-B license.
 */

package fr.azuxul.eraclock;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

/**
 * Main class of EraClock plugin
 */
public class EraClock extends JavaPlugin implements Listener {

    private PluginEraClock pluginEraClock;

    @Override
    public void onEnable() {

        this.pluginEraClock = new PluginEraClock(getServer());

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginCommand("clock").setExecutor(new CommandClock(pluginEraClock));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Map<UUID, PlayerEraClock> players = pluginEraClock.getPlayers();
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (!players.containsKey(uuid)) {
            players.put(uuid, new PlayerEraClock(event.getPlayer(), pluginEraClock));
        }

        players.values().stream().filter(p -> !p.isPlayerVisible()).forEach(p -> p.getPlayerIfOnline().hidePlayer(player));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        pluginEraClock.getPlayers().remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (event.getItem() != null && event.getItem().getType().equals(Material.WATCH) && (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {

            PlayerEraClock playerEraClock = pluginEraClock.getPlayers().get(event.getPlayer().getUniqueId());

            playerEraClock.switchPlayerVisibilty();
        }
    }

}
