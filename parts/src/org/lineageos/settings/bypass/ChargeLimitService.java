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

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.lineageos.settings.utils.FileUtils;

/**
 * Follows the phone's own charge limit, whatever level it is set to.
 * The framework pauses charging by writing battery/charging_enabled = 0.
 * Bypass on (default): leave the charger connected, so the phone is powered
 * while the battery is held. Bypass off: also cut the charger with
 * dc/input_suspend = 1 while the framework is holding the battery.
 */
public class ChargeLimitService extends Service {
    private static final String TAG = "ChargeLimitService";
    private static final long POLL_MS = 30_000;

    private Thread mThread;
    private volatile boolean mRunning;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mThread == null) {
            mRunning = true;
            mThread = new Thread(this::loop, TAG);
            mThread.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mRunning = false;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void loop() {
        while (mRunning) {
            applyOnce();
            try {
                Thread.sleep(POLL_MS);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void applyOnce() {
        if (!BypassChargingUtils.isSupported()) {
            return;
        }
        int chargingEnabled = BypassChargingUtils.readInt(BypassChargingUtils.CHARGING_ENABLED);
        if (chargingEnabled < 0) {
            return;
        }

        boolean frameworkHolding = chargingEnabled == 0;
        boolean cutCharger = frameworkHolding && !BypassChargingUtils.isEnabled(this);
        write(BypassChargingUtils.INPUT_SUSPEND, cutCharger ? "1" : "0");
    }

    private static void write(String path, String value) {
        if (!FileUtils.writeLine(path, value)) {
            Log.w(TAG, "Failed to write " + value + " to " + path);
        }
    }
}
