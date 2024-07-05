package attaque;

import java.util.HashSet;
import java.util.Set;

import protagoniste.ZoneDeCombat;

public abstract class Arme extends ForceDeCombat implements Orderable<Arme>{
	private Set<ZoneDeCombat> zonesDeCombat = new HashSet<>();

	
	public Arme(int pointDeDegat, String nom, ZoneDeCombat... zones) {
		super(pointDeDegat, nom);
		for(ZoneDeCombat z : zones) {
			zonesDeCombat.add(z);
		}
	}


	public Set<ZoneDeCombat> getZonesDeCombat() {
		return zonesDeCombat;
	}
	
	public int compareTo(Arme autre) {
		if(autre.isOperationnel() && this.isOperationnel()) {
			if(this.getPointDeDegat() == autre.getPointDeDegat()) {
				return this.getNom().compareTo(autre.getNom());
			}
			else return autre.getPointDeDegat() - this.getPointDeDegat();
		}
		else if (Boolean.TRUE.equals(this.isOperationnel())) {
			return -1;
		}
		else if (Boolean.TRUE.equals(autre.isOperationnel())) {
			return 1;
		}
		else {
			return this.getNom().compareTo(autre.getNom());
		}
		
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Arme autre) {
			return this.isOperationnel() == autre.isOperationnel() && 
					this.getNom().equals(autre.getNom()) &&
					this.getPointDeDegat() == autre.getPointDeDegat();
		}
		return false;
	}
	
	
}
