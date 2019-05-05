package es.eprinsa.redmine.boundary;

import javax.inject.Named;

import com.taskadapter.redmineapi.RedmineManager;

@Named
public interface InfoSesionLocal {

	public RedmineManager getMgr();
	
}
