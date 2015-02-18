/*
 * This file is part of Visual Illusions Minecraft Plugin Base Library.
 *
 * Copyright © 2013-2015 Visual Illusions Entertainment
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
package net.visualillusionsent.minecraft.plugin.util;

import net.visualillusionsent.utils.PropertiesFile;

/**
 * Configuration handler for the ProgramChecker
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class ProgramCheckerConfiguration {

    private final PropertiesFile cfg;

    public ProgramCheckerConfiguration(PropertiesFile cfg) {
        if (cfg == null) {
            throw new NullPointerException("Configuration file for ProgramChecker was null");
        }
        this.cfg = cfg;
        this.verify();
    }

    private void verify() {
        cfg.getBoolean("do-check", true);
        cfg.setComments("do-check", "Whether or not to check the Visual Illusions status site for plugin information");
        cfg.getInt("query-interval", 5);
        cfg.setComments("query-interval", "How many minutes between checking the Visual Illusions status site for plugin information");
        cfg.save();
    }

    public final boolean isEnabled() {
        return cfg.getBoolean("do-check", true);
    }

    public final int queryInterval() {
        return cfg.getInt("query-interval", 5);
    }
}
