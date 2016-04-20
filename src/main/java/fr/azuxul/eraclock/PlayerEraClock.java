/*
 * Copyright (c) 2015-2016 Azuxul, All Rights Reserved.
 * https://azuxul.fr
 *
 * This software is published under the CeCILL-B license.
 */

package fr.azuxul.eraclock;

import org.bukkit.entity.Player;

import java.util.Date;
import java.util.UUID;

/**
 * Player class for EraClock plugin
 *
 * @author Azuxul
 * @version 1.0
 */
public class PlayerEraClock {

    private final UUID uuid;
    private final Player player;
    private final PluginEraClock pluginEraClock;
    private boolean playerVisible;
    private long changeVisibilityTime;

    public PlayerEraClock(Player player, PluginEraClock pluginEraClock) {

        this.pluginEraClock = pluginEraClock;
        this.player = player;
        this.uuid = player.getUniqueId();
        this.playerVisible = true;
        this.changeVisibilityTime = 0;
    }

    public Player getPlayerIfOnline() {
        return player;
    }

    public long getRemainingTimeBeforeChange() {

        return changeVisibilityTime - new Date().getTime() + 5 * 1000;
    }

    public void setChangeVisibilityTime(long changeVisibilityTime) {
        this.changeVisibilityTime = changeVisibilityTime;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isPlayerVisible() {
        return playerVisible;
    }

    public void setPlayerVisible(boolean playerVisible) {
        this.playerVisible = playerVisible;
    }

    public void switchPlayerVisibilty() {

        long remainingTimeBeforeChange = getRemainingTimeBeforeChange();

        if (remainingTimeBeforeChange <= 0) {
            if (isPlayerVisible()) {

                setPlayerVisible(false);

                pluginEraClock.getServer().getOnlinePlayers().stream().filter(p -> !p.getUniqueId().equals(getUuid())).forEach(player::hidePlayer);
                player.sendMessage("§cLes joueurs viennent de disparaître !");
            } else {

                setPlayerVisible(true);

                pluginEraClock.getServer().getOnlinePlayers().stream().filter(p -> !p.getUniqueId().equals(getUuid())).forEach(player::showPlayer);
                player.sendMessage("§aLes joueurs viennent de réapparaitre !");
            }

            setChangeVisibilityTime(new Date().getTime());
        } else {
            player.sendMessage("§cMerci de patienter " + ((double) remainingTimeBeforeChange / 1000) + " secondes avant de réutiliser cette option !");
        }
    }
}
