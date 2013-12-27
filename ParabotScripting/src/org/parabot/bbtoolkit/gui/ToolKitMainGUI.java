package org.parabot.bbtoolkit.gui;

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
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class ToolKitMainGUI extends JFrame {
	ToolKitDropperGUI dropper = new ToolKitDropperGUI();
	ToolKitClickerGUI clicker = new ToolKitClickerGUI();
	ToolKitTyperGUI typer = new ToolKitTyperGUI();
	ToolKitSpawnerGUI spawner = new ToolKitSpawnerGUI();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToolKitMainGUI frame = new ToolKitMainGUI();
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
	public ToolKitMainGUI() {
		setTitle("bbToolKit");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 291, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 275, 226);
		contentPane.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Operation", null, panel_1, null);
		panel_1.setLayout(null);

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Auto Spawner", "Auto Clicker", "Auto Dropper", "Auto Talker" }));
		comboBox.setToolTipText("");
		comboBox.setFont(new Font("Arial", Font.PLAIN, 17));
		comboBox.setBounds(69, 46, 138, 35);
		panel_1.add(comboBox);

		JLabel lblToolToUse = new JLabel("Tool to use");
		lblToolToUse.setFont(new Font("Arial", Font.PLAIN, 17));
		lblToolToUse.setBounds(89, 21, 82, 14);
		panel_1.add(lblToolToUse);

		JButton btnLaunchBot = new JButton("Launch Bot");
		btnLaunchBot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString()
						.equalsIgnoreCase("Auto Spawner")) {
					dispose();
					spawner.setVisible(true);
				}
				if (comboBox.getSelectedItem().toString()
						.equalsIgnoreCase("Auto Clicker")) {
					dispose();
					clicker.setVisible(true);

				}
				if (comboBox.getSelectedItem().toString()
						.equalsIgnoreCase("Auto Dropper")) {
					dispose();
					dropper.setVisible(true);
				}
				if (comboBox.getSelectedItem().toString()
						.equalsIgnoreCase("Auto Talker")) {
					dispose();
					typer.setVisible(true);
				}
			}
		});
		btnLaunchBot.setBounds(0, 142, 270, 56);
		panel_1.add(btnLaunchBot);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Info", null, panel, null);
		panel.setLayout(null);

		JLabel lblThankYouFor = new JLabel("Thank you for choosing bbToolKit");
		lblThankYouFor.setFont(new Font("Arial", Font.PLAIN, 16));
		lblThankYouFor.setBounds(10, 11, 255, 19);
		panel.add(lblThankYouFor);

		JLabel label_1 = new JLabel("Credits: ");
		label_1.setFont(new Font("Arial", Font.PLAIN, 16));
		label_1.setBounds(96, 41, 87, 14);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Bautista123 - Main Coder");
		label_2.setFont(new Font("Arial", Font.PLAIN, 12));
		label_2.setBounds(66, 64, 166, 14);
		panel.add(label_2);

		/*JLabel label_3 = new JLabel("Bears - Paints and Spanwer");
		label_3.setFont(new Font("Arial", Font.PLAIN, 12));
		label_3.setBounds(59, 89, 155, 14);
		panel.add(label_3);*/

		JLabel label_4 = new JLabel("If you have any suggestions");
		label_4.setFont(new Font("Arial", Font.PLAIN, 16));
		label_4.setBounds(32, 124, 200, 19);
		panel.add(label_4);

		JLabel label_5 = new JLabel("for future features, please ");
		label_5.setFont(new Font("Arial", Font.PLAIN, 16));
		label_5.setBounds(42, 145, 179, 19);
		panel.add(label_5);

		JLabel label_6 = new JLabel("post on the thread.");
		label_6.setFont(new Font("Arial", Font.PLAIN, 16));
		label_6.setBounds(66, 164, 148, 19);
		panel.add(label_6);
	}
}