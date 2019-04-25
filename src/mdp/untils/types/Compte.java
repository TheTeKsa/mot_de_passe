package mdp.untils.types;

import mdp.untils.util.Cryptage;

public class Compte {

	private String login;
	private String keyLogin;
	private String password;
	private String keyPassword;
	
	public Compte(String login, String keyLogin, String password, String keyPassword) {
		this.login = login;
		this.keyLogin = keyLogin;
		this.password = password;
		this.keyPassword = keyPassword;
	}

	public Compte(String login, String password) {
		this(login, Cryptage.generateKey(login), password, Cryptage.generateKey(password));
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
}
