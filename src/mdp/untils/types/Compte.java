package mdp.untils.types;

import java.util.ArrayList;
import java.util.List;

import mdp.untils.util.Cryptage;
import mdp.untils.util.MyFile;

public class Compte {

	private List<MotDePasse> lesMotsDePasse;
	private MyFile fileMotsDePasse;
	private String login;
	private String keyLogin;
	private String password;
	private String keyPassword;
	
	public Compte(String login, String keyLogin, String password, String keyPassword) {
		this.login = login;
		this.keyLogin = keyLogin;
		this.password = password;
		this.keyPassword = keyPassword;
		fileMotsDePasse = new MyFile("/home/theteksa/Documents/" + this.login + ".txt", ';');
		initLesMotsDePasse();
	}

	public Compte(String login, String password) {
		this(login, Cryptage.generateKey(login), password, Cryptage.generateKey(password));
	}
	
	public MyFile getFile() {
		return fileMotsDePasse;
	}
	
	public String getLogin() {
		return login;
	}

	public String getKeyLogin() {
		return keyLogin;
	}

	public String getPassword() {
		return password;
	}

	public String getKeyPassword() {
		return keyPassword;
	}
	
	public void setLesMotsDePasse(MotDePasse[] lesMotsDePasse) {
		List<MotDePasse> mdps = new ArrayList<MotDePasse>();
		
		for (int i = 0; i < lesMotsDePasse.length; i++) {
			mdps.add(lesMotsDePasse[i]);
		}
		this.lesMotsDePasse = mdps;
	}
	
	public List<MotDePasse> getLesMotsDePasse() {
		return lesMotsDePasse;
	}
	
	public void initLesMotsDePasse() {
		String[][] motsDePasseString = this.getFile().getString();
		lesMotsDePasse = new ArrayList<MotDePasse>();
		
		for (int i = 0; i < motsDePasseString.length; i++) {
			lesMotsDePasse.add(new MotDePasse(Cryptage.decodage(motsDePasseString[i][0], motsDePasseString[i][1]), motsDePasseString[i][1], Cryptage.decodage(motsDePasseString[i][2], motsDePasseString[i][3]), motsDePasseString[i][3], Cryptage.decodage(motsDePasseString[i][4], motsDePasseString[i][5]), motsDePasseString[i][5], Cryptage.decodage(motsDePasseString[i][6], motsDePasseString[i][7]), motsDePasseString[i][7], Cryptage.decodage(motsDePasseString[i][8], motsDePasseString[i][9]), motsDePasseString[i][9]));
		}
	}
	
	public void supprimerMotDePasse(MotDePasse mdp) {
		lesMotsDePasse.remove(mdp);
		fileMotsDePasse.supprimer(mdp);
	}
}
