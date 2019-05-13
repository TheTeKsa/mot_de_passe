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
public class Rechercher extends JDialog {
	
	private String nom;
	
	private JTextField txtFieldNom;
	private JLabel lblMessage = new JLabel("");

	public Rechercher() {
		this.setTitle("Rechercher");
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JPanel contentPan = (JPanel) this.getContentPane();
		contentPan.setLayout(new BoxLayout(contentPan, BoxLayout.Y_AXIS));
		
		JPanel panNom = new JPanel();
		panNom.setLayout(new BoxLayout(panNom, BoxLayout.X_AXIS));
		
		JLabel lblNom = new JLabel("Nom");
		panNom.add(lblNom);
		
		txtFieldNom = new JTextField();
		txtFieldNom.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				nom = txtFieldNom.getText();
			}
			
		});
		panNom.add(txtFieldNom);
		
		contentPan.add(panNom);
		
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
			public void windowClosed(WindowEvent e) {
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
		List<MotDePasse> mdps = Main.compteCourent.getLesMotsDePasse();
		
		if (!nom.equals("")) {
			if (nomExist(nom, mdps)) {
				MotDePasse mdp = mdps.get(idxMotDePasse(nom, mdps));
				Main.clearTableau();
				Main.ajouterUnMotDePasseAuTableau(mdp);
				txtFieldNom.setText("");
				lblMessage.setText("");
				dispose();
			} else {
				lblMessage.setText("Nom non existant");
				this.pack();
				this.setLocationRelativeTo(null);
			}
		} else {
			Main.clearTableau();
			Main.ajouterDesMotsDePasseAuTableau(Main.compteCourent.getLesMotsDePasse());
			dispose();
		}
	}
	
	private void btnQuitterListener(ActionEvent event) {
		txtFieldNom.setText("");
		lblMessage.setText("");
		dispose();
	}
	
	private boolean nomExist(String nom, List<MotDePasse> mdps) {
		for (int i = 0; i < mdps.size(); i++) {
			if (mdps.get(i).getNom().equals(nom)) {
				return true;
			}
		}
		return false;
	}
	
	private int idxMotDePasse(String nom, List<MotDePasse> mdps) {
		for (int i = 0; i < mdps.size(); i++) {
			if (mdps.get(i).getNom().equals(nom)) {
				return i;
			}
		}
		return -1;
	}
	
}
