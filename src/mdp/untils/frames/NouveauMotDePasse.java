package mdp.untils.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mdp.untils.types.MotDePasse;

@SuppressWarnings("serial")
public class NouveauMotDePasse extends JDialog {
	
	private String nom;
	private String id;
	private String adresse;
	private String motDePasse;
	private String confirmMotDePasse;
	
	private JLabel lblMessage = new JLabel("");

	public NouveauMotDePasse() {
		this.setTitle("Nouveau mot de passe");
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JPanel contentPan = (JPanel) this.getContentPane();
		contentPan.setLayout(new BoxLayout(contentPan, BoxLayout.Y_AXIS));
		
		JPanel panNom = new JPanel();
		panNom.setLayout(new BoxLayout(panNom, BoxLayout.X_AXIS));
		
		JLabel lblNom = new JLabel("          Name :          ");
		panNom.add(lblNom);
		
		JTextField txtFieldNom = new JTextField();
		txtFieldNom.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				nom = txtFieldNom.getText();
			}
			
		});
		panNom.add(txtFieldNom);
		
		contentPan.add(panNom);
		
		JPanel panId = new JPanel();
		panId.setLayout(new BoxLayout(panId, BoxLayout.X_AXIS));
		
		JLabel lblId = new JLabel("             Id :             ");
		panId.add(lblId);
		
		JTextField txtFieldId = new JTextField();
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
		
		JTextField txtFieldAdresse = new JTextField();
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
		
		JPasswordField pswFieldMotDePasse = new JPasswordField();
		pswFieldMotDePasse.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				motDePasse = passwordToString(pswFieldMotDePasse.getPassword());
			}
			
		});
		panMotDePasse.add(pswFieldMotDePasse);
		
		contentPan.add(panMotDePasse);
		
		JPanel panConfirmMotDePasse = new JPanel();
		panConfirmMotDePasse.setLayout(new BoxLayout(panConfirmMotDePasse, BoxLayout.X_AXIS));
		
		JLabel lblConfirmMotDePasse = new JLabel(" Confirm password :");
		panConfirmMotDePasse.add(lblConfirmMotDePasse);
		
		JPasswordField pswConfirmMotDePasse = new JPasswordField();
		pswConfirmMotDePasse.setPreferredSize(new Dimension(150, 25));
		pswConfirmMotDePasse.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				confirmMotDePasse = passwordToString(pswConfirmMotDePasse.getPassword());
			}
			
		});
		panConfirmMotDePasse.add(pswConfirmMotDePasse);
		
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
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void btnValiderListener(ActionEvent event) {
		if (Main.compteCourent != null) {
			if (nom != null && id != null && adresse != null && motDePasse != null && confirmMotDePasse != null) {
				if (!nomExist(nom) && motDePasse.equals(confirmMotDePasse)) {
					MotDePasse mdp = new MotDePasse(Main.compteCourent.getLogin(), nom, id, adresse, motDePasse);
					Main.addMotDePasse(mdp);
					dispose();
				} else if (nomExist(nom)) {
					lblMessage.setText("Le nom existe deja");
					this.pack();
					this.setLocationRelativeTo(null);
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
		} else {
			lblMessage.setText("Veillez vous connecter");
			this.pack();
			this.setLocationRelativeTo(null);
		}
	}
	
	private void btnQuitterListener(ActionEvent event) {
		dispose();
	}
	
	private String passwordToString(char[] mot) {
		String result = "";
		
		for (int i = 0; i < mot.length; i++) {
			result += mot[i];
		}
		return result;
	}
	
	private boolean nomExist(String nom) {
		MotDePasse[] lesMotsDePasse = Main.getMotsDePasse();
		
		for (int i = 0; i < lesMotsDePasse.length; i++) {
			if (lesMotsDePasse[i].getNom().equals(nom)) {
				return true;
			}
		}
		return false;
	}
}
