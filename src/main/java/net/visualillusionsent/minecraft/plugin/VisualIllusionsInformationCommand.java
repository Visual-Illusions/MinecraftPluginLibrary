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

import net.visualillusionsent.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base Information Command class
 *
 * @author Jason (darkdiplomat)
 */
public abstract class VisualIllusionsInformationCommand {
    protected final VisualIllusionsPlugin plugin;
    protected final List<String> about;

    protected VisualIllusionsInformationCommand(VisualIllusionsPlugin plugin) {
        this.plugin = plugin;

        ArrayList<String> pre = new ArrayList<String>();
        pre.add(center("§B--- §A".concat(plugin.getName()).concat(" §6v").concat(plugin.getVersion()).concat(" §B---")));
        pre.add("$VERSION_CHECK$");
        pre.add("§BJenkins Build: §A".concat(plugin.getBuild()));
        pre.add("§BBuilt On: §A".concat(plugin.getBuildTime()));
        pre.add("§BDeveloper(s): §A".concat(plugin.getDevelopers()));
        pre.add("§BWebsite: §A".concat(plugin.getWikiURL()));
        pre.add("§BIssues: §A".concat(plugin.getIssuesURL()));

        // Next line should always remain at the end of the About
        pre.add(center(String.format("§BCopyright © %s §AVisual §6I§9l§Bl§4u§As§2i§5o§En§7s §6Entertainment", plugin.getCopyYear())));

        about = Collections.unmodifiableList(pre);
    }

    protected final String center(String toCenter) {
        String strColorless = clean(toCenter);
        return StringUtils.padCharLeft(toCenter, (int) (Math.floor(63 - strColorless.length()) / 2), ' ');
    }

    protected abstract VisualIllusionsPlugin getPlugin();

    private String clean(String toClean) {
        return toClean.replaceAll("§[A-FK-NRa-fk-nr0-9]", "");
    }
}
