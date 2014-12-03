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
