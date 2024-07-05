package attaque;

public abstract class Pouvoir extends ForceDeCombat{
	private int nbUtilisationPouvoir;
	private int nbUtilisationPouvoirInitial;
	
	
	public Pouvoir(int pointDeDegat, String nom, int nbUtilisationPouvoir) {
		super(pointDeDegat, nom);
		this.nbUtilisationPouvoir = nbUtilisationPouvoir;
	}
	
	public void regenererPouvoir() {
		operationnel = true;
	}
	
	@Override
	public int utiliser() {
		nbUtilisationPouvoir--;
		if(nbUtilisationPouvoir <= 0)
			operationnel = false;
		return getPointDeDegat();
	}
}
