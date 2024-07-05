package bataille;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;

import attaque.Arme;
import attaque.Pouvoir;
import protagoniste.Homme;
import protagoniste.Monstre;

public class GroupeHommes {
	private TreeSet<Homme> groupe = new TreeSet<>();
	
	public void ajouterHommes(Homme...hommes) {
		for(Homme homme : hommes) {
			groupe.add(homme);
		}
	}
	
	private static class ComparateurHommes implements Comparator<Homme>{
		public int compare(Homme homme1, Homme homme2) {
			if(homme1.getForceDeVie() != homme2.getForceDeVie()) {
				return homme2.getForceDeVie() - homme1.getForceDeVie();
			}
			return homme1.compareTo(homme2);
		}	
	}
	
	private static class ComparateurArmes implements Comparator<Arme>{
		private Monstre<? extends Pouvoir> monstre;
		
		public ComparateurArmes(Monstre<? extends Pouvoir> monstre) {
			this.monstre = monstre;
		}
		
		@Override
		public int compare(Arme a1, Arme a2) {
			int forceDeVie = monstre.getForceDeVie();
			int degatArme1 = a1.getPointDeDegat();
			int degatArme2 = a2.getPointDeDegat();
			
			if(degatArme1 != degatArme2) {
				NavigableMap<Integer, Arme> classementForce = new TreeMap<>();
				classementForce.put(degatArme1, a1);
				classementForce.put(degatArme2, a2);
				Map.Entry<Integer, Arme> meilleur = classementForce.floorEntry(forceDeVie);
				if (meilleur == null)
					return degatArme1 - degatArme2;
				else if (meilleur.getValue().equals(a1))
					return -1;
				else 
					return 1;
			} else 
				return a1.compareTo(a2);
				
			}
		}

	public List<Homme> choixCampHomme(Bataille bataille) {
		Camp<Monstre<? extends Pouvoir>> campMonstre = bataille.getCampMonstres();
		Monstre<? extends Pouvoir> monstre = campMonstre.selectionner();
		TreeMap<Arme, Set<Homme>> hommesArmes = new TreeMap<>(new ComparateurArmes(monstre));
		for (Homme h : groupe) {
			Arme meilleureArme = h.choisirArme(monstre);
			if (meilleureArme != null && hommesArmes.containsKey(meilleureArme)) {
				Set<Homme> set = hommesArmes.get(meilleureArme);
				set.add(h);
			} else if (meilleureArme != null) {
				TreeSet<Homme> tSet = new TreeSet<Homme>((homme1,homme2) -> (homme1.getForceDeVie() != homme2.getForceDeVie()) ? 
						(homme2.getForceDeVie() - homme1.getForceDeVie()) : (homme1.compareTo(homme2)));
				tSet.add(h);
				hommesArmes.put(meilleureArme, tSet);
			}
		}
		
		Arme meilleureArme = hommesArmes.firstKey();
		Set<Homme> meilleursHommes = hommesArmes.get(meilleureArme);
		List<Homme> listeHommes = new ArrayList<>(meilleursHommes);
		while(meilleursHommes.size() < 3 && meilleureArme != null) {
			meilleureArme = hommesArmes.higherKey(meilleureArme);
			if (meilleureArme != null) {
				meilleursHommes = hommesArmes.get(meilleureArme);
				listeHommes.addAll(meilleursHommes);
			}
		}
		if (listeHommes.size() < 3) {
			for (Homme h : listeHommes)
				h.rejointBataille(bataille);
			return listeHommes;
		}
		for (int i = 0; i < 3; i++) {
			Homme h = listeHommes.get(i);
			h.rejointBataille(bataille);
		}
		return listeHommes.subList(0, 3);
	}
}

