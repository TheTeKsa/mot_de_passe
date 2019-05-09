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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mdp.untils.types.Compte;

@SuppressWarnings("serial")
public class NouveauCompte extends JDialog {
	
	private String login;
	private String password;
	private String confirmPassword;
	
	private JTextField txtFieldLogin;
	private JPasswordField pswFieldMDP;
	private JPasswordField pswFieldConfirmMDP;
	private JLabel lblMessage = new JLabel("");

	public NouveauCompte() {
		this.setTitle("Nouveau compte");
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JPanel contentPan = (JPanel) this.getContentPane();
		contentPan.setLayout(new BoxLayout(contentPan, BoxLayout.Y_AXIS));
		
		JPanel panLogin = new JPanel();
		panLogin.setLayout(new BoxLayout(panLogin, BoxLayout.X_AXIS));
		
		JLabel lblLogin = new JLabel("           Login :           ");
		panLogin.add(lblLogin);
		
		txtFieldLogin = new JTextField();
		txtFieldLogin.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				login = txtFieldLogin.getText();
			}
			
		});
		panLogin.add(txtFieldLogin);
		
		contentPan.add(panLogin);
		
		JPanel panMDP = new JPanel();
		panMDP.setLayout(new BoxLayout(panMDP, BoxLayout.X_AXIS));
		
		JLabel lblMDP = new JLabel("        Password :       ");
		panMDP.add(lblMDP);
		
		pswFieldMDP = new JPasswordField();
		pswFieldMDP.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				password = passwordToString(pswFieldMDP.getPassword());
			}
			
		});
		panMDP.add(pswFieldMDP);
		
		contentPan.add(panMDP);
		
		JPanel panConfirmMdp = new JPanel();
		panConfirmMdp.setLayout(new BoxLayout(panConfirmMdp, BoxLayout.X_AXIS));
		
		JLabel lblConfirmMDP = new JLabel(" Confirm password : ");
		panConfirmMdp.add(lblConfirmMDP);
		
		pswFieldConfirmMDP = new JPasswordField();
		pswFieldConfirmMDP.setPreferredSize(new Dimension(150, 25));
		pswFieldConfirmMDP.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				confirmPassword = passwordToString(pswFieldConfirmMDP.getPassword());
			}
			
		});
		panConfirmMdp.add(pswFieldConfirmMDP);
		
		contentPan.add(panConfirmMdp);
		
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
			public void windowClosing(WindowEvent arg0) {
				txtFieldLogin.setText("");
				pswFieldMDP.setText("");
				pswFieldConfirmMDP.setText("");
				lblMessage.setText("");
			}
			
		});
		txtFieldLogin.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					login = txtFieldLogin.getText();
					password = passwordToString(pswFieldMDP.getPassword());
					confirmPassword = passwordToString(pswFieldConfirmMDP.getPassword());
					btnValiderListener(null);
				}
			}
			
		});
		pswFieldMDP.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					login = txtFieldLogin.getText();
					password = passwordToString(pswFieldMDP.getPassword());
					confirmPassword = passwordToString(pswFieldConfirmMDP.getPassword());
					btnValiderListener(null);
				}
			}
			
		});
		pswFieldConfirmMDP.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					login = txtFieldLogin.getText();
					password = passwordToString(pswFieldMDP.getPassword());
					confirmPassword = passwordToString(pswFieldConfirmMDP.getPassword());
					btnValiderListener(null);
				}
			}
			
		});
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void btnValiderListener(ActionEvent event) {
		if (!login.equals("") && !password.equals("") && !confirmPassword.equals("")) {
			if (!loginExist(login) && password.equals(confirmPassword)) {
				Compte compte = new Compte(login, password);
				Main.addCompte(compte);
				txtFieldLogin.setText("");
				pswFieldMDP.setText("");
				pswFieldConfirmMDP.setText("");
				lblMessage.setText("");
				dispose();
			} else if(loginExist(login)) {
				lblMessage.setText("Le login existe deja");
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
	}
	
	private void btnQuitterListener(ActionEvent event) {
		txtFieldLogin.setText("");
		pswFieldMDP.setText("");
		pswFieldConfirmMDP.setText("");
		lblMessage.setText("");
		dispose();
	}
	
	private String passwordToString(char[] mot) {
		String result = "";
		
		for (int i = 0; i < mot.length; i++) {
			result += mot[i];
		}
		return result;
	}
	
	private boolean loginExist(String login) {
		Compte[] lesComptes = Main.getComptes();
		
		for (int i = 0; i < lesComptes.length; i++) {
			if (lesComptes[i].getLogin().equals(login)) {
				return true;
			}
		}
		return false;
	}
}
