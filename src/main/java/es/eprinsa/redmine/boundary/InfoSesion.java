package es.eprinsa.redmine.boundary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;


@Named
@SessionScoped
public class InfoSesion implements InfoSesionLocal, Serializable {

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
		
System.out.println("InfoSesion inicializado!!");			
			
		setMgr(RedmineManagerFactory.createWithApiKey(uri, apiAccessKey));
		//mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		mgr.setObjectsPerPage(100);		
	}

	@Override
	public RedmineManager getMgr() {
		return mgr;
	}

	public void setMgr(RedmineManager mgr) {
		this.mgr = mgr;
	}

}
