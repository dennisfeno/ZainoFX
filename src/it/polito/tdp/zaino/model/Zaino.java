package it.polito.tdp.zaino.model;

import java.util.ArrayList;
import java.util.List;

public class Zaino {
	
	private String nome ; // nome del problema es. P01
	private int capienza; // peso max sopportato dallo zaino
	private List<Pezzo> pezzi; // pezzi da provare ad inserire

	public Zaino(String nome, int capienza) {
		this.nome = nome ;
		this.capienza = capienza;
		this.pezzi = new ArrayList<Pezzo>();
	}

	
	
	
	@Override
	public String toString() {
		return String.format("Zaino [nome=%s, capienza=%s]", nome, capienza);
	}




	public String getNome() {
		return nome;
	}




	public int getCapienza() {
		return capienza;
	}




	public List<Pezzo> getPezzi() {
		return pezzi;
	}


	/**
	 * Aggiunge un nuovo pezzo al problema dello zaino da risolvere. Il nuovo
	 * pezzo deve essere diverso da quelli esistenti.
	 * 
	 * @param p
	 *            il {@link Pezzo} da aggiungere
	 */
	public void add(Pezzo p) {
		if (!pezzi.contains(p))
			pezzi.add(p);
		else
			throw new IllegalArgumentException("Pezzo duplicato " + p);
	}
	
	public void setPezzi(List<Pezzo> list) {
		pezzi.clear();
		pezzi.addAll(list) ;
	}

}
