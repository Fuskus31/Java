package attaque;

public abstract class ForceDeCombat {
	private String nom;
	private int pointDeDegat;
	
	protected Boolean operationnel=true;
	
	
	public ForceDeCombat(int pointDeDegat, String nom) {
		this.pointDeDegat = pointDeDegat;
		this.nom = nom;
	}

	public int getPointDeDegat() {
		return pointDeDegat;
	}

	public String getNom() {
		return nom;
	}

	public Boolean isOperationnel() {
		return operationnel;
	}
	
	public int utiliser() {
		return pointDeDegat;
	}
	
	@Override
	public String toString() {
		return "ForceDeCombat [nom=" + nom + ", pointDeDegat=" + pointDeDegat + "]";
	}
}
