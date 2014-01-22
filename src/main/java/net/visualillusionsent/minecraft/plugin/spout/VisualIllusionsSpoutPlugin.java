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
package net.visualillusionsent.minecraft.plugin.spout;

import net.visualillusionsent.minecraft.plugin.VisualIllusionsMinecraftPlugin;
import net.visualillusionsent.minecraft.plugin.VisualIllusionsPlugin;
import net.visualillusionsent.utils.JarUtils;
import net.visualillusionsent.utils.ProgramChecker;
import net.visualillusionsent.utils.ProgramStatus;
import org.spout.api.plugin.Plugin;
import org.spout.cereal.config.yaml.YamlConfiguration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Visual Illusions Spout Plugin extension
 *
 * @author Jason (darkdiplomat)
 */
public abstract class VisualIllusionsSpoutPlugin extends Plugin implements VisualIllusionsPlugin {

    private ProgramChecker pChecker;
    private final YamlConfiguration pluginyml;
    private final boolean debug = Boolean.valueOf(System.getProperty("debug.".concat(getName().toLowerCase()), "false"));

    public VisualIllusionsSpoutPlugin() {
        YamlConfiguration temp = null;
        try {
            JarFile jfile = new JarFile(getJarPath());
            JarEntry pyml = jfile.getJarEntry("properties.yml");
            temp = new YamlConfiguration(jfile.getInputStream(pyml));
            temp.load();
        }
        catch (Exception ex) {
            throw new RuntimeException("Failed to read Visual Illusions Information from plugin.yml");
        }
        this.pluginyml = temp;
        this.pChecker = new ProgramChecker(getDefinedName(), getVersionArray(), getStatusURL(), getStatus());
    }

    @Override
    public void onEnable() {
        VisualIllusionsMinecraftPlugin.checkVersion(this);
        VisualIllusionsMinecraftPlugin.checkStatus(this);
    }

    @Override
    public final String getBuild() {
        return getPluginYML().getChild("build.number").getString("0");
    }

    @Override
    public final String getBuildTime() {
        return getPluginYML().getChild("build.time").getString("19700101-0000");
    }

    @Override
    public final ProgramChecker getProgramChecker() {
        return pChecker;
    }

    @Override
    public final String getWikiURL() {
        return getPluginYML().getChild("website").getString("missing.url");
    }

    @Override
    public final String getIssuesURL() {
        return getPluginYML().getChild("issues.url").getString("missing.url");
    }

    @Override
    public final String getDevelopers() {
        return getPluginYML().getChild("developers").getString("missing.developers");
    }

    @Override
    public final String getCopyYear() {
        return getPluginYML().getChild("copyright.years").getString(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
    }

    @Override
    public final ProgramStatus getStatus() {
        return getDefinedVersion().contains("-SNAPSHOT") ? ProgramStatus.SNAPSHOT : ProgramStatus.STABLE;
    }

    @Override
    public final long[] getVersionArray() {
        long[] mmr = new long[3];
        String[] vbreakdown = getDescription().getVersion().split("\\.");
        mmr[0] = Long.valueOf(vbreakdown[0]);
        mmr[1] = Long.valueOf(vbreakdown[1]);
        mmr[2] = Long.valueOf(vbreakdown[2]);
        return mmr;
    }

    private URL getStatusURL() {
        try {
            return new URL(getPluginYML().getChild("status.url").getString("missing.url"));
        }
        catch (MalformedURLException e) {
            return null;
        }
    }

    // Spout is late to define these properties so we grab them directly from our properties.yml instance
    private String getDefinedName() {
        return getPluginYML().getChild("name").getString("UnknownVISpoutPlugin");
    }

    private String getDefinedVersion() {
        return getPluginYML().getChild("version").getString("0.0.0");
    }
    //

    private YamlConfiguration getPluginYML() {
        return this.pluginyml;
    }

    private String getJarPath() {
        return JarUtils.getJarPath(this.getClass());
    }
}
