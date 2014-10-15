package com.nakhl.behtarinentekhab.activity;

import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;
import com.nakhl.behtarinentekhab.R;
import com.nakhl.behtarinentekhab.model.dao.IntroDao;
import com.nakhl.behtarinentekhab.model.dao.LevelDao;
import com.nakhl.behtarinentekhab.model.entity.Intro;
import com.nakhl.behtarinentekhab.model.entity.Level;

/**
 * This Activity displays Introduction with {@link Intro}
 * 
 * @author Ahmad khalilfar
 * 
 */
public class IntroActivity extends FullScreenActivity {

	/** Exercise's level. */
	private Level level;

	/** DAO for Level. */
	@Inject
	private LevelDao lvlDao;

	/** Displayed intro. */
	private Intro intro;

	/** DAO for Intro. */
	@Inject
	private IntroDao introDao;

	/** Intro textview. */
	@InjectView(R.id.textIntro)
	private TextView tvIntro;
	
	/** Next button. */
	@InjectView(R.id.buttonNext)
	private Button buttonNext;

	/** Application name. */
	@InjectResource(R.string.app_name)
	private String applicationName;

	/** Error string. */
	@InjectResource(R.string.error)
	private String errorString;
	
	int levelId, type, sub;
	
	/** Level id flag. */
	private static final String LEVEL_ID = "level_id";
	private static final String LEVEL_TYPE = "level_type";
	private static final String LEVEL_SUB = "level_sub";

	/** Back pressed flag. */
	private static final String BACK_PRESSED = "back_pressed";

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		initButtons();

		// Get levelId from Bundle
		Bundle b = getIntent().getExtras();
		levelId = b.getInt(LEVEL_ID);
		type = b.getInt(LEVEL_TYPE);
		sub = b.getInt(LEVEL_SUB);

		level = lvlDao.queryForId(levelId);
		
		tvIntro.setMovementMethod(new ScrollingMovementMethod());
		tvIntro.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View arg0) {
				shareIntro();
				return false;
			}			
		});
		
		displayIntro();
		
	}
	
	private void shareIntro() {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(Intent.EXTRA_TEXT, tvIntro.getText().toString());
		shareIntent.putExtra(Intent.EXTRA_SUBJECT, "بهترین انتخاب");
		startActivity(Intent.createChooser(shareIntent, "به اشتراک گذاری..."));
	}

	/**
	 * Displays {@link Intro}.
	 * 
	 * @param intro
	 *            element do display
	 */
	private void displayIntro() {
		
		String value = "";
		
		for (Intro intro : level.getIntros()) {
			value += intro.getText() + "\n";
		}
		
		tvIntro.setText(value);		
	}

	/**
	 * Initialize buttons.
	 */
	private void initButtons() {
		// Next
		buttonNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				bundle.putInt(LEVEL_ID, levelId);
				bundle.putInt(LEVEL_TYPE, type);
				bundle.putInt(LEVEL_SUB, sub);
				bundle.putInt("counter", 0);
				
				Intent intent = new Intent(getApplicationContext(),
						ExerciseActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
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
		Bundle b = new Bundle();
		b.putInt("type", type);
		b.putInt("sub", sub);
		
		Intent intent = new Intent(getApplicationContext(),
				LevelsActivity.class);
		intent.putExtras(b);
		
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
}