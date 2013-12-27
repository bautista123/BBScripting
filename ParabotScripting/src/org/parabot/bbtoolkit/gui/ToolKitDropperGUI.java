package org.parabot.bbtoolkit.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JButton;

import org.parabot.bbtoolkit.data.ToolKitVar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ToolKitDropperGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToolKitDropperGUI frame = new ToolKitDropperGUI();
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
	public ToolKitDropperGUI() {
		setTitle("Dropper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 274, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 255, 152);
		contentPane.add(panel);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(96, 37, 125, 20);
		panel.add(textField);

		JLabel label = new JLabel("Item ID: ");
		label.setFont(new Font("Arial", Font.PLAIN, 17));
		label.setBounds(24, 38, 64, 14);
		panel.add(label);

		final JCheckBox checkBox = new JCheckBox("Only Drop When Full");
		checkBox.setFont(new Font("Arial", Font.PLAIN, 17));
		checkBox.setBounds(36, 90, 185, 23);
		panel.add(checkBox);

		final JCheckBox checkBox_1 = new JCheckBox("Paint On");
		checkBox_1.setFont(new Font("Arial", Font.PLAIN, 17));
		checkBox_1.setBounds(82, 64, 97, 23);
		panel.add(checkBox_1);

		JButton button = new JButton("Start");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkBox_1.isSelected()) {
						ToolKitVar.setPaint(true);
					}
					if (checkBox.isSelected()) {
						ToolKitVar.setDropT(true);
					}
					ToolKitVar.setDroppedID(Integer.parseInt(textField
							.getText()));
					ToolKitVar.setDropper(true);
					ToolKitVar.setGuiWait(false);
					dispose();
				} catch (Exception Gay) {

				}
			}
		});
		button.setFont(new Font("Arial", Font.PLAIN, 16));
		button.setBounds(82, 118, 89, 23);
		panel.add(button);
	}

}