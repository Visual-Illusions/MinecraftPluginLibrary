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
package net.visualillusionsent.minecraft.plugin.canary;

import net.canarymod.chat.MessageReceiver;
import net.visualillusionsent.minecraft.plugin.ModMessageReceiver;

/**
 * Canary Message Receiver
 *
 * @author Jason (darkdiplomat)
 */
public class CanaryMessageReceiver implements ModMessageReceiver {
    private final MessageReceiver receiver;

    public CanaryMessageReceiver(MessageReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public final void message(String message) {
        receiver.message(message);
    }

    @Override
    public final String getName() {
        return receiver.getName();
    }

    @Override
    public final MessageReceiver unwrap() {
        return receiver;
    }
}
