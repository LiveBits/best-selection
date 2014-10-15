package com.nakhl.behtarinentekhab.activity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.inject.Inject;
//import com.j256.ormlite.logger.Log;
import com.nakhl.behtarinentekhab.R;
import com.nakhl.behtarinentekhab.model.dao.JobDao;
import com.nakhl.behtarinentekhab.model.dao.LevelDao;
import com.nakhl.behtarinentekhab.model.entity.Exercise;
import com.nakhl.behtarinentekhab.model.entity.Job;
import com.nakhl.behtarinentekhab.model.entity.Level;

public class Model1ResultActivity extends FullScreenActivity {

	@InjectView(R.id.spinnerEducation)
	private Spinner spinnerEducation;

	@InjectView(R.id.editTextTitle1)
	private EditText etTitle1;

	@InjectView(R.id.editTextTitle2)
	private EditText etTitle2;

	@InjectView(R.id.editTextTitle3)
	private EditText etTitle3;

	@InjectView(R.id.textViewJobs)
	private TextView tvJobs;

	@Inject
	private LevelDao levelDao;

	@Inject
	private JobDao jobDao;

	// grade of test's letters
	int v, a, j, m, g, h;

	// person passed test's letters
	String l1, l2, l3, l4;

	// if person has more than three letter in test
	boolean threeLetterMode = true;

	// three lettre mode --> [1,2] or [2,3] or [3,4]
	int type;

