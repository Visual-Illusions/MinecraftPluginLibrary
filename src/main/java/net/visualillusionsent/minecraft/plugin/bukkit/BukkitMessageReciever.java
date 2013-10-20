/*
 * This file is part of VIMCPlugin.
 *
 * Copyright © 2013 Visual Illusions Entertainment
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
package net.visualillusionsent.minecraft.plugin.bukkit;

import net.visualillusionsent.minecraft.plugin.ModMessageReceiver;
import org.bukkit.command.CommandSender;

/**
 * Bukkit Message Receiver
 *
 * @author Jason (darkdiplomat)
 */
public class BukkitMessageReciever implements ModMessageReceiver {
    private CommandSender receiver;

    public BukkitMessageReciever(CommandSender receiver) {
        this.receiver = receiver;
    }

    @Override
    public final void message(String message) {
        receiver.sendMessage(message);
    }

    @Override
    public final String getName() {
        return receiver.getName();
    }

    @Override
    public final CommandSender unwrap() {
        return receiver;
    }
}
