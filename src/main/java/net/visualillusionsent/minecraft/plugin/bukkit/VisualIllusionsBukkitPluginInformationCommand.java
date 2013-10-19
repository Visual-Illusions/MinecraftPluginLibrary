/*
 * This file is part of VIMCPlugin.
 *
 * Copyright © 2013 Visual Illusions Entertainment
 *
 * VIMCPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * VIMCPlugin is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with VIMCPlugin.
 * If not, see http://www.gnu.org/licenses/lgpl.html.
 */
package net.visualillusionsent.minecraft.plugin.bukkit;

import net.visualillusionsent.minecraft.plugin.VisualIllusionsInformationCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Visual Illusions Bukkit Plugin Information command
 *
 * @author Jason (darkdiplomat)
 */
public abstract class VisualIllusionsBukkitPluginInformationCommand extends VisualIllusionsInformationCommand implements CommandExecutor {

    public VisualIllusionsBukkitPluginInformationCommand(VisualIllusionsBukkitPlugin plugin) {
        super(plugin);
    }

    protected final void sendInformation(CommandSender receiver) {
        this.sendInformation(new BukkitMessageReciever(receiver));
    }

    @Override
    protected String[] messageInject() {
        // Implementing plugin can override this to inject messages
        return new String[0];
    }

    @Override
    protected VisualIllusionsBukkitPlugin getPlugin() {
        return (VisualIllusionsBukkitPlugin) plugin;
    }
}
