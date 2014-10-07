package com.nakhl.behtarinentekhab.model.dao;

import java.sql.SQLException;
import java.util.List;

import roboguice.inject.InjectResource;
import android.util.Log;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.nakhl.behtarinentekhab.R;
import com.nakhl.behtarinentekhab.model.entity.Intro;
import com.nakhl.behtarinentekhab.quiz.model.DatabaseHelper;

/**
 * Data Access Object for entity {@link Intro}.
 * 
 * @author Ahmad khalilfar
 * 
 */
@Singleton
public class IntroDao extends BaseDaoImpl<Intro, Integer> {

	/** Application name. */
	@InjectResource(R.string.app_name)
	private String applicationName;

	/** Error string. */
	@InjectResource(R.string.error)
	private String errorString;

	public IntroDao() throws SQLException {
		super(DatabaseHelper.getConnectionSrc(), Intro.class);
		this.initialize();
	}

	public IntroDao(ConnectionSource connectionSource, Class<Intro> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	public IntroDao(Class<Intro> dataClass) throws SQLException {
		super(dataClass);
	}

	public IntroDao(ConnectionSource connectionSource, DatabaseTableConfig<Intro> tableConfig) throws SQLException {
		super(connectionSource, tableConfig);
	}

	/* (non-Javadoc)
	 * @see com.j256.ormlite.dao.BaseDaoImpl#queryForId(java.lang.Object)
	 */
	@Override
	public Intro queryForId(Integer id) {
		try {
			return super.queryForId(id);
		} catch (SQLException e) {
			Log.e(applicationName, errorString, e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.j256.ormlite.dao.BaseDaoImpl#update(java.lang.Object)
	 */
	@Override
	public int update(Intro arg0) {
		try {
			return super.update(arg0);
		} catch (SQLException e) {
			Log.e(applicationName, errorString, e);
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see com.j256.ormlite.dao.BaseDaoImpl#queryForAll()
	 */
	@Override
	public List<Intro> queryForAll() {
		try {
			return super.queryForAll();
		} catch (SQLException e) {
			Log.e(applicationName, errorString, e);
			return null;
		}
	}

}

