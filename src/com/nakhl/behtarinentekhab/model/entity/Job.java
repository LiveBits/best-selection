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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import android.util.Log;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.nakhl.behtarinentekhab.model.dao.JobDao;
import com.nakhl.behtarinentekhab.model.dao.LevelDao;

/**
 * Model1 Job. It is XML element and DB entity in one.
 *  
 * @author Ahmad khalilfar
 * 
 */
@Root
@DatabaseTable(daoClass = JobDao.class)
public class Job {

	/** Job id. */
	@Attribute
	@DatabaseField(id = true)
	private int id;

	/** Job letter 1. */
	@Attribute
	@DatabaseField(canBeNull = false)
	private String letter1;
	
	/** Job letter 2. */
	@Attribute
	@DatabaseField(canBeNull = false)
	private String letter2;
	
	/** Job letter 3. */
	@Attribute
	@DatabaseField(canBeNull = false)
	private String letter3;
	
	/** Job required edu. */
	@Attribute
	@DatabaseField(canBeNull = false)
	private int edu;
	
	/** job value. */
	@Text
	@DatabaseField(canBeNull = false)
	private String value;	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLetter1() {
		return letter1;
	}

	public void setLetter1(String letter1) {
		this.letter1 = letter1;
	}
	
	public String getLetter2() {
		return letter2;
	}

	public void setLetter2(String letter2) {
		this.letter2 = letter2;
	}
	
	public String getLetter3() {
		return letter3;
	}

	public void setLetter3(String letter3) {
		this.letter3 = letter3;
	}
		
	public int getEdu() {
		return edu;
	}

	public void setEdu(int edu) {
		this.edu = edu;
	}	

	@Override
	public String toString() {
		return "Level [id=" + id + ", value=" + value + ", letter1=" + letter1 + ", letter2=" + letter2 + ", letter3=" + letter3 + ", edu=" + edu ;
	}

}
