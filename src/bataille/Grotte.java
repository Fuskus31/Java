package bataille;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import attaque.Pouvoir;
import protagoniste.Monstre;
import protagoniste.ZoneDeCombat;
import protagoniste.ZoneDeCombatNonCompatibleException;

public class Grotte {
	private Map<Salle, List<Salle>> planGrotte = new LinkedHashMap<>();
	private Map<Salle, Bataille> batailles = new HashMap<>();
	private Set<Salle> sallesExplorees = new HashSet<>();
	private int numeroSalleDecisive;
	
	
	public void setNumeroSalleDecisive(int numeroSalleDecisive) {
		this.numeroSalleDecisive = numeroSalleDecisive;
	}
	
	
	public void ajouterSalle(ZoneDeCombat zone, Monstre<? extends Pouvoir> ...monstres) throws ZoneDeCombatNonCompatibleException{
		Salle salle = new Salle(planGrotte.size()+1, zone);
		Bataille bataille = new Bataille();
		for(Monstre<? extends Pouvoir> monstre : monstres) {
			if(zone == monstre.getZoneDeCombat()) monstre.rejointBataille(bataille);
        	else throw new ZoneDeCombatNonCompatibleException("La zone de combat de la salle est de type "+zone+
                    " alors que celle du monstre est "+monstre.getZoneDeCombat());
		}
		List<Salle> list = new ArrayList<>();
		planGrotte.put(salle, list);
		batailles.put(salle, bataille);
	}
	
	
	private Salle trouverSalle(int numeroSalle) {
		int indice = 1;
		Map<Salle, List<Salle>> grotte = planGrotte;
		for(Salle salle : grotte.keySet()) {
			if(indice == numeroSalle) return salle;
			indice++;
		}
		return null;
	}
	
	public void configurerAcces(int numeroSalle, int...numerosSalle){
		Salle salle = trouverSalle(numeroSalle);
		List<Salle> organisationSalles = planGrotte.get(salle);
		for(int numSalleSup : numerosSalle) {
			Salle salleAccessible = trouverSalle(numSalleSup);
			organisationSalles.add(salleAccessible);
		}
	}
	
	
	public boolean salleDecisive(Salle salle) {
		return salle.getNumeroSalle() == numeroSalleDecisive;
	}
	
	public Salle premiereSalle() {
		Salle salle = trouverSalle(1);
		sallesExplorees.add(salle);
		return salle;
	}
	
	
	public Salle salleSuivante(Salle salle) {
		Random rand = new Random();
		List<Salle> salles = new ArrayList<>(planGrotte.get(salle));
		salles.removeAll(sallesExplorees);
		if(salles.isEmpty()) salles = planGrotte.get(salle);
		int num = rand.nextInt(salles.size());
		Salle bonneSalle = salles.get(num);
		sallesExplorees.add(bonneSalle);
		return bonneSalle;
		
	}
	
	
	public String afficherPlanGrotte() {
		  StringBuilder affichage = new StringBuilder();
		  for (Map.Entry<Salle, List<Salle>> entry : planGrotte.entrySet()) {
		   Salle salle = entry.getKey();
		   List<Salle> acces = planGrotte.get(salle);
		   affichage.append("La " + salle + ".\nelle possede " + acces.size() + " acces : ");
		   for (Salle access : acces) {
		    affichage.append(" vers la salle " + access);
		   }
		   Bataille bataille = batailles.get(salle);
		   Camp<Monstre<? extends Pouvoir>> camp = bataille.getCampMonstres();
		   Monstre<?> monstre = camp.selectionner();
		   if (camp.nbCombattants() > 1) {
		    affichage.append("\n" + camp.nbCombattants() + " monstres de type ");
		   } else {
		    affichage.append("\nUn monstre de type ");
		   }
		   affichage.append(monstre.getNom() + " la protege.\n");
		   if (salle.getNumeroSalle() == numeroSalleDecisive) {
		    affichage.append("C'est dans cette salle que se trouve la pierre de sang.\n");
		   }
		   affichage.append("\n");
		  }
		  return affichage.toString();
		}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grotte other = (Grotte) obj;
		if (batailles == null) {
			if (other.batailles != null)
				return false;
		} else if (!batailles.equals(other.batailles))
			return false;
		if (numeroSalleDecisive != other.numeroSalleDecisive)
			return false;
		if (planGrotte == null) {
			if (other.planGrotte != null)
				return false;
		} else if (!planGrotte.equals(other.planGrotte))
			return false;
		if (sallesExplorees == null) {
			if (other.sallesExplorees != null)
				return false;
		} else if (!sallesExplorees.equals(other.sallesExplorees))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + batailles.hashCode();
		result = prime * result + numeroSalleDecisive;
		result = prime * result +  planGrotte.hashCode();
		result = prime * result +  sallesExplorees.hashCode();
		return result;
	}
	
}
