package org.eclipsebot.draynorfisher.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipsebot.draynorfisher.data.DraynorFisherVar;

public class DraynorFisherGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DraynorFisherGUI frame = new DraynorFisherGUI();
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
	public DraynorFisherGUI() {
		setTitle("Draynor Fisher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 109, 136);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Drop",
				"Bank" }));
		comboBox.setBounds(23, 11, 70, 20);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString()
						.equalsIgnoreCase("Drop")) {
					DraynorFisherVar.setDrop(true);
				} else if (comboBox.getSelectedItem().toString()
						.equalsIgnoreCase("Bank")) {
					DraynorFisherVar.setBank(true);
				}
				DraynorFisherVar.setGuiWait(false);
				dispose();
			}
		});
		btnNewButton.setBounds(23, 64, 70, 23);
		contentPane.add(btnNewButton);

	}
}
