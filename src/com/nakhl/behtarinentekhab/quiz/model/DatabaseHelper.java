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

package com.nakhl.behtarinentekhab.quiz.model;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import roboguice.RoboGuice;
import roboguice.inject.InjectResource;
import android.content.Context;
import android.content.res.Resources;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.inject.Inject;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nakhl.behtarinentekhab.QuizApplication;
import com.nakhl.behtarinentekhab.R;
import com.nakhl.behtarinentekhab.model.dao.AnswerDao;
import com.nakhl.behtarinentekhab.model.dao.ExerciseDao;
import com.nakhl.behtarinentekhab.model.dao.IntroDao;
import com.nakhl.behtarinentekhab.model.dao.JobDao;
import com.nakhl.behtarinentekhab.model.dao.LevelDao;
import com.nakhl.behtarinentekhab.model.dao.QuestionDao;
import com.nakhl.behtarinentekhab.model.dao.ScoringDao;
import com.nakhl.behtarinentekhab.model.entity.Answer;
import com.nakhl.behtarinentekhab.model.entity.Exercise;
import com.nakhl.behtarinentekhab.model.entity.Intro;
import com.nakhl.behtarinentekhab.model.entity.Job;
import com.nakhl.behtarinentekhab.model.entity.Level;
import com.nakhl.behtarinentekhab.model.entity.Model1;
import com.nakhl.behtarinentekhab.model.entity.Question;
import com.nakhl.behtarinentekhab.model.entity.Quiz;
import com.nakhl.behtarinentekhab.model.entity.Scoring;

/**
 * Creates database in first application run. Also can
 * return DAO. To enable ORMLite logs execute 'setprop
 * log.tag.ORMLite DEBUG' on device
 * 
 * @author Maciej Laskowski
 * 
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_FILE_NAME = "quizes.sqlite";

	/**
	 * Any time changes are made to database objects, may
	 * have to increase the database version.
	 */
	private static final int DATABASE_VERSION = 1;

	private static DatabaseHelper instance = new DatabaseHelper(QuizApplication.getContext());

	private Context applicationContext;

	@Inject
	private JobDao jobDao;
	
	@Inject
	private LevelDao levelDao;

	@Inject
	private ExerciseDao exerciseDao;

	@Inject
	private QuestionDao questionDao;

	@Inject
	private AnswerDao answerDao;

	@Inject
	private ScoringDao scoringDao;
	
	@Inject
	private IntroDao introDao;

	@InjectResource(R.string.app_name)
	private String applicationName;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
		this.applicationContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		try {
			createDataBase(cs);
			// Inject manually since only RoboActivity can
			// do it by itself.
			RoboGuice.injectMembers(applicationContext, this);
			loadContentToDataBase();
		} catch (SQLException e) {
			Log.e(applicationName, "Can't create database!", e);
			throw new RuntimeException(e);
		} catch (java.sql.SQLException e) {
			Log.e(applicationName, "Error while creating DB!", e);
		} catch (Exception e) {
			Log.e(applicationName, "Cannot load XML to DB!", e);
		}

	}

	private void loadContentToDataBase() throws Exception, java.sql.SQLException {
		Quiz quiz = new XmlDataLoader(applicationContext).loadXml();
		// Children first than parents.
		for (Level level : quiz.getLevels()) {
						
			for (Scoring scoring : level.getScorings()) {
				scoring.setLevel(level);				
				scoringDao.create(scoring);
			}
			
			for (Intro intro : level.getIntros()) {
				intro.setLevel(level);				
				introDao.create(intro);
			}
			
			for (Exercise exercise : level.getExercises()) {
				exercise.setLevel(level);
				questionDao.create(exercise.getQuestion());
				questionDao.refresh(exercise.getQuestion());
				exerciseDao.create(exercise);
				for (Answer answer : exercise.getAnswers()) {
					answer.setExercise(exercise);
					answerDao.create(answer);
				}
			}
			
			levelDao.create(level);
		}
		
		Model1 model1 = new XmlDataLoader(applicationContext).loadXml_Model1();
		// Children first than parents.
		for (Job job : model1.getJobs()) {			
			
			jobDao.create(job);
		}
	}	
	
	private void createDataBase(ConnectionSource cs) throws java.sql.SQLException {
		TableUtils.createTable(cs, Level.class);
		TableUtils.createTable(cs, Exercise.class);
		TableUtils.createTable(cs, Question.class);
		TableUtils.createTable(cs, Answer.class);
		TableUtils.createTable(cs, Scoring.class);
		TableUtils.createTable(cs, Intro.class);
		TableUtils.createTable(cs, Job.class);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource cs, int oldVersion, int newVersion) {
		try {
			List<String> allSql = new ArrayList<String>();
			// DB upgrade can depend on version is upgrading
			// from
			switch (oldVersion) {
			case 1:
				// allSql.add("SQL query 1 here");
				// allSql.add("SQL query 2 here");
			}
			for (String sql : allSql) {
				db.execSQL(sql);
			}
		} catch (SQLException e) {
			Log.e(applicationName, "Exception during DB upgrade!", e);
			throw new RuntimeException(e);
		}

	}

	/**
	 * Returns {@link ConnectionSource} object.
	 * 
	 * @return {@link ConnectionSource} object
	 */
	public static ConnectionSource getConnectionSrc() {
		return instance.getConnectionSource();
	}
}
