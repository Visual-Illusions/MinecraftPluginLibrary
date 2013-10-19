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

import net.canarymod.plugin.Plugin;
import net.visualillusionsent.minecraft.plugin.VisualIllusionsMinecraftPlugin;
import net.visualillusionsent.minecraft.plugin.VisualIllusionsPlugin;
import net.visualillusionsent.utils.ProgramStatus;
import net.visualillusionsent.utils.VersionChecker;

import java.util.Calendar;

/**
 * Visual Illusions Canary Plugin extension
 *
 * @author Jason (darkdiplomat)
 */
public abstract class VisualIllusionsCanaryPlugin extends Plugin implements VisualIllusionsPlugin {

    private final VersionChecker vc;

    public VisualIllusionsCanaryPlugin() {
        this.vc = new VersionChecker(getName(), getVersion(), getBuild(), getVersionCheckURL(), getStatus(), false);
    }

    @Override
    public boolean enable() {
        VisualIllusionsMinecraftPlugin.checkVersion(this);
        VisualIllusionsMinecraftPlugin.checkStatus(this);
        return true;
    }

    @Override
    public final String getBuild() {
        return getCanaryInf().getString("build.number", "0");
    }

    @Override
    public final String getBuildTime() {
        return getCanaryInf().getString("build.time", "19700101-0000");
    }

    @Override
    public final VersionChecker getVersionChecker() {
        return vc;
    }

    @Override
    public final String getWikiURL() {
        return getCanaryInf().getString("wiki.url", "missing.url");
    }

    @Override
    public final String getIssuesURL() {
        return getCanaryInf().getString("issues.url", "missing.url");
    }

    @Override
    public final String getDevelopers() {
        return getCanaryInf().getString("developers", "missing.developers");
    }

    @Override
    public final String getCopyYear() {
        return getCanaryInf().getString("copyright.years", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
    }

    @Override
    public final ProgramStatus getStatus() {
        try {
            return ProgramStatus.valueOf(getCanaryInf().getString("program.status").toUpperCase());
        }
        catch (Exception ex) {
            return ProgramStatus.UNKNOWN;
        }
    }

    private final String getVersionCheckURL() {
        return getCanaryInf().getString("version.check.url", "missing.url");
    }
}
