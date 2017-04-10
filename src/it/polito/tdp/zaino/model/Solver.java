package it.polito.tdp.zaino.model;

import java.util.HashSet;
import java.util.Set;

public class Solver {

	private Zaino zaino ;
	
	private int numCalls = 0 ;


	/**
	 * Inizializza un nuovo problema dello zaino
	 */
	public Solver(Zaino z) {
		this.zaino = z ;
	}


	/**
	 * Calcola il costo di una soluzione parziale
	 * 
	 * @param parziale
	 * @return
	 */
	private int costo(Set<Pezzo> parziale) {
		int r = 0;
		for (Pezzo p : parziale) {
			r += p.getCosto();
		}
		return r;
	}

	private int peso(Set<Pezzo> parziale) {
		int r = 0;
		for (Pezzo p : parziale) {
			r += p.getPeso();
		}
		return r;
	}

	private void scegli(Set<Pezzo> parziale, int livello, Set<Pezzo> best) {
		
		numCalls++ ;

		if (costo(parziale) > costo(best)) {
			// WOW!!! trovato una soluzione migliore
			// Devo SALVARLA in 'best'
			
			best.clear();
			best.addAll(parziale) ;
			
			//System.out.println(parziale);
		}

		for (Pezzo p : zaino.getPezzi()) {
			if (!parziale.contains(p)) {
				// 'p' non c'è  ancora, provo a metterlo
				// se non supera la capacità
				if (peso(parziale) + p.getPeso() <= zaino.getCapienza()) {
					// prova a mettere 'p' nello zaino
					parziale.add(p);
					// e delegare la ricerca al livello successivo
					scegli(parziale, livello + 1, best);
					// poi rimetti le cose a posto (togli 'p')
					parziale.remove(p);
				}
			}
		}

	}

	public Set<Pezzo> risolvi() {
		numCalls = 0 ; // numero di chiamate del metodo ricorsivo. 
				
		Set<Pezzo> parziale = new HashSet<Pezzo>();
		Set<Pezzo> best = new HashSet<Pezzo>();
		scegli(parziale, 0, best);
		return best;
	}
	
	public int getNumCalls() {
		return numCalls;
	}


	public static void main(String[] args) {

		Zaino z = new Zaino("Prova", 15);
		z.add(new Pezzo(12, 4, "Verde"));
		z.add(new Pezzo(2, 2, "Azzurro"));
		z.add(new Pezzo(1, 1, "Salmone"));
		z.add(new Pezzo(4, 10, "Giallo"));
		z.add(new Pezzo(1, 2, "Grigio"));
		
		Solver s = new Solver(z) ;

		Set<Pezzo> soluzione = s.risolvi();

		System.out.println(soluzione);
		System.out.println(s.getNumCalls()+ " chiamate");


	}
	
	/**
	 * stiamo usando solo un thread del processore, ma non gli sto facendo usare gli altri core
	 */




}
