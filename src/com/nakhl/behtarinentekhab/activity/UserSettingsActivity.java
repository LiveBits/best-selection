package com.nakhl.behtarinentekhab.activity;

import com.nakhl.behtarinentekhab.R;
import com.nakhl.behtarinentekhab.R.xml;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class UserSettingsActivity extends PreferenceActivity {
	private static int prefs = R.xml.settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(prefs);
	}
}
