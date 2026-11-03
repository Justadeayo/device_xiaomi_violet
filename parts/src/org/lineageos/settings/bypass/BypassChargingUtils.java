/*
 * Copyright (C) 2026 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings.bypass;

import android.content.Context;
import android.content.SharedPreferences;

import org.lineageos.settings.utils.FileUtils;

public final class BypassChargingUtils {
    public static final String CHARGING_ENABLED = "/sys/class/power_supply/battery/charging_enabled";
    public static final String INPUT_SUSPEND = "/sys/class/power_supply/dc/input_suspend";

    public static final String PREFS = "bypass_charging";
    public static final String KEY_ENABLED = "enabled";

    private BypassChargingUtils() {
        // This class is not supposed to be instantiated
    }

    public static boolean isSupported() {
        return FileUtils.isFileWritable(CHARGING_ENABLED)
                && FileUtils.isFileWritable(INPUT_SUSPEND);
    }

    /** Bypass is on by default. On = charger powers the phone while the battery is held. */
    public static boolean isEnabled(Context context) {
        return prefs(context).getBoolean(KEY_ENABLED, true);
    }

    public static void setEnabled(Context context, boolean enabled) {
        prefs(context).edit().putBoolean(KEY_ENABLED, enabled).apply();
    }

    public static int readInt(String path) {
        String line = FileUtils.readOneLine(path);
        if (line == null) {
            return -1;
        }
        try {
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }
}
