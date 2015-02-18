/*
 * This file is part of Visual Illusions Minecraft Plugin Base Library.
 *
 * Copyright © 2013-2015 Visual Illusions Entertainment
 *
 * Visual Illusions Minecraft Plugin Base Library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * Visual Illusions Minecraft Plugin Base Library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with Visual Illusions Minecraft Plugin Base Library.
 * If not, see http://www.gnu.org/licenses/lgpl.html.
 */
package net.visualillusionsent.minecraft.plugin.canary;

import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandListener;
import net.visualillusionsent.minecraft.plugin.VisualIllusionsInformationCommand;

/**
 * Visual Illusions Canary Plugin Information command
 *
 * @author Jason (darkdiplomat)
 */
public abstract class VisualIllusionsCanaryPluginInformationCommand extends VisualIllusionsInformationCommand implements CommandListener {

    public VisualIllusionsCanaryPluginInformationCommand(VisualIllusionsCanaryPlugin plugin) {
        super(plugin);
    }

    protected final void sendInformation(MessageReceiver receiver) {
        sendInformation(new CanaryMessageReceiver(receiver));
    }

    @Override
    protected VisualIllusionsCanaryPlugin getPlugin() {
        return (VisualIllusionsCanaryPlugin) plugin;
    }
}
