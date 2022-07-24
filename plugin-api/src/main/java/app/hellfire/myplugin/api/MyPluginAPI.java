/* Copyright (c) 2022 - <name> and contributors. All rights reserved.
 *
 * This file is part of Hellfire <plugin name>.
 *
 * Hellfire <plugin name> is free software: you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Hellfire <plugin name> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Hellfire <plugin name>. If not, see
 * <https://www.gnu.org/licenses/>.
 */
package app.hellfire.myplugin.api;

/**
 * The API interface for your plugin. Other Hellfire apps can access functionality using this
 * interface.
 */
public class MyPluginAPI {
    String someCoolMethod() {
        return "Hello, world!";
    }
}
