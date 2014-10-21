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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import roboguice.inject.InjectView;
import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;
import com.nakhl.behtarinentekhab.R;
import com.nakhl.behtarinentekhab.model.dao.ExerciseDao;
import com.nakhl.behtarinentekhab.model.dao.LevelDao;
import com.nakhl.behtarinentekhab.model.entity.Answer;
import com.nakhl.behtarinentekhab.model.entity.Exercise;
import com.nakhl.behtarinentekhab.model.entity.Level;
import com.nakhl.behtarinentekhab.model.entity.Scoring;

/**
 * This Activity displays level score.
 * 
 * @author Maciej Laskowski
 * 
 */
public class ScoreActivity extends FullScreenActivity {

	@InjectView(R.id.buttonReset)
	private Button levelResetButton;

	@Inject
	private LevelDao levelDao;

	@Inject
	private ExerciseDao exerciseDao;

	private Level level;

	private int type, sub;

	private int ans1 = 0, ans2 = 0, ans3 = 0, ans4 = 0, ans5 = 0;

	int[] e = new int[] { 1, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 2, 1, 2, 2, 1,
			1, 1, 1, 1, 1, 1, 1 };
	int eScore, iScore;

	int[] s = new int[] { 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2,
			2 };
	int sScore, nScore;

	int[] t = new int[] { 1, 2, 2, 2, 2, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 2,
			1, 2, 1, 2, 2, 2 };
	int tScore, fScore;

	int[] j = new int[] { 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1,
			1 };
	int jScore, pScore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		displayScore();
		initButtons();
	}

	private void displayScore() {
		Bundle b = getIntent().getExtras();
		int score = b.getInt("score");
		type = b.getInt("type");
		sub = b.getInt("sub");

		level = levelDao.queryForId(b.getInt("level_id"));

		String value = "";
		TextView tv = (TextView) findViewById(R.id.scoreText);

		if (type == 4) {

			int levelId = b.getInt("level_id");

			if (levelId == 60)// ie
			{
				int index = 0;
				for (Exercise exercise : level.getExercises()) {
					if (exercise.getSelAns() == e[index])
						eScore += exercise.getScore();
					else
						iScore += exercise.getScore();
					index++;
				}

				Log.d("my tag", "i=" + iScore + " e=" + eScore + " index="
						+ index);

				Collection<Scoring> scoring = level.getScorings();
				List<Scoring> scoringsList = new ArrayList<Scoring>(scoring);

				if (iScore >= eScore) {
					value = scoringsList.get(0).getResult();
					level.setScore(0);
					levelDao.update(level);
				} else {
					value = scoringsList.get(1).getResult();
					level.setScore(1);
					levelDao.update(level);
				}
			} else if (levelId == 61) {// ns

				int index = 0;
				for (Exercise exercise : level.getExercises()) {
					if (exercise.getSelAns() == s[index])
						sScore += exercise.getScore();
					else
						nScore += exercise.getScore();
					index++;
				}

				Log.d("my tag", "s=" + sScore + " n=" + nScore + " index="
						+ index);
				Collection<Scoring> scoring = level.getScorings();
				List<Scoring> scoringsList = new ArrayList<Scoring>(scoring);
				if (nScore >= sScore) {
					value = scoringsList.get(0).getResult();
					level.setScore(0);
					levelDao.update(level);
				} else {
					level.setScore(1);
					levelDao.update(level);
					value = scoringsList.get(1).getResult();
				}
			} else if (levelId == 62) {// tf

				int index = 0;
				for (Exercise exercise : level.getExercises()) {
					if (exercise.getSelAns() == t[index])
						tScore += exercise.getScore();
					else
						fScore += exercise.getScore();
					index++;
				}

				Log.d("my tag", "t=" + tScore + " f=" + fScore + " index="
						+ index);
				Collection<Scoring> scoring = level.getScorings();
				List<Scoring> scoringsList = new ArrayList<Scoring>(scoring);
				if (fScore >= tScore) {
					level.setScore(0);
					levelDao.update(level);
					value = scoringsList.get(0).getResult();
				} else {
					level.setScore(1);
					levelDao.update(level);
					value = scoringsList.get(1).getResult();
				}
			} else if (levelId == 63) {// jp

				int index = 0;
				for (Exercise exercise : level.getExercises()) {
					if (exercise.getSelAns() == j[index])
						jScore += exercise.getScore();
					else
						pScore += exercise.getScore();
					index++;
				}

				Log.d("my tag", "j=" + jScore + " p=" + pScore + " index="
						+ index);
				Collection<Scoring> scoring = level.getScorings();
				List<Scoring> scoringsList = new ArrayList<Scoring>(scoring);
				if (pScore >= jScore) {
					value = scoringsList.get(0).getResult();
					level.setScore(0);
					levelDao.update(level);
				} else {
					level.setScore(1);
					levelDao.update(level);
					value = scoringsList.get(1).getResult();
				}
			}

		} else {
			if (level.getMostSelected()) {
				if (level.getMaxAnswer() == 4) {

					for (Exercise exercise : level.getExercises()) {
						switch (exercise.getScore()) {
						case 1:
							ans1++;
							break;
						case 2:
							ans2++;
							break;
						case 3:
							ans3++;
							break;
						case 4:
							ans4++;
							break;
						default:
							break;
						}
					}
					int max = Math.max(Math.max(ans1, ans2),
							Math.max(ans3, ans4));
					int tmp = 0;

					if (max == ans1)
						tmp = 1;
					else if (max == ans2)
						tmp = 2;
					else if (max == ans3)
						tmp = 3;
					else
						tmp = 4;

					for (Scoring myScore : level.getScorings()) {
						int sel = myScore.getSelectedAnswer();
						if (tmp == sel) {
							value = myScore.getResult();
							break;
						}
					}
				} else if (level.getMaxAnswer() == 4) {

					for (Exercise exercise : level.getExercises()) {
						switch (exercise.getScore()) {
						case 1:
							ans1++;
							break;
						case 2:
							ans2++;
							break;
						case 3:
							ans3++;
							break;
						case 4:
							ans4++;
							break;
						case 5:
							ans5++;
							break;
						default:
							break;
						}
					}
					int max = Math.max(Math.max(Math.max(ans1, ans2),
							Math.max(ans3, ans4)), ans5);
					int tmp = 0;

					if (max == ans1)
						tmp = 1;
					else if (max == ans2)
						tmp = 2;
					else if (max == ans3)
						tmp = 3;
					else if (max == ans4)
						tmp = 4;
					else
						tmp = 5;

					for (Scoring myScore : level.getScorings()) {
						int sel = myScore.getSelectedAnswer();
						if (tmp == sel) {
							value = myScore.getResult();
							break;
						}
					}
				}
			} else {

				for (Scoring myScore : level.getScorings()) {
					int min = myScore.getMinVal();
					int max = myScore.getMaxVal();
					if (score >= min && score <= max) {
						value = myScore.getResult();
						break;
					}
				}
			}
		}

		tv.setText(value);
	}

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

	private void initButtons() {
		// Reset level
		levelResetButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				level.setScore(-1);
				for (Exercise exercise : level.getExercises()) {
					exercise.setScore(-1);
					exercise.setSolved(false);
					exerciseDao.update(exercise);
				}
				levelDao.update(level);
				Bundle bundle = new Bundle();
				bundle.putInt("level_id", level.getId());
				bundle.putInt("level_type", type);
				bundle.putInt("level_sub", sub);
				bundle.putInt("counter", 0);
				Intent intent = new Intent(getApplicationContext(),
						ExerciseActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

}
