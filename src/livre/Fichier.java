package livre;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Fichier implements Livre{

	@Override
	public void ecrire(String message) {
		String chemin = "./src/livre/histoire.txt";
		File writer = new File (chemin);
		try(
			
			FileWriter fichier = new FileWriter(writer, true))
		{
			fichier.write(message);
		}catch(IOException e) {
			System.err.println("Erreur ecriture fichier");
			e.printStackTrace();
		
		}
		
	}

}
