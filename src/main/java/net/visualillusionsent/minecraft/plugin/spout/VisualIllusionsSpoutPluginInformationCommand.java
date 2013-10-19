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
package net.visualillusionsent.minecraft.plugin.spout;

import net.visualillusionsent.minecraft.plugin.VisualIllusionsInformationCommand;
import net.visualillusionsent.utils.VersionChecker;
import org.spout.api.command.CommandSource;
import org.spout.vanilla.ChatStyle;

/**
 * Visual Illusions Spout Plugin Information command
 *
 * @author Jason (darkdiplomat)
 */
public abstract class VisualIllusionsSpoutPluginInformationCommand extends VisualIllusionsInformationCommand {

    public VisualIllusionsSpoutPluginInformationCommand(VisualIllusionsSpoutPlugin plugin) {
        super(plugin);
    }

    protected final void sendInformation(CommandSource source) {
        for (String msg : about) {
            if (msg.equals("$VERSION_CHECK$")) {
                VersionChecker vc = plugin.getVersionChecker();
                Boolean isLatest = vc.isLatest();
                if (isLatest == null) {
                    source.sendMessage(center(ChatStyle.DARK_GRAY + "VersionCheckerError: " + vc.getErrorMessage()));
                }
                else if (!isLatest) {
                    source.sendMessage(center(ChatStyle.DARK_GRAY + vc.getUpdateAvailibleMessage()));
                }
                else {
                    source.sendMessage(center(ChatStyle.GREEN + "Latest Version Installed"));
                }

                messageInject(source);
            }
            else {
                source.sendMessage(msg);
            }
        }
    }

    protected void messageInject(CommandSource receiver) {
        // Implementing plugin can override this to inject messages
    }

    @Override
    protected VisualIllusionsSpoutPlugin getPlugin() {
        return (VisualIllusionsSpoutPlugin) plugin;
    }
}
