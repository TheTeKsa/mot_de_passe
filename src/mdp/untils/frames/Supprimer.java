package mdp.untils.frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mdp.untils.types.MotDePasse;

@SuppressWarnings("serial")
public class Supprimer extends JDialog{
	
	private String nom;
	
	private JTextField txtFieldNom;
	private JLabel lblMessage = new JLabel("");

	public Supprimer() {
		this.setTitle("Supprimer");
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panNom = new JPanel();
		panNom.setLayout(new BoxLayout(panNom, BoxLayout.X_AXIS));
		
		JLabel lblNom = new JLabel("Nom : ");
		panNom.add(lblNom);
		
		txtFieldNom = new JTextField();
		txtFieldNom.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				nom = txtFieldNom.getText();
			}
			
		});
		panNom.add(txtFieldNom);
		
		contentPane.add(panNom);
		
		lblMessage.setAlignmentX(CENTER_ALIGNMENT);
		lblMessage.setForeground(Color.RED);
		contentPane.add(lblMessage);
		
		JPanel panButton = new JPanel();
		panButton.setLayout(new BoxLayout(panButton, BoxLayout.X_AXIS));
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(this::btnValiderListener);
		panButton.add(btnValider);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(this::btnQuitterListener);
		panButton.add(btnQuitter);
		
		contentPane.add(panButton);
		
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent arg0) {
				txtFieldNom.setText("");
				lblMessage.setText("");
			}

		});
		txtFieldNom.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					nom = txtFieldNom.getText();
					btnValiderListener(null);
				}
			}
			
		});
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void btnValiderListener(ActionEvent event) {
		List<MotDePasse> lesMotsDePasse = Main.compteCourent.getLesMotsDePasse();
		
		if (!nom.equals("")) {
			if (nomExist(nom)) {
				for (int i = 0; i < lesMotsDePasse.size(); i++) {
					if (lesMotsDePasse.get(i).getNom().equals(nom)) {
						Main.clearTableau();
						Main.compteCourent.supprimerMotDePasse(lesMotsDePasse.get(i));
						Main.ajouterDesMotsDePasseAuTableau(Main.compteCourent.getLesMotsDePasse());
						txtFieldNom.setText("");
						lblMessage.setText("");
						dispose();
					}
				}
			} else {
				lblMessage.setText("nom non existant");
				this.pack();
				this.setLocationRelativeTo(null);
			}
		} else {
			lblMessage.setText("Veillez remplir le champ");
			this.pack();
			this.setLocationRelativeTo(null);
		}
	}
	
	private void btnQuitterListener(ActionEvent event) {
		txtFieldNom.setText("");
		lblMessage.setText("");
		dispose();
	}
	
	private boolean nomExist(String nom) {
		List<MotDePasse> lesMotsDePasse = Main.compteCourent.getLesMotsDePasse();

		for (int i = 0; i < lesMotsDePasse.size(); i++) {
			if (lesMotsDePasse.get(i).getNom().equals(nom)) {
				return true;
			}
		}
		return false;
	}
	
}
