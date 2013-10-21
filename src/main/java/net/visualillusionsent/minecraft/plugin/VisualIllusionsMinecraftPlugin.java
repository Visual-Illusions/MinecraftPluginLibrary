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
package net.visualillusionsent.minecraft.plugin;

import net.visualillusionsent.utils.VersionChecker;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/** @author Jason (darkdiplomat) */
public final class VisualIllusionsMinecraftPlugin {

    public static void checkStatus(VisualIllusionsPlugin plugin) {
        String statusReport = "%s has declared itself as '%s' build. %s";
        switch (plugin.getStatus()) {
            case UNKNOWN:
                plugin.getPluginLogger().severe(String.format(statusReport, plugin.getName(), "UNKNOWN STATUS", "Use is not advised and could cause damage to your system!"));
                break;
            case ALPHA:
                plugin.getPluginLogger().warning(String.format(statusReport, plugin.getName(), "ALPHA", "Production use is not advised!"));
                break;
            case BETA:
                plugin.getPluginLogger().warning(String.format(statusReport, plugin.getName(), "BETA", "Production use is not advised!"));
                break;
            case RELEASE_CANDIDATE:
                plugin.getPluginLogger().warning(String.format(statusReport, plugin.getName(), "RELEASE CANDIDATE", "Expect some bugs."));
                break;
        }
    }

    public static void checkVersion(VisualIllusionsPlugin plugin) {
        VersionChecker vc = plugin.getVersionChecker();
        if (vc != null) {
            Boolean isLatest = vc.isLatest();
            if (isLatest == null) {
                plugin.getPluginLogger().warning("Version Checker Error: " + vc.getErrorMessage());
            }
            else if (!isLatest) {
                plugin.getPluginLogger().warning(vc.getUpdateAvailableMessage());
                plugin.getPluginLogger().warning(String.format("You can view update info @ %s#ChangeLog", plugin.getWikiURL()));
            }
        }
        else {
            plugin.getPluginLogger().warning("No VersionChecker instance available.");
        }
    }

    public static void getAPI(String pluginName, String api, String version, URL url) {
        String api_location = String.format("lib/%s-%s.jar", api, version);
        File lib = new File(api_location);
        if (!lib.exists()) {
            try {
                URLConnection conn = url.openConnection();
                ReadableByteChannel rbc = Channels.newChannel(conn.getInputStream());
                FileOutputStream fos = new FileOutputStream(lib);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
            catch (Exception ex) {
                Bukkit.getLogger().severe(String.format("[%s] Failed to download %s %s", pluginName, api, version));
            }
        }
    }
}
