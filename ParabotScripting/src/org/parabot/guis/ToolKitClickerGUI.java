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
import javax.swing.border.EmptyBorder;

import org.parabot.bbtoolkit.data.ToolKitVar;

public class ToolKitClickerGUI extends JFrame {
	public static int i;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToolKitClickerGUI frame = new ToolKitClickerGUI();
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
	public ToolKitClickerGUI() {
		setTitle("Clicker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 273, 168);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 255, 130);
		contentPane.add(panel);

		JLabel label = new JLabel("Time Intervals: ");
		label.setFont(new Font("Arial", Font.PLAIN, 17));
		label.setBounds(10, 30, 116, 14);
		panel.add(label);

		final JSpinner spinner = new JSpinner();
		spinner.setBounds(120, 29, 50, 20);
		panel.add(spinner);

		JLabel label_1 = new JLabel("Seconds");
		label_1.setFont(new Font("Arial", Font.PLAIN, 17));
		label_1.setBounds(180, 30, 65, 14);
		panel.add(label_1);

		final JCheckBox checkBox = new JCheckBox("Paint On");
		checkBox.setFont(new Font("Arial", Font.PLAIN, 17));
		checkBox.setBounds(72, 69, 97, 23);
		panel.add(checkBox);

		JButton button = new JButton("Start");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkBox.isSelected()) {
						ToolKitVar.setPaint(true);
					}
					int i = (int) spinner.getValue();
					ToolKitVar.setInterval(i);
					ToolKitVar.setGuiWait(false);
					dispose();
				} catch (Exception Fukcer) {

				}
			}
		});
		button.setFont(new Font("Arial", Font.PLAIN, 16));
		button.setBounds(72, 99, 89, 23);
		panel.add(button);
	}

}
