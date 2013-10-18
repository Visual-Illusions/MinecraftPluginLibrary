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

import net.visualillusionsent.utils.StringUtils;
import net.visualillusionsent.utils.VersionChecker;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Visual Illusions Bukkit Plugin Information command
 *
 * @author Jason (darkdiplomat)
 */
public abstract class VisualIllusionsBukkitPluginInformationCommand implements CommandExecutor {
    protected final List<String> about;
    protected final VisualIllusionsBukkitPlugin plugin;
    private static final String copyIllusions = "§BCopyright © %s §AVisual §6I§9l§Bl§4u§As§2i§5o§En§7s §6Entertainment";

    public VisualIllusionsBukkitPluginInformationCommand(VisualIllusionsBukkitPlugin plugin) {
        this.plugin = plugin;
        List<String> pre = new ArrayList<String>();
        pre.add(center(ChatColor.AQUA + "---" + ChatColor.GREEN + plugin.getName() + " " + ChatColor.GOLD + "v" + plugin.getDescription().getVersion() + ChatColor.AQUA + " ---"));
        pre.add("$VERSION_CHECK$");
        pre.add(ChatColor.AQUA + "Jenkins Build: " + ChatColor.GREEN + plugin.getBuild());
        pre.add(ChatColor.AQUA + "Built On: " + ChatColor.GREEN + plugin.getBuildTime());
        pre.add(ChatColor.AQUA + "Developer(s): " + ChatColor.GREEN + plugin.getDevelopers());
        pre.add(ChatColor.AQUA + "Website: " + ChatColor.GREEN + plugin.getWikiURL());
        pre.add(ChatColor.AQUA + "Issues: " + ChatColor.GREEN + plugin.getIssuesURL());

        // Next line should always remain at the end of the About
        pre.add(center(String.format(copyIllusions, plugin.getCopyYear())));
        about = Collections.unmodifiableList(pre);
    }

    protected final String center(String toCenter) {
        String strColorless = ChatColor.stripColor(toCenter);
        return StringUtils.padCharLeft(toCenter, (int) (Math.floor(63 - strColorless.length()) / 2), ' ');
    }

    protected final void versionCheck(CommandSender sender) {
        VersionChecker vc = plugin.getVersionChecker();
        Boolean isLatest = vc.isLatest();
        if (isLatest == null) {
            sender.sendMessage(center(ChatColor.DARK_GRAY + "VersionCheckerError: " + vc.getErrorMessage()));
        }
        else if (!isLatest) {
            sender.sendMessage(center(ChatColor.DARK_GRAY + vc.getUpdateAvailibleMessage()));
        }
        else {
            sender.sendMessage(center(ChatColor.GREEN + "Latest Version Installed"));
        }
    }
}
