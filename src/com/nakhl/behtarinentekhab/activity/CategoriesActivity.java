package com.nakhl.behtarinentekhab.activity;

import roboguice.inject.InjectView;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.nakhl.behtarinentekhab.R;

/**
 * Cats activity.
 * 
 * @author Ahmad khalilfar
 * 
 */
public class CategoriesActivity extends FullScreenActivity {

	/** Start button. */
	@InjectView(R.id.buttonSelf)
	private Button buttonSelf;

	/** More games button. */
	@InjectView(R.id.buttonMarriage)
	private Button buttonMarriage;

	/** About button. */
	@InjectView(R.id.buttonJob)
	private Button buttonJob;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cats);
		initButtons();
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
		
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Initialize buttons.
	 */
	private void initButtons() {
		// Start Self... 
		buttonSelf.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle b = new Bundle();
				b.putInt("type", 1);
				b.putInt("sub", 0);
				
				Intent intent = new Intent(getApplicationContext(),
						LevelsActivity.class);
				intent.putExtras(b);
				
				startActivity(intent);
			}
		});

		buttonMarriage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle b = new Bundle();
				b.putInt("type", 2);
				b.putInt("sub", 0);
				
				Intent intent = new Intent(getApplicationContext(),
						LevelsActivity.class);
				intent.putExtras(b);
				
				startActivity(intent);
			}
		});
		
		buttonJob.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						JobsCategoriesActivity.class);
				startActivity(intent);
			}
		});

	}
	
	@Override
	public void onBackPressed() {
		
		Intent intent = new Intent(getApplicationContext(),
				MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		
	}

}
