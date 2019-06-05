package mdp.untils.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

import mdp.untils.types.Compte;
import mdp.untils.types.MotDePasse;
import mdp.untils.util.Cryptage;
import mdp.untils.util.MyFile;

@SuppressWarnings("serial")
public class Main extends JDialog {
	
	private Connexion connexion = new Connexion();
	private NouveauCompte nouveauCompte = new NouveauCompte();
	private NouveauMotDePasse nouveauMotDePasse = new NouveauMotDePasse();
	private Supprimer supprimer = new Supprimer();
	private PreModifier modifier = new PreModifier();
	private Rechercher rechercher = new Rechercher();
	private static MyFile fileComptes = new MyFile(System.getProperty("user.home") + "/.programme/perso/comptes.txt", ';');
	private static Compte[] comptes;
	public static Compte compteCourent = null;
	private static JTable tableauMotsDePasse;
	private static DefaultTableModel model;
	
	public Main() {
		this.setTitle("Mot de passe");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setJMenuBar(this.createJMenuBar());
		
		initTabComptes();
		
		String[] entetes = {"Nom", "ID", "Adresse", "Mot de passe"};
		String[][] donnees = getTabStringMotDePasse();
		
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		model = new DefaultTableModel(donnees, entetes);
		
		tableauMotsDePasse = new JTable(model);
		contentPane.add(new JScrollPane(tableauMotsDePasse), BorderLayout.CENTER);
	}
	
	public static Compte[] getComptes() {
		return comptes;
	}
	
	private JMenuBar createJMenuBar() {
		JMenuBar mnuBar = new JMenuBar();
		
		JMenu mnuCompte = new JMenu("Compte");
		mnuCompte.setMnemonic('C');
		
		JMenuItem mnuConnexion = new JMenuItem("Connexion");
		mnuConnexion.setMnemonic('O');
		mnuConnexion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		mnuConnexion.addActionListener(this::mnuConnexionListener);
		mnuCompte.add(mnuConnexion);
		
		JMenuItem mnuDeconnexion = new JMenuItem("Deconnexion");
		mnuDeconnexion.setMnemonic('I');
		mnuDeconnexion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
		mnuDeconnexion.addActionListener(this::mnuDeconnexionListener);
		mnuCompte.add(mnuDeconnexion);
		
		mnuCompte.addSeparator();
		
		JMenuItem mnuNouveauCompte = new JMenuItem("Nouveau compte");
		mnuNouveauCompte.setMnemonic('N');
		mnuNouveauCompte.addActionListener(this::mnuNouveauCompteListener);
		mnuCompte.add(mnuNouveauCompte);
		
		mnuCompte.addSeparator();
		
		JMenuItem mnuExit = new JMenuItem("Exit");
		mnuExit.setMnemonic('E');
		mnuExit.addActionListener(this::mnuExitListener);
		mnuCompte.add(mnuExit);
		
		mnuBar.add(mnuCompte);
		
		JMenu mnuMDP = new JMenu("Mot de passe");
		mnuMDP.setMnemonic('M');
		
		JMenuItem mnuNouveauMDP = new JMenuItem("Nouveau mot de passe");
		mnuNouveauMDP.setMnemonic('N');
		mnuNouveauMDP.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		mnuNouveauMDP.addActionListener(this::mnuNouveauMotDePasseListener);
		mnuMDP.add(mnuNouveauMDP);
		
		JMenuItem mnuSupprimerMDP = new JMenuItem("Supprimer mot de passe");
		mnuSupprimerMDP.setMnemonic('S');
		mnuSupprimerMDP.addActionListener(this::mnuSupprimerMotDePasseListener);
		mnuMDP.add(mnuSupprimerMDP);
		
		JMenuItem mnuModifierMDP = new JMenuItem("Modifier mot de passe");
		mnuModifierMDP.setMnemonic('M');
		mnuModifierMDP.addActionListener(this::mnuModifierListener);
		mnuMDP.add(mnuModifierMDP);
		
		mnuMDP.addSeparator();
		
		JMenuItem mnuRechercherMDP = new JMenuItem("Rechercher");
		mnuRechercherMDP.setMnemonic('R');
		mnuRechercherMDP.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
		mnuRechercherMDP.addActionListener(this::mnuRechercherListener);
		mnuMDP.add(mnuRechercherMDP);
		
		JMenu mnuTrier = new JMenu("Trier");
		mnuTrier.setMnemonic('T');
		
		JMenuItem mnuCroissant = new JMenuItem("Croissant");
		mnuCroissant.setMnemonic('C');
		mnuCroissant.addActionListener(this::mnuCroissantListener);
		mnuTrier.add(mnuCroissant);
		
		JMenuItem mnuDecroissant = new JMenuItem("Decroissant");
		mnuDecroissant.setMnemonic('D');
		mnuDecroissant.addActionListener(this::mnuDecroissantListener);
		mnuTrier.add(mnuDecroissant);
		
		mnuMDP.add(mnuTrier);
		
		mnuBar.add(mnuMDP);
		
		JMenu mnuHelp = new JMenu("Help");
		mnuHelp.setMnemonic('H');
		
		JMenuItem mnuTuto = new JMenuItem("Tuto");
		mnuTuto.setMnemonic('T');
		mnuTuto.addActionListener(null);
		mnuHelp.add(mnuTuto);
		
		JMenuItem mnuContact = new JMenuItem("Contactez moi");
		mnuContact.setMnemonic('C');
		mnuContact.addActionListener(null);
		mnuHelp.add(mnuContact);
		
		mnuBar.add(mnuHelp);
		
		return mnuBar;
	}
	
