package it.polito.tdp.zaino.model;

public class TestModelSpeed {
	
	/**
	 * vedo come cresce volta per volta il tempo di esecuzione aggiungendo un pezzo per volta. 
	 * @param args
	 */
	
	public static void main(String args[]) {
		
		Model m = new Model() ;
		
		Zaino grande = null ;
		for(Zaino z: m.getProblemi()) {
			if(z.getNome().equals("P08"))
				grande = z ;
		}
		
		for(int size=0; size<=24; size++) {
			m.selezionaZaino(grande, size);
			m.solve() ;
			System.out.format("Size: %2d: calls %9d, time %8dms\n",
					size, m.getNumCalls(), m.getElapsedTime());
		}
		
	}

}
