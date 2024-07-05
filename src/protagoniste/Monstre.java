package protagoniste;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import attaque.Pouvoir;
import bataille.Bataille;

public class Monstre<T extends Pouvoir> extends EtreVivant {
	
	private T[] attaques;
	private ZoneDeCombat zoneDeCombat;
	private Domaine domaine;
	private GestionAttaque gestionAttaque = null;

	@SafeVarargs
	public Monstre(String nom, int forceDeVie, ZoneDeCombat zoneDeCombat, Domaine domaine, T... attaques) {
		super(nom, forceDeVie);
		this.domaine = domaine;
		this.zoneDeCombat = zoneDeCombat;
		this.attaques = attaques;
		gestionAttaque = new GestionAttaque();
	}

	public ZoneDeCombat getZoneDeCombat() {
		return zoneDeCombat;
	}

	public Domaine getDomaine() {
		return domaine;
	}

	@Override
	public String toString() {
		return "Monstre [getNom()="+getNom()+", attaques=" + Arrays.toString(attaques) + ", zoneDeCombat=" + zoneDeCombat + ", getForceDeVie()="+getForceDeVie()+"]";
	}

	public void entreEnCombat() {
		for(T pouvoir : attaques) {
			pouvoir.regenererPouvoir();
		}
	}
	
	public T attaque() {
		if(gestionAttaque.hasNext()) return gestionAttaque.next();
		return null;
	}
	
	public void rejointBataille(Bataille bataille) {
		this.bataille = bataille;
		this.bataille.ajouter(this);
	}
	
	public class GestionAttaque implements Iterator<T>{
		private T[] attaquesPossibles = attaques;
		private int nbAttaquesPossibles = attaques.length;
		
		
		@Override
		public boolean hasNext() {
			for(int i=0; i<nbAttaquesPossibles; i++) {
				if(Boolean.FALSE.equals((attaques[i].isOperationnel()))) {
					attaquesPossibles[i] = attaquesPossibles[nbAttaquesPossibles-1];
					nbAttaquesPossibles--;
				}
			}
			return nbAttaquesPossibles>0;
		}
		
		@Override
		public T next() {
			Random rand = new Random();
			if(hasNext()) {
				int nombreAleatoire = rand.nextInt(nbAttaquesPossibles);
				return attaquesPossibles[nombreAleatoire];
			}
			else throw new NoSuchElementException();
		}
		
		
	}

	@Override
	public void mourir() {
		bataille.eliminer(this);
		
	}
}
