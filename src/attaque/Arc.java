package attaque;

import protagoniste.ZoneDeCombat;

public class Arc extends Arme {
	private int nbFlechesRestantes;
	
	public Arc(int nbFlechesRestantes) {
		super(50, "Arc", ZoneDeCombat.AERIEN, ZoneDeCombat.TERRESTRE, ZoneDeCombat.AQUATIQUE);
		this.nbFlechesRestantes = nbFlechesRestantes;
	}
	
	@Override
	public int utiliser() {
		if(this.nbFlechesRestantes > 0)
			this.nbFlechesRestantes--;
		else
			operationnel = false;
		
		return getPointDeDegat();
	}

}
