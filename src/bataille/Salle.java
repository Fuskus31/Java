package bataille;

import protagoniste.ZoneDeCombat;

public class Salle {
	private int numSalle;
	private ZoneDeCombat zone;
	
	public Salle(int numSalle, ZoneDeCombat zone) {
		this.numSalle = numSalle;
		this.zone = zone;
	}

	public int getNumeroSalle() {
		return numSalle;
	}

	public ZoneDeCombat getZone() {
		return zone;
	}

	@Override
	public String toString() {
		return "Salle n°" + numSalle + " de type combat " + zone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numSalle;
		result = prime * result + zone.hashCode();
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salle other = (Salle) obj;
		if (numSalle != other.numSalle)
			return false;
		if (zone != other.zone)
			return false;
		return true;
	}
	
	
}
