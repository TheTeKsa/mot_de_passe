package mdp.untils.frames;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		
		JLabel lblNom = new JLabel("Nom :");
		panNom.add(lblNom);
		
		JTextField txtFieldNom = new JTextField();
		txtFieldNom.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				nom = txtFieldNom.getText();
			}
		});
	}
}
