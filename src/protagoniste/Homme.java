package protagoniste;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

import attaque.Arme;
import attaque.Pouvoir;
import bataille.Bataille;

public class Homme extends EtreVivant {
	Map<ZoneDeCombat, List<Arme>> armes = new EnumMap<>(ZoneDeCombat.class);
	private Arme armeChoisie;

	public Homme(String nom) {
		super(nom, 70);
		for (ZoneDeCombat zone : ZoneDeCombat.values())
			armes.put(zone, new ArrayList<>());
	}
	
	
	public Arme choisirArme(Monstre<? extends Pouvoir> monstre) {
		NavigableSet<Arme> armesTriees = new TreeSet<>(armes.get(monstre.getZoneDeCombat()));
		if (armesTriees.isEmpty()) return null;
		SortedSet<Arme> armeAdaptee = armesTriees.tailSet(new KeyArme(monstre.getForceDeVie()));
		if(armeAdaptee.isEmpty()) {
			armeChoisie = armesTriees.last();
		}
		else {
			armeChoisie = armeAdaptee.first();
		}
		return armeChoisie;
	}
	
	public void ajouterArmes(Arme... armesAAjouter) {
		for(Arme arme : armesAAjouter) {
			for(ZoneDeCombat zone : arme.getZonesDeCombat()) {
				List<Arme> liste = armes.get(zone);
				liste.add(arme);
				armes.put(zone, liste);
			}
		}
	}
	
	public void supprimerArme(Arme armeASupp) {
		for(ZoneDeCombat zone : armeASupp.getZonesDeCombat()) {
			List<Arme> liste = armes.get(zone);
			liste.remove(armeASupp);
			armes.put(zone, liste);
		}
	}
	
	public void rejointBataille(Bataille bataille) {
		this.bataille = bataille;
		this.bataille.ajouter(this);
	}

	@Override
	public void mourir() {
		this.bataille.eliminer(this);
	}

	@Override
	public String toString() {
		return "Homme [nom="+ getNom() +", forceDeVie=" + forceDeVie + "]";
	}

	public Arme getArmeChoisie() {
		return armeChoisie;
	}
	
	
	private static class KeyArme extends Arme {
		public KeyArme (int pointDeDegat) {
			super(pointDeDegat, " ");
		}
	}
}
