package mdp.untils.frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mdp.untils.types.Compte;

@SuppressWarnings("serial")
public class Connexion extends JDialog {
	
	private String password;
	private String login;
	
	private JLabel lblMessage= new JLabel("");
	
	public Connexion() {
		this.setTitle("Connexion");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		// Panel principal
		JPanel contentPan = (JPanel) this.getContentPane();
		contentPan.setLayout(new BoxLayout(contentPan, BoxLayout.Y_AXIS));
		
		JLabel lblName = new JLabel("Connexion");
		lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPan.add(lblName);
		
		// Panel du login
		JPanel panLogin = new JPanel();
		panLogin.setLayout(new BoxLayout(panLogin, BoxLayout.X_AXIS));
		
		JLabel lblLogin = new JLabel("    Login :     ");
		panLogin.add(lblLogin);
		
		JTextField txtFieldLogin = new JTextField();
		txtFieldLogin.setPreferredSize(new Dimension(100, 25));
		txtFieldLogin.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				login = txtFieldLogin.getText();
			}
			
		});
		panLogin.add(txtFieldLogin);
		
		contentPan.add(panLogin);
		
		// Panel du password
		JPanel panPassword = new JPanel();
		panPassword.setLayout(new BoxLayout(panPassword, BoxLayout.X_AXIS));
		
		JLabel lblPassword = new JLabel(" Password : ");
		panPassword.add(lblPassword);
		
		JPasswordField pswFieldPassword = new JPasswordField();
		pswFieldPassword.setPreferredSize(new Dimension(100, 25));
		pswFieldPassword.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				password = passwordToString(pswFieldPassword.getPassword());
			}
			
		});
		panPassword.add(pswFieldPassword);
		
		contentPan.add(panPassword);
		
		lblMessage.setAlignmentX(CENTER_ALIGNMENT);
		lblMessage.setForeground(Color.red);
		contentPan.add(lblMessage);

		// Panel des boutons
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
		Compte[] comptes = Main.getComptes();
		int idx;
		
		if (login != null && password != null) {
			if (!exist(login, comptes)) {
				lblMessage.setText("Login non existant");
				this.pack();
				this.setLocationRelativeTo(null);
			} else {
				idx = getIdxCompte(login, comptes);
				if (!comptes[idx].getPassword().equals(password)) {
					lblMessage.setText("Mot de passe incorrecte");
					this.pack();
					this.setLocationRelativeTo(null);
				} else {
					dispose();
				}
			}
		} else {
			lblMessage.setText("Veillez remplir les champs");
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
	
	private boolean exist(String login, Compte[] comptes) {
		for (int i = 0; i < comptes.length; i++) {
			if (comptes[i].getLogin().equals(login)) {
				return true;
			}
		}
		return false;
	}
	
	private int getIdxCompte(String login, Compte[] comptes) {
		for (int i = 0; i < comptes.length; i++) {
			if (comptes[i].getLogin().equals(login)) {
				return i;
			}
		}
		return -1;
	}
}
