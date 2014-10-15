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
public class JobsCategoriesActivity extends FullScreenActivity {

	/** Start button. */
	@InjectView(R.id.buttonType1)
	private Button buttonType1;

	/** More games button. */
	@InjectView(R.id.buttonType2)
	private Button buttonType2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jobs_cats);
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

		buttonType1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getApplicationContext(),
						Model1LevelsActivity.class);				
				
				startActivity(intent);
			}
		});

		buttonType2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getApplicationContext(),
						LevelsActivity.class);
				
				startActivity(intent);
			}
		});

	}
	
	@Override
	public void onBackPressed() {
		
		Intent intent = new Intent(getApplicationContext(),
				CategoriesActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		
	}

}
