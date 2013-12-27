package org.parabot.bbtoolkit.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.parabot.bbtoolkit.data.ToolKitVar;

public class ToolKitSpawnerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToolKitSpawnerGUI frame = new ToolKitSpawnerGUI();
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
	public ToolKitSpawnerGUI() {
		setTitle("Spanwer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 276, 193);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 255, 154);
		contentPane.add(panel);

		final JCheckBox checkBox = new JCheckBox("Paint On");
		checkBox.setFont(new Font("Arial", Font.PLAIN, 17));
		checkBox.setBounds(87, 76, 97, 23);
		panel.add(checkBox);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(98, 44, 125, 20);
		panel.add(textField);

		JLabel label = new JLabel("Item ID: ");
		label.setFont(new Font("Arial", Font.PLAIN, 17));
		label.setBounds(24, 45, 64, 14);
		panel.add(label);

		JButton button = new JButton("Start");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkBox.isSelected()) {
						ToolKitVar.setPaint(true);
					}
					ToolKitVar.setSpawnID(Integer.parseInt(textField.getText()));
					ToolKitVar.setSpawner(true);
					ToolKitVar.setGuiWait(false);
					dispose();
				} catch (Exception Fuck) {
					textField.setText("Numbers Only!");
				}

			}
		});
		button.setFont(new Font("Arial", Font.PLAIN, 16));
		button.setBounds(87, 120, 89, 23);
		panel.add(button);
	}

}
