package mdp.untils.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import mdp.untils.types.Compte;
import mdp.untils.types.MotDePasse;
import mdp.untils.util.MyFile;

@SuppressWarnings("serial")
public class Main extends JDialog {
	
	private Connexion connexion = new Connexion();
	private NouveauCompte nouveauCompte = new NouveauCompte();
	private static MyFile fileComptes = new MyFile("/home/theteksa/Documents/comptes.txt", ';');
	private static MyFile fileMotsDePasse = new MyFile("/home/theteksa/Documents/mots_de_passe.txt", ';');
	private static Compte[] comptes;
	private static MotDePasse[] motsDePasse;
	
	public Main() {
		this.setTitle("Mot de passe");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setJMenuBar(this.createJMenuBar());
		
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(new BorderLayout());
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
		mnuNouveauCompte.addActionListener(this::mnuNouveauCompte);
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
		mnuNouveauMDP.addActionListener(null);
		mnuMDP.add(mnuNouveauMDP);
		
		JMenuItem mnuSupprimerMDP = new JMenuItem("Supprimer mot de passe");
		mnuSupprimerMDP.setMnemonic('S');
		mnuSupprimerMDP.addActionListener(null);
		mnuMDP.add(mnuSupprimerMDP);
		
		JMenuItem mnuModifierMDP = new JMenuItem("Modifier mot de passe");
		mnuModifierMDP.setMnemonic('M');
		mnuModifierMDP.addActionListener(null);
		mnuMDP.add(mnuModifierMDP);
		
		mnuMDP.addSeparator();
		
		JMenuItem mnuRechercherMDP = new JMenuItem("Rechercher");
		mnuRechercherMDP.setMnemonic('R');
		mnuRechercherMDP.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
		mnuRechercherMDP.addActionListener(null);
		mnuMDP.add(mnuRechercherMDP);
		
		JMenu mnuTrier = new JMenu("Trier");
		mnuTrier.setMnemonic('T');
		
		JMenuItem mnuCroissant = new JMenuItem("Croissant");
		mnuCroissant.setMnemonic('C');
		mnuCroissant.addActionListener(null);
		mnuTrier.add(mnuCroissant);
		
		JMenuItem mnuDecroissant = new JMenuItem("Decroissant");
		mnuDecroissant.setMnemonic('D');
		mnuDecroissant.addActionListener(null);
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
		connexion.setVisible(true);
	}
	
	private void mnuDeconnexionListener(ActionEvent event) {
		JOptionPane.showMessageDialog(null, "test");
		System.out.println("Deconnexion");
	}
	
	private void mnuNouveauCompte(ActionEvent event) {
		nouveauCompte.setVisible(true);
	}
	
	private void mnuExitListener(ActionEvent event) {
		dispose();
	}
	
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		Main frmMain = new Main();
		frmMain.setVisible(true);
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
		MotDePasse[] newMotsDePasse = new MotDePasse[motsDePasse.length + 1];
		
		for (int i = 0; i < motsDePasse.length; i++) {
			newMotsDePasse[i] = motsDePasse[i];
		}
		newMotsDePasse[motsDePasse.length] = m;
		motsDePasse = newMotsDePasse;
		fileMotsDePasse.ajouter(m);
	}
}
