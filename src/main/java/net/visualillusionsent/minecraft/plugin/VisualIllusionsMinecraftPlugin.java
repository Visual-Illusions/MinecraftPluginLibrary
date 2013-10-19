package net.visualillusionsent.minecraft.plugin;

import net.visualillusionsent.utils.VersionChecker;

/** @author Jason (darkdiplomat) */
public final class VisualIllusionsMinecraftPlugin {

    public static final void checkStatus(VisualIllusionsPlugin plugin) {
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

    public static final void checkVersion(VisualIllusionsPlugin plugin) {
        VersionChecker vc = plugin.getVersionChecker();
        Boolean isLatest = vc.isLatest();
        if (isLatest == null) {
            plugin.getPluginLogger().warning("VersionCheckerError: " + vc.getErrorMessage());
        }
        else if (!isLatest) {
            plugin.getPluginLogger().warning(vc.getUpdateAvailibleMessage());
            plugin.getPluginLogger().warning(String.format("You can view update info @ %s#ChangeLog", plugin.getWikiURL()));
        }
    }
}
