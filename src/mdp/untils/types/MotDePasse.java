package mdp.untils.types;

import mdp.untils.util.Cryptage;

public class MotDePasse {

	private String loginCompte;
	private String keyLoginCompte;
	private String nom;
	private String keyNom;
	private String id;
	private String keyId;
	private String adresse;
	private String keyAdresse;
	private String motDePasse;
	private String keyMotDePasse;
	
	public MotDePasse(String loginCompte, String keyLoginCompte, String nom, String keyNom, String id, String keyId, String adresse, String keyAdresse, String motDePasse, String keyMotDePasse) {
		this.loginCompte = loginCompte;
		this.keyLoginCompte = keyLoginCompte;
		this.nom = nom;
		this.keyNom = keyNom;
		this.id = id;
		this.keyId = keyId;
		this.adresse = adresse;
		this.keyAdresse = keyAdresse;
		this.motDePasse = motDePasse;
		this.keyMotDePasse = keyMotDePasse;
	}
	
	public MotDePasse(String loginCompte, String nom, String id, String adresse, String motDePasse) {		
		this(loginCompte, Cryptage.generateKey(loginCompte), nom, Cryptage.generateKey(nom), id, Cryptage.generateKey(id), adresse, Cryptage.generateKey(adresse), motDePasse, Cryptage.generateKey(motDePasse));
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getKeyNom() {
		return keyNom;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getKeyId() {
		return keyId;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public String getKeyAdresse() {
		return keyAdresse;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	public String getKeyMotDePasse() {
		return keyMotDePasse;
	}

	public String getLoginCompte() {
		return loginCompte;
	}
	
	public String getKeyLoginCompte() {
		return keyLoginCompte;
	}
}