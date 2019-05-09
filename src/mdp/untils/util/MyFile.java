package mdp.untils.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import mdp.untils.types.Compte;
import mdp.untils.types.MotDePasse;

public class MyFile {
	
	private File file;
	private char separator;
	
	public MyFile(String url, char separator) {
		this.file = new File(url);
		this.separator = separator;
		
		if (!this.file.exists()) {
			try {
				this.file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String[][] getString() {
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(this.file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String[][] result = new String[this.getNbLignes()][this.getNbColonnes()];
		String line;
		
		for (int i = 0; i < result.length; i++) {
			try {
				line = reader.readLine();
				for (int j = 0; j < result[i].length; j++) {
					result[i][j] = line.split(this.separator + "")[j];
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int getNbLignes() {
		BufferedReader reader = null;
		int nb = 0;
		
		try {
			reader = new BufferedReader(new FileReader(this.file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			while (reader.readLine() != null) {
				nb ++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nb;
	}
	
	public int getNbColonnes() {
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(this.file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			return reader.readLine().split(this.separator + "").length;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		} catch (NullPointerException e) {
			return 0;
		}
	}
	
	public void ajouter(Compte c) {
		FileWriter fw = null;
		String[][] lesComptes = this.getString();
		
		try {
			fw = new FileWriter(file);
			for (int i = 0; i < lesComptes.length; i++) {
				fw.write(lesComptes[i][0] + this.separator + lesComptes[i][1] + this.separator + lesComptes[i][2] + this.separator + lesComptes[i][3] + "\n");
			}
			fw.write(Cryptage.codage(c.getLogin(), c.getKeyLogin()) + this.separator + c.getKeyLogin() + this.separator + Cryptage.codage(c.getPassword(), c.getKeyPassword()) + this.separator + c.getKeyPassword() + "\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ajouter(MotDePasse m) {
		FileWriter fw = null;
		String[][] lesMotsDePasse = this.getString();
		
		try {
			fw = new FileWriter(file);
			for (int i = 0; i < lesMotsDePasse.length; i++) {
				fw.write(lesMotsDePasse[i][0] + this.separator + lesMotsDePasse[i][1] + this.separator + lesMotsDePasse[i][2] + this.separator + lesMotsDePasse[i][3] + this.separator + lesMotsDePasse[i][4] + this.separator + lesMotsDePasse[i][5] + this.separator + lesMotsDePasse[i][6] + this.separator + lesMotsDePasse[i][7] + this.separator + lesMotsDePasse[i][8] + this.separator + lesMotsDePasse[i][9] + "\n");
			}
			fw.write(Cryptage.codage(m.getLoginCompte(), m.getKeyLoginCompte()) + this.separator + m.getKeyLoginCompte() + this.separator + Cryptage.codage(m.getNom(), m.getKeyNom()) + this.separator + m.getKeyNom() + this.separator + Cryptage.codage(m.getId(), m.getKeyId()) + this.separator + m.getKeyId() + this.separator + Cryptage.codage(m.getAdresse(), m.getKeyAdresse()) + this.separator + m.getKeyAdresse() + this.separator + Cryptage.codage(m.getMotDePasse(), m.getKeyMotDePasse()) + this.separator + m.getKeyMotDePasse() + "\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void supprimer(MotDePasse m) {
		FileWriter fw = null;
		String[][] lesMotsDePasse = this.getString();
		
		try {
			fw = new FileWriter(file);
			for (int i = 0; i < lesMotsDePasse.length; i++) {
				if (!Cryptage.decodage(lesMotsDePasse[i][2], lesMotsDePasse[i][3]).equals(m.getNom())) {
					fw.write(lesMotsDePasse[i][0] + this.separator + lesMotsDePasse[i][1] + this.separator + lesMotsDePasse[i][2] + this.separator + lesMotsDePasse[i][3] + this.separator + lesMotsDePasse[i][4] + this.separator + lesMotsDePasse[i][5] + this.separator + lesMotsDePasse[i][6] + this.separator + lesMotsDePasse[i][7] + this.separator + lesMotsDePasse[i][8] + this.separator + lesMotsDePasse[i][9] + "\n");

				}
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
