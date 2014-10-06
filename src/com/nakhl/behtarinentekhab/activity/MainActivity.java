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

import roboguice.inject.InjectView;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.nakhl.behtarinentekhab.R;

/**
 * Main application activity.
 * 
 * @author Maciej Laskowski
 * 
 */
// test comment
public class MainActivity extends FullScreenActivity {

	/** Start button. */
	@InjectView(R.id.buttonStart)
	private Button buttonStart;

	/** More games button. */
	@InjectView(R.id.buttonMoreGames)
	private Button buttonMoreGames;

	/** About button. */
	@InjectView(R.id.buttonAbout)
	private Button buttonAbout;

	/** Exit button */
	@InjectView(R.id.buttonExit)
	private Button buttonExit;

	// private FButton twitterBtn;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initButtons();

		// twitterBtn = (FButton) findViewById(R.id.f_twitter_button);
	}

	/**
	 * Initialize buttons.
	 */
	private void initButtons() {
		// Start quiz
		buttonStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						LevelsActivity.class);
				startActivity(intent);
			}
		});

		// About
		buttonAbout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						AboutActivity.class);
				startActivity(intent);
			}
		});

		// More games
		buttonMoreGames.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(getString(R.string.check_more_games_url)));
				startActivity(browserIntent);
			}
		});

		// Exit application
		findViewById(R.id.buttonExit).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// finish();
				createExitDialog().show();
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		// finish();
		createExitDialog().show();
	}

	/**
	 * Creates exit dialog element.
	 * 
	 * @return exit dialog element
	 */
	private Dialog createExitDialog() {
		return new AlertDialog.Builder(this)
				.setTitle(getString(R.string.exit_dialog_header))
				.setMessage(getString(R.string.exit_dialog_text))
				.setPositiveButton(getString(R.string.positive_ans),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// Exit application
								finish();
							}
						})
				.setNegativeButton(getString(R.string.negative_ans),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// Do nothing
								return;

							}
						}).create();
	}

}
