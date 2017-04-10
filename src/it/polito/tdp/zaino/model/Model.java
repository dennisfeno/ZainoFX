package it.polito.tdp.zaino.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.zaino.db.ZainoDAO;

public class Model {
	
	private List<Zaino> problemi ;
	private Zaino problemaCorrente = null ;
	
	// statistiche
	private int numCalls ;
	private long elapsedTime ;
	
	public Model() {
		ZainoDAO dao = new ZainoDAO() ; // leggimi dal DB l'elenco di tutti gli zaini
		this.problemi = dao.listZaino() ; // listZaino: dammi l'elenco degli zaini
		// se non faccio la new, ho chiaramente il riferimento a questa lista. 
	}

	public List<Zaino> getProblemi() {
		return problemi;
	}

	public Zaino getProblemaCorrente() {
		return problemaCorrente;
	}

	public int getNumCalls() {
		return numCalls;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void selezionaZaino(Zaino z) {
		this.problemaCorrente = z ;
		
		ZainoDAO dao = new ZainoDAO() ;
		List<Pezzo> pezzi = dao.listPezzo(z) ;
		
		z.setPezzi(pezzi);
	}
	
	// variante con numero massimo di pezzi
	public void selezionaZaino(Zaino z, int maxPezzi) {
		this.problemaCorrente = z ;
		
		ZainoDAO dao = new ZainoDAO() ;
		List<Pezzo> pezzi = dao.listPezzo(z) ;
		
		z.setPezzi(new ArrayList<Pezzo>());
		int cnt = 0 ;
		for(Pezzo p: pezzi) {
			if(cnt>=maxPezzi)
				// ne ho già messi max
				break ;
			z.add(p);
			
			cnt++ ;
		}
	}

	
	/**
	 * ho separato la classe zaino con la classe solver
	 * il problema è uno zaino PIENO.
	 */
	
	public Set<Pezzo> solve() {
		Solver s = new Solver(this.problemaCorrente) ; // chiamo la classe solver, il problema è un oggetto di classe zaino 

		long start = System.nanoTime() ;
		Set<Pezzo> soluzione = s.risolvi() ; // qua c'è la chiamata alla funzione ricorsiva
		long stop = System.nanoTime() ;
		this.numCalls = s.getNumCalls() ;
		this.elapsedTime = (stop-start)/1000000 ;
		
		return soluzione ;
	}
	
	

	
	
}
