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
package net.visualillusionsent.minecraft.plugin;

import net.visualillusionsent.utils.FileUtils;
import net.visualillusionsent.utils.JarUtils;
import net.visualillusionsent.utils.LocaleHelper;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author Jason (darkdiplomat)
 */
public abstract class MessageTranslator extends LocaleHelper {
    private static final String langDirFormat = "lang/%s/";

    protected MessageTranslator(VisualIllusionsPlugin plugin, String defaultLocale, boolean updateLang) {
        super(true, getBaseDir(plugin, updateLang), defaultLocale);
    }

    private static String getBaseDir(VisualIllusionsPlugin plugin, boolean updateLang) {
        String lang_dir = String.format(langDirFormat, plugin.getPluginName());
        File dir = new File(lang_dir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            if (!new File(dir, "languages.txt").exists()) {
                FileUtils.cloneFileFromJar(JarUtils.getJarPath(plugin.getClass()), "resources/lang/".concat("languages.txt"), lang_dir.concat("languages.txt"));
            }

            if (!new File(dir, "en_US.lang").exists()) {
                FileUtils.cloneFileFromJar(JarUtils.getJarPath(plugin.getClass()), "resources/lang/".concat("en_US.lang"), lang_dir.concat("en_US.lang"));
            }

            if (updateLang) {
                if (!FileUtils.md5SumMatch(plugin.getClass().getResourceAsStream("/resources/lang/languages.txt"), new FileInputStream(lang_dir.concat("languages.txt")))) {
                    FileUtils.cloneFileFromJar(JarUtils.getJarPath(plugin.getClass()), "resources/lang/".concat("languages.txt"), lang_dir.concat("languages.txt"));
                }
                if (!FileUtils.md5SumMatch(plugin.getClass().getResourceAsStream("/resources/lang/en_US.lang"), new FileInputStream(lang_dir.concat("en_US.lang")))) {
                    FileUtils.cloneFileFromJar(JarUtils.getJarPath(plugin.getClass()), "resources/lang/".concat("en_US.lang"), lang_dir.concat("en_US.lang"));
                }
            }
        }
        catch (Exception ex) {
            throw new PluginInitializationException("Failed to verify and move lang files", ex);
        }
        return lang_dir;
    }

    public String translate(String key, String locale, Object... args) {
        return ChatFormat.formatString(localeTranslate(key, locale, args), "$c");
    }
}
