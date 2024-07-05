package protagoniste;


import java.util.Comparator;

import bataille.Bataille;

public abstract class EtreVivant implements Comparable<EtreVivant>{
	protected String nom;
	protected int forceDeVie;
	protected Bataille bataille;
	
	
	
	public EtreVivant(String nom, int forceDeVie) {
		this.nom = nom;
		this.forceDeVie = forceDeVie;
	}


	public String getNom() {
		return nom;
	}


	public int getForceDeVie() {
		return forceDeVie;
	}

	public void rejoindreBataille(Bataille bataille) {
		this.bataille = bataille;
	}
	
	public abstract void mourir();
	
	

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof EtreVivant) {
			EtreVivant etreVivantCompare = (EtreVivant) obj;
			return nom.equals(etreVivantCompare.getNom());
		}
		return false;
	}

	@Override
	public int compareTo(EtreVivant e1) {
		return nom.compareTo(e1.getNom());
		
	}

	@Override
	public String toString() {
		return "EtreVivant [nom=" + nom + ", forceDeVie=" + forceDeVie + "]";
	}
	
	
}
