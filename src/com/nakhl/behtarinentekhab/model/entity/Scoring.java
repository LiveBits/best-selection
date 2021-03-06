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

package com.nakhl.behtarinentekhab.model.entity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.nakhl.behtarinentekhab.model.dao.ScoringDao;

/**
 * Class represents scoring for {@link Level}. It is XML
 * element and DB entity in one.
 * 
 * @author Maciej Laskowski
 *  
 */
@Root
@DatabaseTable(daoClass = ScoringDao.class)
public class Scoring {

	public static final String LEVEL_ID_FIELD_NAME = "level_id";

	/** Scoring id. */
	@DatabaseField(generatedId = true)
	private int id;

	/** Points granted for correct answer. */
	@Text(required = true)
	@DatabaseField(canBeNull = false)
	private String result;

	@Attribute
	@DatabaseField(canBeNull = true)
	private int minVal;

	@Attribute
	@DatabaseField(canBeNull = true)
	private int maxVal;
	
	@Attribute
	@DatabaseField(canBeNull = true)
	private int selectedAnswer;

	/** The level to which exercise belongs. */
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = LEVEL_ID_FIELD_NAME)
	private Level level;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public int getMinVal() {
		return minVal;
	}

	public void setMinVal(int minVal) {
		this.minVal = minVal;
	}

	public int getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(int maxVal) {
		this.maxVal = maxVal;
	}
	
	public int getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(int selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Scoring [id=" + id + ", result=" + result + ", minVal=" + minVal + ", maxVal="
				+ maxVal + ", selectedAnswer=" + selectedAnswer + "]";
	}

}
