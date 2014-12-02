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
package net.visualillusionsent.minecraft.plugin.canary;

import net.canarymod.plugin.Plugin;
import net.visualillusionsent.minecraft.plugin.VisualIllusionsMinecraftPlugin;
import net.visualillusionsent.minecraft.plugin.VisualIllusionsPlugin;
import net.visualillusionsent.minecraft.plugin.integrity.SelfIntegrityChecker;
import net.visualillusionsent.utils.JarUtils;
import net.visualillusionsent.utils.ProgramChecker;
import net.visualillusionsent.utils.ProgramStatus;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.jar.Manifest;
import java.util.logging.Logger;

/**
 * Visual Illusions Canary Plugin extension
 *
 * @author Jason (darkdiplomat)
 */
public abstract class VisualIllusionsCanaryPlugin extends Plugin implements VisualIllusionsPlugin {

    private final ProgramChecker pChecker;
    private final Manifest manifest;
    private final long[] versionArray;
    private final SelfIntegrityChecker sic;
    private final Metrics metrics;
    protected final boolean debug;
    protected final WrappedLogger logger;

    public VisualIllusionsCanaryPlugin() {
        this.manifest = readManifest();
        this.versionArray = createVersionArray();
        this.debug = Boolean.valueOf(System.getProperty("debug.".concat(getPluginName().toLowerCase()), "false"));
        this.pChecker = new ProgramChecker(getPluginName(), getVersionArray(), getStatusURL(), getStatus());
        this.pChecker.setConnectionTimeOut(1500);
        this.logger = new WrappedLogger(getLogman());
        this.sic = new SelfIntegrityChecker(this);
        Metrics temp = null;
        try {
            temp = new Metrics(this);
        }
        catch (IOException e) {
            logger.warning("Metrics failed to start, statistics will no be sent.");
        }
        this.metrics = temp;
    }

    @Override
    public boolean enable() {
        try {
            sic.selfTest();
            VisualIllusionsMinecraftPlugin.checkStatus(this);
            VisualIllusionsMinecraftPlugin.checkVersion(this);
            if (metrics != null) {
                metrics.start();
            }
        }
        catch (Exception ex) {
            // SUPPRESSED
        }
        return true;
    }

    @Override
    public void disable() {
        if (metrics != null) {
            metrics.stop();
        }
    }

    @Override
    public final String getPluginName() {
        if (manifest != null) {
            return manifest.getMainAttributes().getValue("Specification-Title");
        }
        return "UnknownVICanaryPlugin";
    }

    @Override
    public boolean buildSet() {
        return getBuild() != null;
    }


    @Override
    public final String getBuild() {
        if (manifest != null) {
            return manifest.getMainAttributes().getValue("Implementation-Version");
        }
        return null;
    }

    @Override
    public boolean buildTimeSet() {
        return getBuildTime() != null;
    }

    @Override
    public final String getBuildTime() {
        if (manifest != null) {
            return manifest.getMainAttributes().getValue("Build-Time");
        }
        return null;
    }

    @Override
    public final ProgramChecker getProgramChecker() {
        return pChecker;
    }

    @Override
    public boolean wikiURLSet() {
        return getWikiURL() != null;
    }

    @Override
    public final String getWikiURL() {
        if (manifest != null) {
            return manifest.getMainAttributes().getValue("Wiki-URL");
        }
        return null;
    }

    @Override
    public final String getIssuesURL() {
        if (manifest != null) {
            return manifest.getMainAttributes().getValue("Issues-URL");
        }
        return null;
    }

    @Override
    public final String getDevelopers() {
        if (manifest != null) {
            return manifest.getMainAttributes().getValue("Developers");
        }
        return "darkdiplomat";
    }

    @Override
    public final String getCopyYear() {
        if (manifest != null) {
            return manifest.getMainAttributes().getValue("Copyright");
        }
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    @Override
    public final String getPluginVersion() {
        if (manifest != null) {
            return manifest.getMainAttributes().getValue("Specification-Version");
        }
        return "0.0.1-SNAPSHOT";
    }

    @Override
    public final ProgramStatus getStatus() {
        return getPluginVersion().contains("-SNAPSHOT") ? ProgramStatus.SNAPSHOT : ProgramStatus.STABLE;
    }

    @Override
    public final long[] getVersionArray() {
        return versionArray;
    }

    @Override
    public Logger getPluginLogger() {
        return logger;
    }

    private Manifest readManifest() {
        try {
            return JarUtils.getManifest(this.getClass());
        }
        catch (IOException ioex) {
            // SUPPRESSED
        }
        return null;
    }

    private long[] createVersionArray() {
        StringTokenizer tokenizer = new StringTokenizer(getPluginVersion().replace("-SNAPSHOT", ""), ".");
        long[] mmr = new long[tokenizer.countTokens()];
        for (int index = 0; index < mmr.length; index++) {
            mmr[index] = Long.parseLong(tokenizer.nextToken());
        }
        return mmr;
    }

    private URL getStatusURL() {
        if (manifest != null) {
            try {
                return new URL(manifest.getMainAttributes().getValue("Status-URL"));
            }
            catch (MalformedURLException murlex) {
                // SUPPRESSED
            }
        }
        return null;
    }
}
