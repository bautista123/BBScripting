package study.flashcardgame.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import study.flashcardgame.core.Core;
import study.flashcardgame.data.Variables;

public class StartGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartGUI frame = new StartGUI();
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
	public StartGUI() {
		setTitle("Bautista's Study Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 276, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblChooseASubject = new JLabel("Choose A Subject");
		lblChooseASubject.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChooseASubject.setBounds(63, 28, 129, 19);
		contentPane.add(lblChooseASubject);

		final JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Religion",
				"English", "Custom" }));
		comboBox.setBounds(63, 58, 120, 29);
		contentPane.add(comboBox);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI g = new GUI();
				if (comboBox.getSelectedItem().toString()
						.equalsIgnoreCase("religion")) {
					Variables.setSubject("Religion");
				} else if (comboBox.getSelectedItem().toString()
						.equalsIgnoreCase("english")) {
					Variables.setSubject("English");

				} else if (comboBox.getSelectedItem().toString()
						.equalsIgnoreCase("Custom")) {
					Variables.setSubject("Custom");
				}
				dispose();
				Variables.setStart(true);
				Core.onStart();
				g.setVisible(true);
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnStart.setBounds(63, 125, 120, 23);
		contentPane.add(btnStart);
	}
}
