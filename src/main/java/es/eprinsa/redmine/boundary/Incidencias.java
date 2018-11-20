package es.eprinsa.redmine.boundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Named;

import com.taskadapter.redmineapi.Include;
import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Journal;

import es.eprinsa.redmine.MiIssue;

@Named("incidenciasBean")
public class Incidencias {

	List<MiIssue> lista;
	
	public List<MiIssue> getLista() {
		return lista;
	}

	public void setLista(List<MiIssue> lista) {
		this.lista = lista;
	}

	public Incidencias() throws IOException {
		liberacionesConQuery();
	}

	public void liberacionesConQuery() throws IOException {
//		proyecto MIA = 74
//		queryid = 1211
		InfoSesion info = new InfoSesion();

		Collection<MiIssue> miLista = new ArrayList<MiIssue>();
		List<Issue>listaI = null;
		try {		
			System.out.println("liberacionesConQuery.");
			IssueManager issueManager = info.getMgr().getIssueManager();
			listaI = issueManager.getIssues("74", 1211, Include.journals);
//			setLista(issueManager.getIssues("74", 1211, Include.journals));
			
			for (Issue r: listaI) {
				MiIssue mio = new MiIssue(issueManager, r); 
				miLista.add(mio);
			}
			// habría que recorrer cada issue, hacer un getissuebyid con include.journals y cargarlo
			// todo en un hashmap <issue, getjournals>
			int i = 0;
			for (MiIssue is : miLista) {
//			    System.out.println(is.getIs().toString() + " " + is.getListaJournals().size());
			    for (Journal jo : is.getListaJournals()) {
			    	try {
				    	if (jo.getNotes().contains("#incidenciaspostliberacion")) {
				    		System.out.println(++i + " -----------------------------------------------");
				    		System.out.println(is.getIs().toString()  + " - " + jo.toString() );
				    		is.setDsIncidencia(jo.toString());
				    	}
			    	} catch (NullPointerException e) {
//			    		System.out.println(is.getIs().toString() + " no tiene incidencias");
			    	}
			    }
			}
			
//System.out.println("------------------------------- " + miLista.size());			
//			miLista.stream().filter(i -> i.getListaJournals().stream().anyMatch(j -> j.getNotes().contains("incidenciaspostliberacion")))
//						  .forEach(System.out::println);
//						  .collect(Collecion.toList());
			System.out.println("FIN liberacionesConQuery " );
			
		} catch (RedmineException e) {
			System.out.println("Se ha producido una RedmineExcepción."+ e.getMessage());
		}
		
//		return lista;		
		setLista(new ArrayList<MiIssue>(miLista));
	}		
	
}
