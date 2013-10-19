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

import net.visualillusionsent.minecraft.plugin.VisualIllusionsMinecraftPlugin;
import net.visualillusionsent.minecraft.plugin.VisualIllusionsPlugin;
import net.visualillusionsent.utils.JarUtils;
import net.visualillusionsent.utils.ProgramStatus;
import net.visualillusionsent.utils.VersionChecker;
import org.spout.api.plugin.Plugin;
import org.spout.cereal.config.yaml.YamlConfiguration;

import java.util.Calendar;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Visual Illusions Spout Plugin extension
 *
 * @author Jason (darkdiplomat)
 */
public abstract class VisualIllusionsSpoutPlugin extends Plugin implements VisualIllusionsPlugin {

    private VersionChecker vc;
    private YamlConfiguration pluginyml;

    @Override
    public void onEnable() {
        this.vc = new VersionChecker(getName(), getDescription().getVersion(), getBuild(), getVersionCheckURL(), getStatus(), false);
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
    public final VersionChecker getVersionChecker() {
        return vc;
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
        try {
            return ProgramStatus.valueOf(getPluginYML().getChild("program.status").getString().toUpperCase());
        }
        catch (Exception ex) {
            return ProgramStatus.UNKNOWN;
        }
    }

    public final String getVersionCheckURL() {
        return getPluginYML().getChild("version.check.url").getString("missing.url");
    }

    private YamlConfiguration getPluginYML() {
        if (pluginyml == null) {
            try {
                JarFile jfile = new JarFile(getJarPath());
                JarEntry pyml = jfile.getJarEntry("properties.yml");
                pluginyml = new YamlConfiguration(jfile.getInputStream(pyml));
                pluginyml.load();
            }
            catch (Exception ex) {
                getLogger().warning("Failed to read Visual Illusions Information from properties.yml");
            }
        }
        return this.pluginyml;
    }

    private String getJarPath() {
        return JarUtils.getJarPath(this.getClass());
    }
}
