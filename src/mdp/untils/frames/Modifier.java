package mdp.untils.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mdp.untils.types.MotDePasse;

@SuppressWarnings("serial")
public class Modifier extends JDialog{

	private String nom;
	private String id;
	private String adresse;
	private String motDePasse;
	private String confirmMotDePasse;
	private MotDePasse mdp;
	
	private JTextField txtFieldNom;
	private JTextField txtFieldId;
	private JTextField txtFieldAdresse;
	private JTextField txtFieldMotDePasse;
	private JTextField txtConfirmMotDePasse;
	private JLabel lblMessage = new JLabel("");

	public Modifier(String nomC, String idC, String adresseC, String motDePasseC, String confirmMotDePasseC, MotDePasse mdpC) {
		this.setTitle("Nouveau mot de passe");
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		this.nom = nomC;
		this.id = idC;
		this.adresse = adresseC;
		this.motDePasse = motDePasseC;
		this.confirmMotDePasse = confirmMotDePasseC;
		this.mdp = mdpC;
		
		JPanel contentPan = (JPanel) this.getContentPane();
		contentPan.setLayout(new BoxLayout(contentPan, BoxLayout.Y_AXIS));
		
		JPanel panNom = new JPanel();
		panNom.setLayout(new BoxLayout(panNom, BoxLayout.X_AXIS));
		
		JLabel lblNom = new JLabel("          Name :          ");
		panNom.add(lblNom);
		
		txtFieldNom = new JTextField();
		txtFieldNom.setFocusable(false);
		panNom.add(txtFieldNom);
		
		contentPan.add(panNom);
		
		JPanel panId = new JPanel();
		panId.setLayout(new BoxLayout(panId, BoxLayout.X_AXIS));
		
		JLabel lblId = new JLabel("             Id :             ");
		panId.add(lblId);
		
		txtFieldId = new JTextField();
		txtFieldId.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				id = txtFieldId.getText();
			}
			
		});
		panId.add(txtFieldId);
		
		contentPan.add(panId);
		
		JPanel panAdresse = new JPanel();
		panAdresse.setLayout(new BoxLayout(panAdresse, BoxLayout.X_AXIS));
		
		JLabel lblAdresse = new JLabel("        Address :        ");
		panAdresse.add(lblAdresse);
		
		txtFieldAdresse = new JTextField();
		txtFieldAdresse.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				adresse = txtFieldAdresse.getText();
			}
			
		});
		panAdresse.add(txtFieldAdresse);
		
		contentPan.add(panAdresse);
		
		JPanel panMotDePasse = new JPanel();
		panMotDePasse.setLayout(new BoxLayout(panMotDePasse, BoxLayout.X_AXIS));
		
		JLabel lblMotDePasse = new JLabel("       Password :       ");
		panMotDePasse.add(lblMotDePasse);
		
		txtFieldMotDePasse = new JTextField();
		txtFieldMotDePasse.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				motDePasse = txtFieldMotDePasse.getText();
			}
			
		});
		panMotDePasse.add(txtFieldMotDePasse);
		
		contentPan.add(panMotDePasse);
		
		JPanel panConfirmMotDePasse = new JPanel();
		panConfirmMotDePasse.setLayout(new BoxLayout(panConfirmMotDePasse, BoxLayout.X_AXIS));
		
		JLabel lblConfirmMotDePasse = new JLabel(" Confirm password :");
		panConfirmMotDePasse.add(lblConfirmMotDePasse);
		
		txtConfirmMotDePasse = new JTextField();
		txtConfirmMotDePasse.setPreferredSize(new Dimension(150, 25));
		txtConfirmMotDePasse.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				confirmMotDePasse = txtConfirmMotDePasse.getText();
			}
			
		});
		panConfirmMotDePasse.add(txtConfirmMotDePasse);
		
		contentPan.add(panConfirmMotDePasse);
		
		lblMessage.setAlignmentX(CENTER_ALIGNMENT);
		lblMessage.setForeground(Color.RED);
		contentPan.add(lblMessage);
		
		JPanel panButton = new JPanel();
		panButton.setLayout(new BoxLayout(panButton, BoxLayout.X_AXIS));
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(this::btnValiderListener);
		panButton.add(btnValider);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(this::btnQuitterListener);
		panButton.add(btnQuitter);
		
		contentPan.add(panButton);
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				txtFieldNom.setText("");
				txtFieldId.setText("");
				txtFieldAdresse.setText("");
				txtFieldMotDePasse.setText("");
				txtConfirmMotDePasse.setText("");
				lblMessage.setText("");
			}
			
		});
		txtFieldNom.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					nom = txtFieldNom.getText();
					id = txtFieldId.getText();
					adresse = txtFieldAdresse.getText();
					motDePasse = txtFieldMotDePasse.getText();
					confirmMotDePasse = txtConfirmMotDePasse.getText();
					btnValiderListener(null);
				}
			}
			
		});
		txtFieldId.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					nom = txtFieldNom.getText();
					id = txtFieldId.getText();
					adresse = txtFieldAdresse.getText();
					motDePasse = txtFieldMotDePasse.getText();
					confirmMotDePasse = txtConfirmMotDePasse.getText();
					btnValiderListener(null);
				}
			}
			
		});
		txtFieldAdresse.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					nom = txtFieldNom.getText();
					id = txtFieldId.getText();
					adresse = txtFieldAdresse.getText();
					motDePasse = txtFieldMotDePasse.getText();
					confirmMotDePasse = txtConfirmMotDePasse.getText();
					btnValiderListener(null);
				}
			}
			
		});
		txtFieldMotDePasse.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					nom = txtFieldNom.getText();
					id = txtFieldId.getText();
					adresse = txtFieldAdresse.getText();
					motDePasse = txtFieldMotDePasse.getText();
					confirmMotDePasse = txtConfirmMotDePasse.getText();
					btnValiderListener(null);
				}
			}
			
		});
		txtConfirmMotDePasse.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					nom = txtFieldNom.getText();
					id = txtFieldId.getText();
					adresse = txtFieldAdresse.getText();
					motDePasse = txtFieldMotDePasse.getText();
					confirmMotDePasse = txtConfirmMotDePasse.getText();
					btnValiderListener(null);
				}
			}
			
		});
		
		txtFieldNom.setText(nomC);
		txtFieldId.setText(idC);
		txtFieldAdresse.setText(adresseC);
		txtFieldMotDePasse.setText(motDePasseC);
		txtConfirmMotDePasse.setText(confirmMotDePasseC);
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void btnValiderListener(ActionEvent event) {
		if (!nom.equals("") && !motDePasse.equals("") && !confirmMotDePasse.equals("")) {
			if (motDePasse.equals(confirmMotDePasse)) {
				MotDePasse unMdp = new MotDePasse(Main.compteCourent.getLogin(), nom, id, adresse, motDePasse);
				Main.compteCourent.supprimerMotDePasse(mdp);
				Main.addMotDePasse(unMdp);
				Main.clearTableau();
				Main.ajouterDesMotsDePasseAuTableau(Main.compteCourent.getLesMotsDePasse());
				dispose();
			} else {
				lblMessage.setText("Mots de passe non coherent");
				this.pack();
				this.setLocationRelativeTo(null);
			}
		} else {
			lblMessage.setText("Veillez remplir les champs");
			this.pack();
			this.setLocationRelativeTo(null);
		}
	}
	
	private void btnQuitterListener(ActionEvent event) {
		txtFieldNom.setText("");
		txtFieldId.setText("");
		txtFieldAdresse.setText("");
		txtFieldMotDePasse.setText("");
		txtConfirmMotDePasse.setText("");
		lblMessage.setText("");
		dispose();
	}
	
}
