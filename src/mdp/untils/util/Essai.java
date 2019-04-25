package mdp.untils.util;

import mdp.untils.types.MotDePasse;

public class Essai {

	public static void main(String[] args) {
		MyFile f = new MyFile("/home/theteksa/Documents/test.txt", ';');
		MotDePasse m = new MotDePasse("moi", "test", "theteksa", "test@gmail.com", "1234");
		
		f.ajouter(m);
		
		String[][] sf = f.getString();
		System.out.println(sf[0][0] + "  " + sf[0][1] + "  " + sf[0][2] + "  " + sf[0][3]);
		System.out.println(Cryptage.decodage(sf[0][0], sf[0][1]) + "      " + Cryptage.decodage(sf[0][2], sf[0][3]));
	}
}
