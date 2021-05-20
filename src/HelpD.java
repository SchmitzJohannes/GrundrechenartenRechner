import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class HelpD extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HelpD dialog = new HelpD();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HelpD() {
		setType(Type.POPUP);
		setTitle("Help");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 500, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("<html>  Vor Klammern muss auch bei Multiplikation zwingend ein Rechenzeichen.<br>  Maus während des Klickens nicht bewegen.<br>  Nur Zahlen werden unterstützt, keine Variablen.<br>  Es wird automatisch von der letzten Rechnung aus weitergerechnet.<br>  Zum starten einer neuen Rechnung CE drücken oder c eingeben.</html>");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(e -> {
			         HelpD.this.dispose();
			      });
			}
		}
	}

}
