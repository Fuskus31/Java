package livre;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NavigableSet;
import java.util.TreeSet;

import attaque.Feu;
import attaque.Glace;
import attaque.Tornade;
import attaque.Tranchant;
import bataille.Bataille;
import protagoniste.Domaine;
import protagoniste.Heros;
import protagoniste.Homme;
import protagoniste.Monstre;
import protagoniste.ZoneDeCombat;

public class AideEcrivain {
	Bataille bataille;
	
	private NavigableSet<Monstre<?>> monstresDomaineSet = new TreeSet<>(new Comparator<Monstre<?>>()
	{
		public int compare(Monstre<?> m1, Monstre<?> m2) {
			Domaine d1 = m1.getDomaine();
			Domaine d2 = m2.getDomaine();
			return d1.compareTo(d2);
		}
	}
);
	private NavigableSet<Monstre<?>> monstresZoneSet = new TreeSet<>(new Comparator<Monstre<?>>() {

        @Override
        public int compare(Monstre<?> monstre1, Monstre<?> monstre2) {
            ZoneDeCombat zone1 = monstre1.getZoneDeCombat();
            ZoneDeCombat zone2 = monstre2.getZoneDeCombat();
            if(zone1.compareTo(zone2) == 0) {
            	if (monstre1.getForceDeVie() == monstre2.getForceDeVie())
                    return monstre1.compareTo(monstre2);
                if (monstre1.getForceDeVie() > monstre2.getForceDeVie())
                    return -1;
                return 1;
            }
            return zone1.compareTo(zone2);
        }

    });
	
	private NavigableSet<Monstre<?>> monstresDeFeu;
	private NavigableSet<Monstre<?>> monstresDeGlace;
	private NavigableSet<Monstre<?>> monstresTranchants;

	
	public NavigableSet<Monstre<?>> getMonstresDeFeu() {
		updateMonstresDomaine();
		return monstresDeFeu;
	}



	public NavigableSet<Monstre<?>> getMonstresDeGlace() {
		updateMonstresDomaine();
		return monstresDeGlace;
	}



	public NavigableSet<Monstre<?>> getMonstresTranchants() {
		updateMonstresDomaine();
		return monstresTranchants;
	}



	public AideEcrivain(Bataille bataille) {
		this.bataille = bataille;
	}
	
	
	
	public String visualiserForcesHumaines() {
		StringBuilder res = new StringBuilder();
		List<Homme> listeTriee = new LinkedList<>();
		for (Iterator<Homme> iterator = bataille.getCampHumains().iterator(); iterator.hasNext();) {
			Homme homme = iterator.next();
			if (homme instanceof Heros) {
				for (ListIterator<Homme> iterateurHomme = listeTriee.listIterator(); iterateurHomme.hasNext();) {
					Homme hommeTest = iterateurHomme.next();
					if (!(hommeTest instanceof Heros)) {
						iterateurHomme.previous();
						iterateurHomme.add(homme);
						break;
					}
				}
			}
			else listeTriee.add(homme);
		}
		for (Iterator<Homme> it = listeTriee.iterator(); it.hasNext();) {
			Homme homme = it.next();
			if(it.hasNext()) res.append(homme.toString() + ", ");
			else res.append(homme.toString() + ".");
		}
		return res.toString();
	}
	
	
	public String ordreNaturelMonstre() {
		NavigableSet<Monstre<?>> setMonstre = new TreeSet();
		StringBuilder res = new StringBuilder();
		for(Iterator<Monstre<?>> it = bataille.getCampMonstres().iterator(); it.hasNext();) {
			Monstre<?> monstre = it.next();
			setMonstre.add(monstre);
		}
		for(Iterator<Monstre<?>> itMonstre = setMonstre.iterator(); itMonstre.hasNext();) {
			Monstre<?> monstreAfficher = itMonstre.next();
			if(itMonstre.hasNext()) res.append(monstreAfficher.getNom() + ", ");
			else res.append(monstreAfficher.getNom()+".");
		}
		return res.toString();
	}
	
	
	
	private void updateMonstresDomaine() {
		for(Iterator<Monstre<?>> iterator = bataille.getCampMonstres().iterator(); iterator.hasNext();) {
			Monstre<?> monstre = iterator.next();
			monstresDomaineSet.add(monstre);
		}
	}
	
	
	public String ordreMonstreDomaine() {
		StringBuilder res = new StringBuilder();
		updateMonstresDomaine();
		Domaine domaine = Domaine.GLACE;
		for(Iterator<Monstre<?>> itMonstre = monstresDomaineSet.iterator(); itMonstre.hasNext();) {
			Monstre<?> monstreAfficher = itMonstre.next();
			if(monstreAfficher.getDomaine() != domaine) {
				domaine = monstreAfficher.getDomaine();
				res.append("\n" + domaine.toString() + ":\n");
			}
			if(itMonstre.hasNext()) res.append(monstreAfficher.getNom() + ", ");
			else res.append(monstreAfficher.getNom() + ".");
		}
		return res.toString();
	}

	
	private void updateMonstresZone() {
		for(Iterator<Monstre<?>> iterator = bataille.getCampMonstres().iterator(); iterator.hasNext();) {
			Monstre<?> monstre = iterator.next();
			monstresZoneSet.add(monstre);
		}
	}

	
	public String ordreMonstreZone() {
		StringBuilder res = new StringBuilder();
		updateMonstresZone();
		ZoneDeCombat zone = ZoneDeCombat.AQUATIQUE;
		for(Iterator<Monstre<?>> itMonstre = monstresZoneSet.iterator(); itMonstre.hasNext();) {
			Monstre<?> monstreAfficher = itMonstre.next();
			if(monstreAfficher.getZoneDeCombat() != zone) {
				zone = monstreAfficher.getZoneDeCombat();
				res.append("\n" + zone.toString() + ":\n");
			}
			if(itMonstre.hasNext()) res.append(monstreAfficher.getNom() + " : " + monstreAfficher.getForceDeVie() + ", ");
			else res.append(monstreAfficher.getNom() + " : " + monstreAfficher.getForceDeVie() + ".");
		}
		return res.toString();
	}
	
	public Monstre<?> firstMonstreDomaine(Domaine domaine) {
		Monstre<?> res = null;
		for(Monstre<?> monstre : monstresDomaineSet) {
			if(monstre.getDomaine() == domaine) res = monstre;
		}
		return res;
	}
	
	public void initMonstresDeFeu() {
		monstresDeFeu = monstresDomaineSet.headSet(new Monstre<>("a", 0, null, Domaine.GLACE, new Tornade(8)), false);
	}
	
	
	public void initMonstresDeGlace() {
		monstresDeGlace = monstresDomaineSet.subSet(
				new Monstre<>("~", 0, null, Domaine.FEU, new Tornade(8)), false, 
				new Monstre<>("~", 0, null, Domaine.GLACE, new Tornade(8)), false);
	}
	
	
	public void initMonstresTranchant() {
		monstresTranchants = monstresDomaineSet.tailSet(new Monstre<>("~", 0, null, Domaine.GLACE, new Tornade(8)), false);
	}
	
}
