/*
 * Copyright (c) 2013, Maciej Laskowski. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact mlaskowsk@gmail.com if you need additional information
 * or have any questions.
 */

package com.nakhl.behtarinentekhab.activity;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.inject.Inject;
import com.j256.ormlite.stmt.PreparedQuery;
import com.nakhl.behtarinentekhab.R;
import com.nakhl.behtarinentekhab.adapter.LevelsAdapter;
import com.nakhl.behtarinentekhab.model.dao.LevelDao;
import com.nakhl.behtarinentekhab.model.entity.Level;

/**
 * Activity with levels list.
 * 
 * @author Maciej Laskowski
 * 
 */
public class LevelsActivity extends FullScreenActivity {

	/** DAO for Level. */
	@Inject
	private LevelDao lvlDao;

	LinearLayout listContainer, satContainer;

	int type, sub;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_levels);
		List<Level> levels = null;

		// Get type and sub from Bundle
		Bundle b = getIntent().getExtras();
		type = b.getInt("type");
		sub = b.getInt("sub");

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("type", (Object) type);
		args.put("sub", (Object) sub);

		try {
			levels = lvlDao.queryForFieldValues(args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		LevelsAdapter adapter = new LevelsAdapter(this, R.layout.list_item_row,
				levels);
		listContainer = (LinearLayout) findViewById(R.id.listLevelsContainer);

		ListView listView = (ListView) listContainer
				.findViewById(R.id.listLevels);
		listView.setAdapter(adapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		if (type == 1 || type == 2)// not job
		{
			Intent intent = new Intent(getApplicationContext(),
					CategoriesActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		} else if (type == 3)// job - model1
		{
			Intent intent = new Intent(getApplicationContext(),
					Model1LevelsActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		} else if (type == 4)// job - model2
		{
			Intent intent = new Intent(getApplicationContext(),
					Model2LevelsActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		} else if (type == 5)// job - others
		{
			Intent intent = new Intent(getApplicationContext(),
					JobsCategoriesActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
	}

	/* Menu */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_settings) {
			// Intent intent = new Intent(MainActivity.this,
			// AboutActivity.class);
			// startActivity(intent);
		}
		if (item.getItemId() == R.id.action_about) {
			// Intent intent = new Intent(MainActivity.this,
			// NotificationConfig.class);
			// startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
