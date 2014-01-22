/*
 * This file is part of VIMCPlugin.
 *
 * Copyright © 2013-2014 Visual Illusions Entertainment
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

import net.visualillusionsent.minecraft.plugin.VisualIllusionsMinecraftPlugin;
import net.visualillusionsent.minecraft.plugin.VisualIllusionsPlugin;
import net.visualillusionsent.utils.JarUtils;
import net.visualillusionsent.utils.ProgramChecker;
import net.visualillusionsent.utils.ProgramStatus;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Visual Illusions Bukkit Plugin extension
 *
 * @author Jason (darkdiplomat)
 */
public abstract class VisualIllusionsBukkitPlugin extends JavaPlugin implements VisualIllusionsPlugin {

    private final ProgramChecker pChecker;
    private final YamlConfiguration pluginyml;
    private String majorMinor, revision;
    protected final boolean debug;

    public VisualIllusionsBukkitPlugin() {
        this.pluginyml = new YamlConfiguration();
        try {
            JarFile jfile = new JarFile(getJarPath());
            JarEntry pyml = jfile.getJarEntry("plugin.yml");
            pluginyml.load(jfile.getInputStream(pyml));
        }
        catch (Exception ex) {
            Bukkit.getLogger().warning("Failed to read Visual Illusions Information from plugin.yml");
        }
        this.debug = Boolean.valueOf(System.getProperty("debug.".concat(getDefinedName().toLowerCase()), "false"));
        this.pChecker = new ProgramChecker(getName(), getVersionArray(), getStatusURL(), getStatus());
    }

    @Override
    public void onEnable() {
        VisualIllusionsMinecraftPlugin.checkVersion(this);
        VisualIllusionsMinecraftPlugin.checkStatus(this);
    }

    @Override
    public final String getVersion() {
        return getDescription().getVersion();
    }

    @Override
    public final String getBuild() {
        return getPluginYML().getString("build.number", "0");
    }

    @Override
    public final String getBuildTime() {
        return getPluginYML().getString("build.time", "19700101-0000");
    }

    @Override
    public final ProgramChecker getProgramChecker() {
        return pChecker;
    }

    @Override
    public final String getWikiURL() {
        return getPluginYML().getString("website", "missing.url");
    }

    @Override
    public final String getIssuesURL() {
        return getPluginYML().getString("issues.url", "missing.url");
    }

    @Override
    public final String getDevelopers() {
        return getPluginYML().getString("developers", "missing.developers");
    }

    @Override
    public final String getCopyYear() {
        return getPluginYML().getString("copyright.years", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
    }

    private URL getStatusURL() {
        try {
            return new URL(getPluginYML().getString("status.url", "missing.url"));
        }
        catch (MalformedURLException e) {
            return null;
        }
    }

    @Override
    public final ProgramStatus getStatus() {
        return getDefinedVersion().contains("-SNAPSHOT") ? ProgramStatus.SNAPSHOT : ProgramStatus.STABLE;
    }

    @Override
    public final long[] getVersionArray() {
        long[] mmr = new long[3];
        String[] vbreakdown = getDefinedVersion().replace("-SNAPSHOT", "").split("\\.");
        mmr[0] = Long.valueOf(vbreakdown[0]);
        mmr[1] = Long.valueOf(vbreakdown[1]);
        mmr[2] = Long.valueOf(vbreakdown[2]);
        return mmr;
    }

    private YamlConfiguration getPluginYML() {
        return this.pluginyml;
    }

    private String getJarPath() {
        return JarUtils.getJarPath(getClass());
    }

    // Bukkit is late to define these properties so we grab them directly from our plugin.yml instance
    private String getDefinedName() {
        return getPluginYML().getString("name", "UnknownVIBukkitPlugin");
    }

    private String getDefinedVersion() {
        return getPluginYML().getString("version", "0.0.0");
    }
    //

    /** Terminate plugin with out causing a big scene */
    protected final void die() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new SoftDisable(this));
    }

    private class SoftDisable implements Runnable {

        private final VisualIllusionsBukkitPlugin plugin;

        SoftDisable(VisualIllusionsBukkitPlugin plugin) {
            this.plugin = plugin;
        }

        public final void run() {
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }
}
