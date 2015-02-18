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
package net.visualillusionsent.minecraft.plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jason (darkdiplomat)
 */
public abstract class CommandTabCompleteUtil {

    public static boolean startsWith(String partial, String possible) {
        return possible.regionMatches(true, 0, partial, 0, partial.length());
    }

    public static List<String> matchTo(String[] args, String[] possible) {
        String lastArg = args[args.length - 1];
        ArrayList<String> matches = new ArrayList<String>();

        for (int index = 0; index < possible.length; index++) {
            if (startsWith(lastArg, possible[index])) {
                matches.add(possible[index]);
            }
        }
        return matches;
    }
}