	private void mnuConnexionListener(ActionEvent event) {
		if (compteCourent == null) {
			connexion.setVisible(true);
		}
	}
	
	private void mnuDeconnexionListener(ActionEvent event) {
		if (compteCourent != null) {
			clearTableau();
			compteCourent = null;
		}
	}
	
	private void mnuNouveauCompteListener(ActionEvent event) {
		nouveauCompte.setVisible(true);
	}
	
	private void mnuNouveauMotDePasseListener(ActionEvent event) {
		if (compteCourent != null) {
			nouveauMotDePasse.setVisible(true);	
		}
	}
	
	private void mnuSupprimerMotDePasseListener(ActionEvent event) {
		if (compteCourent != null) {
			supprimer.setVisible(true);
		}
	}
	
	private void mnuModifierListener(ActionEvent event) {
		modifier.setVisible(true);
	}
	
	private void mnuRechercherListener(ActionEvent event) {
		rechercher.setVisible(true);
	}
	
	private void mnuCroissantListener(ActionEvent event) {
		MotDePasse.setInvert(false);
		Collections.sort(compteCourent.getLesMotsDePasse());
		clearTableau();
		ajouterDesMotsDePasseAuTableau(compteCourent.getLesMotsDePasse());
	}
	
	private void mnuDecroissantListener(ActionEvent event) {
		MotDePasse.setInvert(true);
		Collections.sort(compteCourent.getLesMotsDePasse());
		clearTableau();
		ajouterDesMotsDePasseAuTableau(compteCourent.getLesMotsDePasse());
	}
	
	private void mnuExitListener(ActionEvent event) {
		dispose();
	}
	
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		Main frmMain = new Main();
		frmMain.setVisible(true);
	}
	
	private void initTabComptes() {
		String[][] comptesString = fileComptes.getString();
		comptes = new Compte[comptesString.length];
		
		for (int i = 0; i < comptesString.length; i++) {
			comptes[i] = new Compte(Cryptage.decodage(comptesString[i][0], comptesString[i][1]), comptesString[i][1], Cryptage.decodage(comptesString[i][2], comptesString[i][3]), comptesString[i][3]);
		}
	}

	public static void addCompte(Compte c) {
		Compte[] newComptes = new Compte[comptes.length + 1];
		
		for (int i = 0; i < comptes.length; i++) {
			newComptes[i] = comptes[i];
		}
		newComptes[comptes.length] = c;
		comptes = newComptes;
		fileComptes.ajouter(c);
	}
	
	public static void addMotDePasse(MotDePasse m) {
		compteCourent.getLesMotsDePasse().add(m);
		compteCourent.getFile().ajouter(m);
	}
	
	public static String[][] getTabStringMotDePasse() {
		if (compteCourent == null) {
			return new String[0][0];
		}
		
		String[][] result = new String[compteCourent.getLesMotsDePasse().size()][4];
		
		for (int i = 0; i < result.length; i++) {
			result[i][0] = compteCourent.getLesMotsDePasse().get(i).getNom();
			result[i][1] = compteCourent.getLesMotsDePasse().get(i).getId();
			result[i][2] = compteCourent.getLesMotsDePasse().get(i).getAdresse();
			result[i][3] = compteCourent.getLesMotsDePasse().get(i).getMotDePasse();
		}
		return result;
	}
	
	public static void ajouterDesMotsDePasseAuTableau(List<MotDePasse> lesMotsDePasse) {
		for (int i = 0; i < lesMotsDePasse.size(); i++) {
			ajouterUnMotDePasseAuTableau(lesMotsDePasse.get(i));
		}
	}
	
	public static void ajouterUnMotDePasseAuTableau(MotDePasse m) {
		Object[] mdpString = {m.getNom(), m.getId(), m.getAdresse(), m.getMotDePasse()};
		
		model.addRow(mdpString);
	}
	
	public static void clearTableau() {
		int cpt = model.getRowCount();
		
		for (int i = 0; i < cpt; i++) {
			model.removeRow(0);
		}
	}
}
