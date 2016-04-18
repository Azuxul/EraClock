/*
 * Copyright (c) 2015-2016 Azuxul, All Rights Reserved.
 * https://azuxul.fr
 *
 * This software is published under the CeCILL-B license.
 */

package fr.azuxul.eraclock;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command executor of /clock
 *
 * @author Azuxul
 * @version 1.0
 */
public class CommandClock implements CommandExecutor {

    private final PluginEraClock pluginEraClock;

    public CommandClock(PluginEraClock pluginEraClock) {

        this.pluginEraClock = pluginEraClock;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {

            PlayerEraClock playerEraClock = pluginEraClock.getPlayers().get(((Player) sender).getUniqueId());
            playerEraClock.switchPlayerVisibilty();

            return true;
        } else {
            return false;
        }
    }
}
