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

import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.SwitchPreferenceCompat;

import org.lineageos.settings.R;

public class BypassChargingFragment extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String PREF_BYPASS = "bypass_charging_pref";

    private SwitchPreferenceCompat mBypassPref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.bypass_charging_settings);

        mBypassPref = findPreference(PREF_BYPASS);
        mBypassPref.setOnPreferenceChangeListener(this);
        mBypassPref.setChecked(BypassChargingUtils.isEnabled(getContext()));
        mBypassPref.setEnabled(BypassChargingUtils.isSupported());
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mBypassPref) {
            BypassChargingUtils.setEnabled(getContext(), (Boolean) newValue);
            return true;
        }
        return false;
    }
}
