package testsfonctionnels;

import java.util.List;

import attaque.Arc;
import attaque.Boomerang;
import attaque.Epee;
import attaque.Feu;
import attaque.Griffe;
import attaque.LameAcier;
import attaque.LancePierre;
import attaque.Lave;
import attaque.Morsure;
import attaque.Tranchant;
import bataille.Bataille;
import bataille.GroupeHommes;
import protagoniste.Domaine;
import protagoniste.Heros;
import protagoniste.Homme;
import protagoniste.Monstre;
import protagoniste.ZoneDeCombat;

public class TestGestionArmes {

	public static void main(String[] args) {
		Monstre<Tranchant> vampirien1 = new Monstre<>("vampirien", 10, ZoneDeCombat.AERIEN, Domaine.TRANCHANT,
				new Morsure(10));
		Monstre<Tranchant> guillotimort = new Monstre<>("guillotimort", 80, ZoneDeCombat.TERRESTRE, Domaine.TRANCHANT,
				new LameAcier(10), new Griffe());
		Monstre<Feu> aqualave = new Monstre<>("aqualave", 30, ZoneDeCombat.AQUATIQUE, Domaine.FEU, new Lave(5));

		Homme thomas = new Homme("Thomas");
		Homme louis = new Homme("Louis");
		Heros arthur = new Heros("Arthur");
		Heros archibald = new Heros("Archibald");
		Homme alain = new Homme("Alain");

		thomas.ajouterArmes(new LancePierre(), new Boomerang());
		louis.ajouterArmes(new LancePierre(), new Arc(10));
		arthur.ajouterArmes(new Epee("excalibur"), new Arc(30));
		archibald.ajouterArmes(new Epee("excalibur"), new Arc(30));
		alain.ajouterArmes(new Boomerang(), new Arc(10));


		GroupeHommes compagnie = new GroupeHommes();
		compagnie.ajouterHommes(thomas, louis, arthur, archibald, alain);

		
		Bataille bataille = new Bataille();
		bataille.ajouter(aqualave);
		List<Homme> campHomme1 = compagnie.choixCampHomme(bataille);
		System.out.println("Le camps des hommes pour combattre un aqualave (Aquatique, 30) : \n" + campHomme1);
		
		bataille.eliminer(aqualave);
		bataille.ajouter(vampirien1);
		List<Homme> campHomme2 = compagnie.choixCampHomme(bataille);
		System.out.println("Le camps des hommes pour combattre un vampirien (A�rien, 10) : \n" + campHomme2);

		bataille.eliminer(vampirien1);
		bataille.ajouter(guillotimort);
		List<Homme> campHomme3 = compagnie.choixCampHomme(bataille);
		System.out.println("Le camps des hommes pour combattre un guillotimort (Terrestre, 80) : \n" + campHomme3);
		
		
		
		System.out.println("\n\n********TP6********");
		LancePierre lancePierre = new LancePierre();
		Boomerang boomerang = new Boomerang();
		Arc arc1 = new Arc(3);
		Arc arc2 = new Arc(1);
		System.out.println("lancePierre < boomerang ? " + lancePierre.isAfter(boomerang));
		System.out.println("arc1 = arc2 ? " + arc1.isSameAs(arc2));
		System.out.println ("arc2 tire sa seule flèche et n'est plus Opérationnel");
		arc2.utiliser();
		arc2.utiliser();
		System.out.println("arc1 = arc2 ? " + arc1.isSameAs(arc2));
		System.out.println("arc1 > arc2 ? " + arc1.isBefore(arc2));
	}
//		RESULTAT ATTENDU
//		Le camps des hommes pour combattre un aqualave (Aquatique, 30) : 
//		[Heros [nom=Archibald, forceDeVie=100], Heros [nom=Arthur, forceDeVie=100], Homme [nom=Alain, forceDeVie=70]]
//		Le camps des hommes pour combattre un vampirien (A�rien, 10) : 
//		[Homme [nom=Louis, forceDeVie=70], Homme [nom=Thomas, forceDeVie=70], Homme [nom=Alain, forceDeVie=70]]
//		Le camps des hommes pour combattre un guillotimort (Terrestre, 80) : 
//		[Heros [nom=Archibald, forceDeVie=100], Heros [nom=Arthur, forceDeVie=100], Homme [nom=Alain, forceDeVie=70]]

	
		
		
	
}
