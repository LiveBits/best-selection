package com.nakhl.behtarinentekhab.model.entity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.nakhl.behtarinentekhab.model.dao.IntroDao;

/**
 * Class represents an introduction for {@link Intro}. It is XML
 * element and DB entity in one.
 * 
 * @author Ahmad khalilfar
 * 
 */
@Root
@DatabaseTable(daoClass = IntroDao.class)
public class Intro {

	public static final String LEVEL_ID_FIELD_NAME = "level_id";

	/** Intro id. */
	@DatabaseField(generatedId = true)
	private int id;

	/** intro part text. */
	@Text(required = true)
	@DatabaseField(canBeNull = false)
	private String text;	

//	@Attribute
//	@DatabaseField
//	private int maxVal;

	/** The level to which exercise belongs. */
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = LEVEL_ID_FIELD_NAME)
	private Level level;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

//	public int getMaxVal() {
//		return maxVal;
//	}
//
//	public void setMaxVal(int maxVal) {
//		this.maxVal = maxVal;
//	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Scoring [id=" + id + ", text=" + text + "]";
	}

}
