package attaque;

import protagoniste.ZoneDeCombat;

public class Epee extends Arme {
	private String nomEpee;
	
	public Epee(String nomEpee) {
		super(80, "Epee", ZoneDeCombat.TERRESTRE, ZoneDeCombat.AQUATIQUE);
		this.nomEpee = nomEpee;
	}

	@Override
	public String toString() {
		return nomEpee + " " + super.toString();
	}

}
