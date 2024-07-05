package testsfonctionnels;

import protagoniste.Domaine;
import protagoniste.Monstre;
import protagoniste.ZoneDeCombat;
import attaque.BouleDeFeu;
import attaque.Eclair;
import attaque.Feu;
import attaque.Lave;

public class TestGestionAttaque {

	public static void main(String[] args) {
		Monstre<Feu> dragotenebre = new Monstre<>("dragotenebre", 200, ZoneDeCombat.AERIEN, Domaine.FEU, new BouleDeFeu(4),
				new Lave(1), new Eclair(3));

		System.out.println(dragotenebre);
		dragotenebre.entreEnCombat();
		System.out.println(dragotenebre.attaque());
		System.out.println(dragotenebre.attaque());
		System.out.println(dragotenebre.attaque());
		

		for(int i=0; i<100; i++) {
			System.out.println(dragotenebre.attaque().utiliser());
		}
	}
}
