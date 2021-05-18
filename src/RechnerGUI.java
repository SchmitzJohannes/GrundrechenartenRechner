import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RechnerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RechnerGUI frame = new RechnerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});

	}

	/**
	 * Create the frame.
	 */
	public RechnerGUI() {
		setTitle("Rechnerartig");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        // Rechnung und Ergebnis werden auf leere Strings gesetzt damit sie nicht wie von Erstellung noch null sind.
		v[0] = "";
		v[1] = "";
		//Usage instructions get displayed on startup
		HelpD.main(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// Entspricht dem drücken des = Knopfes
					if (v[0].equals("")) {
						v[0] = textField.getText();
					} else {
						v[0] = "(" + v[0] + (")") + textField.getText();
					}
					rechnungsFeld.setText(v[0]);
					textField.setText("");
					v[1] = Rechner.rechnen(v[0]);
					ergebnisFeld.setText(v[1]);
				} else if (e.getKeyCode() == KeyEvent.VK_C) {
					// Entspricht dem drücken des CE Knopfes
					v = clearplease(v);
					ergebnisFeld.setText(v[1]);
					rechnungsFeld.setText(v[0]);
					eingabeFeld.setText("");
				}
			}
		});
		textField.setBounds(124, 11, 300, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		eingabeFeld = textField;

		JButton btnNewButton = new JButton("CE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Variablen werden zurückgesetzt
				v = clearplease(v);
				ergebnisFeld.setText(v[1]);
				rechnungsFeld.setText(v[0]);
				eingabeFeld.setText("");
			}
		});

		btnNewButton.setBounds(10, 102, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("/");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_1.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_1.setBounds(109, 102, 89, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("*");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_2.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_2.setBounds(208, 102, 89, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("-");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_3.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_3.setBounds(307, 102, 89, 23);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("7");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_4.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_4.setBounds(10, 136, 89, 23);
		contentPane.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("8");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_5.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_5.setBounds(109, 136, 89, 23);
		contentPane.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("9");
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_6.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_6.setBounds(208, 136, 89, 23);
		contentPane.add(btnNewButton_6);

		JButton btnNewButton_7 = new JButton("+");
		btnNewButton_7.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_7.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_7.setBounds(307, 136, 89, 23);
		contentPane.add(btnNewButton_7);

		JButton btnNewButton_8 = new JButton("4");
		btnNewButton_8.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_8.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_8.setBounds(10, 170, 89, 23);
		contentPane.add(btnNewButton_8);

		JButton btnNewButton_9 = new JButton("5");
		btnNewButton_9.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_9.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_9.setBounds(109, 170, 89, 23);
		contentPane.add(btnNewButton_9);

		JButton btnNewButton_10 = new JButton("6");
		btnNewButton_10.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_10.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_10.setBounds(208, 170, 89, 23);
		contentPane.add(btnNewButton_10);

		JButton btnNewButton_11 = new JButton("=");
		btnNewButton_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (v[0].equals("")) {
					v[0] = textField.getText();
				} else {
					v[0] = "(" + v[0] + (")") + textField.getText();
				}
				rechnungsFeld.setText(v[0]);
				textField.setText("");
				v[1] = Rechner.rechnen(v[0]);
				ergebnisFeld.setText(v[1]);
			}
		});
		btnNewButton_11.setBounds(307, 170, 89, 23);
		contentPane.add(btnNewButton_11);

		JButton btnNewButton_12 = new JButton("1");
		btnNewButton_12.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_12.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_12.setBounds(10, 204, 89, 23);
		contentPane.add(btnNewButton_12);

		JButton btnNewButton_13 = new JButton("2");
		btnNewButton_13.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_13.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_13.setBounds(109, 204, 89, 23);
		contentPane.add(btnNewButton_13);

		JButton btnNewButton_14 = new JButton("3");
		btnNewButton_14.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_14.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_14.setBounds(208, 204, 89, 23);
		contentPane.add(btnNewButton_14);

		JButton btnNewButton_15 = new JButton("Help");
		btnNewButton_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// unvollständig
				HelpD.main(null);
			}
		});
		btnNewButton_15.setToolTipText(
				"Vor Klammern muss auch bei Multiplikation zwingend ein Rechenzeichen. Maus während des Klickens nicht bewegen.Nur Zahlen werden unterstützt, keine Variablen.Es wird automatisch von der letzten Rechnung aus weitergerechnet, zum starten einer neuen Rechnung CE drücken oder c eingeben.");

		btnNewButton_15.setBounds(307, 204, 89, 23);
		contentPane.add(btnNewButton_15);

		JButton btnNewButton_16 = new JButton("0");
		btnNewButton_16.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_16.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_16.setBounds(10, 238, 89, 23);
		contentPane.add(btnNewButton_16);

		JButton btnNewButton_17 = new JButton(".");
		btnNewButton_17.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_17.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_17.setBounds(109, 238, 89, 23);
		contentPane.add(btnNewButton_17);

		JButton btnNewButton_18 = new JButton("(");
		btnNewButton_18.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_18.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_18.setBounds(208, 238, 89, 23);
		contentPane.add(btnNewButton_18);

		JButton btnNewButton_19 = new JButton(")");
		btnNewButton_19.addMouseListener(new MouseAdapter() {
			String knopftext = btnNewButton_19.getText();

			@Override
			public void mouseClicked(MouseEvent e) {
				v[0] = rechnungAppend(v[0], knopftext);
				rechnungsFeld.setText(v[0]);
			}
		});
		btnNewButton_19.setBounds(307, 238, 89, 23);
		contentPane.add(btnNewButton_19);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(124, 40, 300, 20);
		textPane.setText(v[0]);
		rechnungsFeld = textPane;
		contentPane.add(textPane);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(124, 71, 300, 20);
		textPane_1.setText(v[1]);
		contentPane.add(textPane_1);
		ergebnisFeld = textPane_1;

		JLabel lblNewLabel = new JLabel("Rechnung");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(10, 42, 89, 14);
		contentPane.add(lblNewLabel);

		JLabel lblErgebnis = new JLabel("Ergebnis");
		lblErgebnis.setHorizontalAlignment(SwingConstants.TRAILING);
		lblErgebnis.setBounds(10, 71, 89, 14);
		contentPane.add(lblErgebnis);

		JLabel lblEingabe = new JLabel("Eingabe");
		lblEingabe.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEingabe.setBounds(10, 14, 89, 14);
		contentPane.add(lblEingabe);

	}

	
    // Enthält Rechnung und Ergebnis
	String[] v = new String[2];
	// Die Schreib- und Anzeigefelder werden bei Erstellung diesen Variablen zugewiesen, damit mit diesen auch nach Erstellung noch interagiert werden kann.
	JTextPane rechnungsFeld;
	JTextPane ergebnisFeld;
	JTextField eingabeFeld;
    // Setzt Rechnungs- und Ergebnisvariable zurück
	public static String[] clearplease(String[] tbc) {

		tbc[0] = "";
		tbc[1] = "";
		System.out.println(Arrays.toString(tbc));

		return tbc;
	}
    //Funktion der meisten Knöpfe ist es ihre Beschriftung an die Rechnung anzufügen
	public static String rechnungAppend(String rechnung, String knopftext) {
		if (rechnung.equals("")) {
			rechnung = knopftext;
		} else {
			rechnung = rechnung + knopftext;
		}
		return rechnung;
	}

}
