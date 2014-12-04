/*
 * This file is part of Visual Illusions Minecraft Plugin Base Library.
 *
 * Copyright © 2013-2014 Visual Illusions Entertainment
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
package net.visualillusionsent.minecraft.plugin;

import net.visualillusionsent.utils.ProgramChecker;
import net.visualillusionsent.utils.TaskManager;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;

/** @author Jason (darkdiplomat) */
public final class VisualIllusionsMinecraftPlugin {

    public static void checkStatus(VisualIllusionsPlugin plugin) {
        String statusReport = "%s has declared itself as '%s'. %s";
        switch (plugin.getStatus()) {
            case UNKNOWN:
                plugin.getPluginLogger().severe(String.format(statusReport, plugin.getPluginName(), "UNKNOWN STATUS", "Use is not advised and could cause damage to your system!"));
                break;
            case ALPHA:
                plugin.getPluginLogger().warning(String.format(statusReport, plugin.getPluginName(), "ALPHA", "Production use is not advised!"));
                break;
            case BETA:
                plugin.getPluginLogger().warning(String.format(statusReport, plugin.getPluginName(), "BETA", "Production use is not advised!"));
                break;
            case SNAPSHOT:
                plugin.getPluginLogger().warning(String.format(statusReport, plugin.getPluginName(), "SNAPSHOT", "Production use is not advised!"));
                break;
            case RELEASE_CANDIDATE:
                plugin.getPluginLogger().warning(String.format(statusReport, plugin.getPluginName(), "RELEASE CANDIDATE", "Expect some bugs."));
                break;
        }
    }

    public static void checkVersion(final VisualIllusionsPlugin plugin) {
        final ProgramChecker programChecker = plugin.getProgramChecker();
        if (programChecker != null) {
            TaskManager.scheduleDelayedTaskInMillis(new Runnable() {
                                                        public void run() {
                                                            ProgramChecker.Status response = programChecker.checkStatus();
                                                            switch (response) {
                                                                case ERROR:
                                                                    plugin.getPluginLogger().warning("Program Checker: " + programChecker.getStatusMessage());
                                                                    break;
                                                                case UPDATE:
                                                                    plugin.getPluginLogger().warning(programChecker.getStatusMessage());
                                                                    plugin.getPluginLogger().warning(String.format("You can view update info @ %s#ChangeLog", plugin.getWikiURL()));
                                                                    break;
                                                            }
                                                        }
                                                    }, 1
                                                   );

        }
        else {
            plugin.getPluginLogger().warning("No ProgramChecker instance available.");
        }
    }

    public static void getLibrary(String pluginName, String lib, String version, URL site, Logger logger) {
        String lib_location = String.format("lib/%s-%s.jar", lib, version);
        File library = new File(lib_location);
        if (!library.exists()) {
            try {
                URLConnection conn = site.openConnection();
                ReadableByteChannel rbc = Channels.newChannel(conn.getInputStream());
                FileOutputStream fos = new FileOutputStream(library);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
            catch (Exception ex) {
                logger.severe(String.format("[%s] Failed to download Library: %s %s", pluginName, lib, version));
            }
        }
    }
}