	// result of test - suitable jobs based on person shakhsiat!
	String suitableJobs = "";

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_model1_result);

		findLetters();

		initSpinner();

		findJobs(0);

		tvJobs.setMovementMethod(new ScrollingMovementMethod());
	}

	private void findLetters() {

		v = searchForLevel(30).getScore() + searchForLevel(36).getScore()
				+ searchForLevel(42).getScore()
				+ searchForExercise(48, 0).getScore()
				+ searchForExercise(49, 0).getScore();

		a = searchForLevel(31).getScore() + searchForLevel(37).getScore()
				+ searchForLevel(43).getScore()
				+ searchForExercise(48, 1).getScore()
				+ searchForExercise(49, 1).getScore();

		j = searchForLevel(32).getScore() + searchForLevel(38).getScore()
				+ searchForLevel(44).getScore()
				+ searchForExercise(48, 2).getScore()
				+ searchForExercise(49, 2).getScore();

		m = searchForLevel(33).getScore() + searchForLevel(39).getScore()
				+ searchForLevel(45).getScore()
				+ searchForExercise(48, 3).getScore()
				+ searchForExercise(49, 3).getScore();

		h = searchForLevel(34).getScore() + searchForLevel(40).getScore()
				+ searchForLevel(42).getScore()
				+ searchForExercise(48, 4).getScore()
				+ searchForExercise(49, 4).getScore();

		g = searchForLevel(35).getScore() + searchForLevel(41).getScore()
				+ searchForLevel(42).getScore()
				+ searchForExercise(48, 5).getScore()
				+ searchForExercise(49, 5).getScore();

		int iArr[] = { v, a, j, m, h, g };
		String forbiddenArr[] = { "-", "-", "-" };
		// sorting array
		Arrays.sort(iArr);

		l1 = selectIn(iArr, forbiddenArr, 0);
		forbiddenArr[0] = "l1";

		l2 = selectIn(iArr, forbiddenArr, 1);
		forbiddenArr[1] = "l2";

		l3 = selectIn(iArr, forbiddenArr, 2);
		forbiddenArr[2] = "l3";

		l4 = selectIn(iArr, forbiddenArr, 3);

		if (iArr[0] == iArr[1]) {
			threeLetterMode = false;
			type = 0;
		} else if (iArr[1] == iArr[2]) {
			threeLetterMode = false;
			type = 1;
		} else if (iArr[2] == iArr[3]) {
			threeLetterMode = false;
			type = 2;
		} else
			threeLetterMode = true;

//		// only for test
//		l1 = "j";
//		l2 = "g";
//		l3 = "v";
//		l4 = "m";
//		type=0;
//		threeLetterMode = false;

		// set titles
		etTitle1.setText(findPersianEqual(l1));
		etTitle2.setText(findPersianEqual(l2));
		etTitle3.setText(findPersianEqual(l3));

	}

	private CharSequence findPersianEqual(String letter) {

		if (letter == "v")
			return "و";
		else if (letter == "a")
			return "الف";
		else if (letter == "j")
			return "ج";
		else if (letter == "m")
			return "م";
		else if (letter == "g")
			return "ق";
		else
			return "ه";
	}

	private String selectIn(int[] iArr, String[] fArr, int id) {
		int selectedNum = iArr[id];
		if (id == 0) {
			if (selectedNum == v)
				return "v";
			else if (selectedNum == a)
				return "a";
			else if (selectedNum == j)
				return "j";
			else if (selectedNum == m)
				return "m";
			else if (selectedNum == h)
				return "h";
			else
				return "g";
		} else {
			if (selectedNum == v && fArr[id - 1] != "v")
				return "v";
			else if (selectedNum == a && fArr[id - 1] != "a")
				return "a";
			else if (selectedNum == j && fArr[id - 1] != "j")
				return "j";
			else if (selectedNum == m && fArr[id - 1] != "m")
				return "m";
			else if (selectedNum == h && fArr[id - 1] != "h")
				return "h";
			else
				return "g";
		}
	}

	private Level searchForLevel(int id) {

		Level level = null;

		Map<String, Object> args = new HashMap<String, Object>();

		args.put("id", (Object) id);
		try {
			level = levelDao.queryForFieldValues(args).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return level;
	}

	private Exercise searchForExercise(int levelId, int exerciseId) {

		Level level = null;

		Map<String, Object> args = new HashMap<String, Object>();

		args.put("id", (Object) levelId);
		try {
			level = levelDao.queryForFieldValues(args).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collection<Exercise> exercises = level.getExercises();
		List<Exercise> exercisesList = new ArrayList<Exercise>(exercises);

		return exercisesList.get(exerciseId);
	}

	private void findJobs(int educationId) {

		if (threeLetterMode) {
			suitableJobs = ">> اولویت 1" + "\n";
			suitableJobs += selectJobByEdu(l1, l2, l3, educationId);

			suitableJobs += "\n" + ">> اولویت 2" + "\n";
			suitableJobs += selectJobByEdu(l1, l3, l2, educationId);

			suitableJobs += "\n" + ">> اولویت 3" + "\n";
			suitableJobs += selectJobByEdu(l2, l1, l3, educationId);

			suitableJobs += "\n" + ">> اولویت 4" + "\n";
			suitableJobs += selectJobByEdu(l2, l3, l1, educationId);

			suitableJobs += "\n" + ">> اولویت 5" + "\n";
			suitableJobs += selectJobByEdu(l3, l1, l2, educationId);

			suitableJobs += "\n" + ">> اولویت 6" + "\n";
			suitableJobs += selectJobByEdu(l3, l2, l1, educationId);

		} else {

			if (type == 0) {

				suitableJobs = ">> اولویت 1" + "\n";
				suitableJobs += selectJobByEdu(l1, l2, l3, educationId);

				suitableJobs += "\n" + ">> اولویت 2" + "\n";
				suitableJobs += selectJobByEdu(l2, l1, l3, educationId);

				suitableJobs += "\n" + ">> اولویت 3" + "\n";
				suitableJobs += selectJobByEdu(l1, l3, l2, educationId);

				suitableJobs += "\n" + ">> اولویت 4" + "\n";
				suitableJobs += selectJobByEdu(l2, l3, l1, educationId);

				suitableJobs += "\n" + ">> اولویت 5" + "\n";
				suitableJobs += selectJobByEdu(l1, l2, l4, educationId);

				suitableJobs += "\n" + ">> اولویت 6" + "\n";
				suitableJobs += selectJobByEdu(l2, l1, l4, educationId);

				suitableJobs += "\n" + ">> اولویت 7" + "\n";
				suitableJobs += selectJobByEdu(l3, l2, l1, educationId);

				suitableJobs += "\n" + ">> اولویت 8" + "\n";
				suitableJobs += selectJobByEdu(l3, l1, l2, educationId);

			} else if (type == 1) {
				suitableJobs = ">> اولویت 1" + "\n";
				suitableJobs += selectJobByEdu(l1, l2, l3, educationId);

				suitableJobs += "\n" + ">> اولویت 2" + "\n";
				suitableJobs += selectJobByEdu(l1, l3, l2, educationId);

				suitableJobs += "\n" + ">> اولویت 3" + "\n";
				suitableJobs += selectJobByEdu(l1, l2, l4, educationId);

				suitableJobs += "\n" + ">> اولویت 4" + "\n";
				suitableJobs += selectJobByEdu(l1, l3, l4, educationId);

				suitableJobs += "\n" + ">> اولویت 5" + "\n";
				suitableJobs += selectJobByEdu(l2, l1, l3, educationId);

				suitableJobs += "\n" + ">> اولویت 6" + "\n";
				suitableJobs += selectJobByEdu(l3, l1, l2, educationId);

				suitableJobs += "\n" + ">> اولویت 7" + "\n";
				suitableJobs += selectJobByEdu(l2, l1, l4, educationId);

				suitableJobs += "\n" + ">> اولویت 8" + "\n";
				suitableJobs += selectJobByEdu(l3, l1, l4, educationId);

			} else if (type == 2) {
				suitableJobs = ">> اولویت 1" + "\n";
				suitableJobs += selectJobByEdu(l1, l2, l3, educationId);

				suitableJobs += "\n" + ">> اولویت 2" + "\n";
				suitableJobs += selectJobByEdu(l1, l2, l4, educationId);

				suitableJobs += "\n" + ">> اولویت 3" + "\n";
				suitableJobs += selectJobByEdu(l1, l3, l2, educationId);

				suitableJobs += "\n" + ">> اولویت 4" + "\n";
				suitableJobs += selectJobByEdu(l1, l4, l2, educationId);

				suitableJobs += "\n" + ">> اولویت 5" + "\n";
				suitableJobs += selectJobByEdu(l2, l1, l3, educationId);

				suitableJobs += "\n" + ">> اولویت 6" + "\n";
				suitableJobs += selectJobByEdu(l2, l1, l4, educationId);

				suitableJobs += "\n" + ">> اولویت 7" + "\n";
				suitableJobs += selectJobByEdu(l2, l4, l1, educationId);

				suitableJobs += "\n" + ">> اولویت 8" + "\n";
				suitableJobs += selectJobByEdu(l2, l3, l1, educationId);

			}

		}

		tvJobs.setText(suitableJobs);
	}

	private String selectJobByEdu(String letter1, String letter2,
			String letter3, int edu) {

		List<Job> jobs = null;
		String myJobs = "";

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("letter1", (Object) letter1);
		args.put("letter2", (Object) letter2);
		args.put("letter3", (Object) letter3);
		
		if (edu != 0)
			args.put("edu", (Object) edu);

		try {
			jobs = jobDao.queryForFieldValues(args);
		} catch (SQLException e) {
			// show an suitable message
		}

		for (Job job : jobs) {
			myJobs += job.getValue() + "\n";
		}
		return myJobs;
	}

	private void initSpinner() {

		ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
				R.array.educations, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerEducation.setAdapter(adapter);

		spinnerEducation
				.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent, View v,
							int position, long id) {
						switch (spinnerEducation.getSelectedItemPosition()) {
						case 0:
							findJobs(0);
							break;
						case 1:
							findJobs(1);
							break;
						case 2:
							findJobs(2);
							break;
						case 3:
							findJobs(3);
							break;
						case 4:
							findJobs(4);
							break;
						case 5:
							findJobs(5);
							break;
						case 6:
							findJobs(6);
							break;
						case 7:
							findJobs(7);
							break;
						default:
							findJobs(0);
							break;
						}
					}

					public void onNothingSelected(AdapterView<?> parent) {
						// findJobs(0);
					}

				}));
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

	@Override
	public void onBackPressed() {

		Intent intent = new Intent(getApplicationContext(),
				Model1LevelsActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}
}
