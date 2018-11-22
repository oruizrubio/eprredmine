package es.eprinsa.redmine.boundary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;

@Named("infoSessionBean")
@Stateful
@SessionScoped
//@ManagedBean
//@SessionScoped
public class InfoSesion {

	RedmineManager mgr;	
	
	public InfoSesion() throws IOException {
		String uri = null; 
		String apiAccessKey = null; 
		System.out.println(new File(".").getAbsolutePath());		
		Properties prop = new Properties();
		InputStream input = null;
//		try { 
			input = new FileInputStream("config.properties");
			
			prop.load(input);
			
			uri = prop.getProperty("URI");
			apiAccessKey = prop.getProperty("token");
			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		setMgr(RedmineManagerFactory.createWithApiKey(uri, apiAccessKey));
		//mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		mgr.setObjectsPerPage(100);		
	}

	public RedmineManager getMgr() {
		return mgr;
	}

	public void setMgr(RedmineManager mgr) {
		this.mgr = mgr;
	}

}
