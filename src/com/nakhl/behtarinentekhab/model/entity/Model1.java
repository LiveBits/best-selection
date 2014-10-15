package com.nakhl.behtarinentekhab.model.entity;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Root element of Jobs structure. Main XML element.
 * 
 * @author Ahmad khalilfar
 * 
 */
@Root
public class Model1 {

	/** List of jobs. */
	@ElementList
	private List<Job> jobs;

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	@Override
	public String toString() {
		return "Model1 [jobs=" + jobs + "]";
	}

}
