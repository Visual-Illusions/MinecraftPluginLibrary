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
package net.visualillusionsent.minecraft.plugin.integrity;

import net.visualillusionsent.minecraft.plugin.VisualIllusionsPlugin;
import net.visualillusionsent.utils.JarUtils;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.jar.JarFile;

/**
 * Self Integrity Checking
 * <p/>
 * Relies on the Jar being signed and the root certificate being included in this library
 *
 * @author Jason (darkdiplomat)
 */
public final class SelfIntegrityChecker {
    // Flag for avoiding unnecessary self-integrity checking.
    private boolean verifiedSelfIntegrity = false;
    private final VisualIllusionsPlugin plugin;

    public SelfIntegrityChecker(VisualIllusionsPlugin plugin) {
        this.plugin = plugin;
    }

    public final boolean selfTest() {
        if (verifiedSelfIntegrity) {
            return true;
        }
        X509Certificate providerCert;
        try {
            providerCert = getCertificate();
        }
        catch (Exception ex) {
            plugin.getPluginLogger().warning("Failed to verify self integrity... (Missing Provider Certificate)");
            return false;
        }
        try {
            JarFile jarFile = JarUtils.getJarForClass(SelfIntegrityChecker.class);
            JarVerifier.verify(jarFile, new X509Certificate[]{providerCert});
            verifiedSelfIntegrity = true;
            return true;
        }
        catch (SecurityException sex) {
            plugin.getPluginLogger().warning("Failed to verify self integrity... (" + sex.getMessage() + ")");
        }
        catch (CertificateException e) {
            plugin.getPluginLogger().warning("Failed to verify self integrity... (Verification failure)");
        }
        catch (IOException e) {
            plugin.getPluginLogger().warning("Failed to verify self integrity... (IO failure)");
        }
        return false;
    }

    private X509Certificate getCertificate() throws IOException, CertificateException {
        return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(SelfIntegrityChecker.class.getResourceAsStream("/META-INF/certificates/root.cer"));
    }

}
