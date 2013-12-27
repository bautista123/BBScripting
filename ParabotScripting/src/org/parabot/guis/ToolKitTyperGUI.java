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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.parabot.bbtoolkit.data.ToolKitVar;

public class ToolKitTyperGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToolKitTyperGUI frame = new ToolKitTyperGUI();
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
	public ToolKitTyperGUI() {
		setTitle("Typer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 271, 244);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 255, 206);
		contentPane.add(panel);

		JLabel label = new JLabel("Message: ");
		label.setFont(new Font("Arial", Font.PLAIN, 17));
		label.setBounds(10, 31, 81, 20);
		panel.add(label);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(95, 33, 150, 20);
		panel.add(textField);

		JLabel label_1 = new JLabel("Time Intervals: ");
		label_1.setFont(new Font("Arial", Font.PLAIN, 17));
		label_1.setBounds(10, 71, 110, 14);
		panel.add(label_1);

		final JSpinner spinner = new JSpinner();
		spinner.setBounds(119, 70, 40, 20);
		panel.add(spinner);

		JLabel label_2 = new JLabel("Seconds");
		label_2.setFont(new Font("Arial", Font.PLAIN, 17));
		label_2.setBounds(169, 71, 81, 14);
		panel.add(label_2);

		final JCheckBox checkBox = new JCheckBox("Paint On");
		checkBox.setFont(new Font("Arial", Font.PLAIN, 17));
		checkBox.setBounds(80, 109, 97, 23);
		panel.add(checkBox);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkBox.isSelected()) {
						ToolKitVar.setPaint(true);
					}
					ToolKitVar.setTyper(true);
					ToolKitVar.setMessage(textField.getText().toString());
					int i = (int) spinner.getValue();
					ToolKitVar.setInterval(i * 1000);
					ToolKitVar.setGuiWait(false);
					dispose();
				} catch (Exception FUck) {

				}
			}
		});
		btnStart.setFont(new Font("Arial", Font.PLAIN, 16));
		btnStart.setBounds(80, 161, 89, 23);
		panel.add(btnStart);
	}

}
