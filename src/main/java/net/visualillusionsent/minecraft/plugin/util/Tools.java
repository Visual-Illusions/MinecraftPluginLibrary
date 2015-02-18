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

import java.util.regex.Pattern;

/**
 * Copyright (C) 2014 Visual Illusions Entertainment
 * All Rights Reserved.
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Tools {

    private static final Pattern UUIDPATTERN = Pattern.compile("[0-9A-Fa-f]{8}\\-([0-9A-Fa-f]{4}\\-){3}[0-9A-Fa-f]{12}");
    private static final Pattern USERNAMEPATTERN = Pattern.compile("(\\w+){3,16}");

    public static boolean isUUID(String uuid) {
        return UUIDPATTERN.matcher(uuid).matches();
    }

    public static boolean isUserName(String name) {
        return USERNAMEPATTERN.matcher(name).matches();
    }

}
