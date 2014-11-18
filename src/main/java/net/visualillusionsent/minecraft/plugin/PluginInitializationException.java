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

/**
 * Plugin Initialization Exception<br/>
 * Thrown if something causes the Plugin to not be able to initialize and run properly
 *
 * @author Jason (darkdiplomat)
 */
public final class PluginInitializationException extends RuntimeException {

    /**
     * Constructs a new Initialization Error with a message
     *
     * @param msg
     *         the message of why the exception was thrown
     */
    public PluginInitializationException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new Initialization Error with a cause
     *
     * @param cause
     *         the {@link Throwable} cause for the exception
     */
    public PluginInitializationException(Throwable cause) {
        super(cause);
    }


    /**
     * Constructs a new Initialization Error with a message and cause
     *
     * @param msg
     *         the message of why the exception was thrown
     * @param cause
     *         the {@link Throwable} cause for the exception
     */
    public PluginInitializationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    private static final long serialVersionUID = 200216102013L;
}
