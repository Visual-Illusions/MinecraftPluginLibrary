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
package net.visualillusionsent.minecraft.plugin.canary;

import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandListener;
import net.visualillusionsent.minecraft.plugin.VisualIllusionsInformationCommand;
import net.visualillusionsent.utils.VersionChecker;

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
        for (String msg : about) {
            if (msg.equals("$VERSION_CHECK$")) {
                VersionChecker vc = plugin.getVersionChecker();
                Boolean isLatest = vc.isLatest();
                if (isLatest == null) {
                    receiver.message(center(Colors.GRAY + "VersionCheckerError: " + vc.getErrorMessage()));
                }
                else if (!isLatest) {
                    receiver.message(center(Colors.GRAY + vc.getUpdateAvailibleMessage()));
                }
                else {
                    receiver.message(center(Colors.LIGHT_GREEN + "Latest Version Installed"));
                }

                messageInject(receiver);
            }
            else {
                receiver.message(msg);
            }
        }
    }

    protected void messageInject(MessageReceiver receiver) {
        // Implementing plugin can override this to inject messages
    }

    @Override
    protected VisualIllusionsCanaryPlugin getPlugin() {
        return (VisualIllusionsCanaryPlugin) plugin;
    }
}
