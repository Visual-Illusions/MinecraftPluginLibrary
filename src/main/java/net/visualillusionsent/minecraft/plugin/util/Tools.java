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
