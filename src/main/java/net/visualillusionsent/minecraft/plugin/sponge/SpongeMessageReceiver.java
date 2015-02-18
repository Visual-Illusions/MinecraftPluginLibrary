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
package net.visualillusionsent.minecraft.plugin.sponge;

import net.visualillusionsent.minecraft.plugin.ModMessageReceiver;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.util.command.CommandSource;

/**
 * Copyright (C) 2014 Visual Illusions Entertainment
 * All Rights Reserved.
 *
 * @author Jason Jones (darkdiplomat)
 */
public class SpongeMessageReceiver implements ModMessageReceiver<CommandSource> {
    private final CommandSource source;

    public SpongeMessageReceiver(CommandSource source) {
        this.source = source;
    }

    @Override
    public void message(String message) {
        source.sendMessage(message);
    }

    @Override
    public String getName() {
        if (source instanceof Player) {
            return ((Player)source).getName();
        }
        return "unknown";
    }

    @Override
    public CommandSource unwrap() {
        return source;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SpongeMessageReceiver || obj instanceof CommandSource)) {
            return false;
        }
        CommandSource cmdsource;
        if (obj instanceof SpongeMessageReceiver) {
            cmdsource = ((SpongeMessageReceiver)obj).unwrap();
        }
        else {
            cmdsource = (CommandSource)obj;
        }
        return source.equals(cmdsource);
    }
}
