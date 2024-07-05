package livre;

import java.io.IOException;

public interface Livre {
	
	public default void ecrire(String message) throws IOException{
		System.out.println(message);
	}

}
