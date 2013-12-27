package org.parabot.bbtoolkit;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;

public class ToolKitGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToolKitGUI frame = new ToolKitGUI();
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
	public ToolKitGUI() {
		setTitle("BBToolKit");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 276, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 260, 234);
		contentPane.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Spawner", null, panel_1, null);
		panel_1.setLayout(null);
		
		JCheckBox checkBox = new JCheckBox("Paint On");
		checkBox.setFont(new Font("Arial", Font.PLAIN, 17));
		checkBox.setBounds(82, 124, 97, 23);
		panel_1.add(checkBox);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(98, 44, 125, 20);
		panel_1.add(textField_1);
		
		JLabel label = new JLabel("Item ID: ");
		label.setFont(new Font("Arial", Font.PLAIN, 17));
		label.setBounds(24, 45, 64, 14);
		panel_1.add(label);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Typer", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblMessage = new JLabel("Message: ");
		lblMessage.setFont(new Font("Arial", Font.PLAIN, 17));
		lblMessage.setBounds(10, 31, 81, 20);
		panel_2.add(lblMessage);
		
		textField_2 = new JTextField();
		textField_2.setBounds(95, 33, 150, 20);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblTimeIntervals = new JLabel("Time Intervals: ");
		lblTimeIntervals.setFont(new Font("Arial", Font.PLAIN, 17));
		lblTimeIntervals.setBounds(10, 104, 110, 14);
		panel_2.add(lblTimeIntervals);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(119, 103, 40, 20);
		panel_2.add(spinner);
		
		JLabel lblSeconds = new JLabel("Seconds");
		lblSeconds.setFont(new Font("Arial", Font.PLAIN, 17));
		lblSeconds.setBounds(164, 104, 81, 14);
		panel_2.add(lblSeconds);
		
		JCheckBox checkBox_1 = new JCheckBox("Paint On");
		checkBox_1.setFont(new Font("Arial", Font.PLAIN, 17));
		checkBox_1.setBounds(79, 154, 97, 23);
		panel_2.add(checkBox_1);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Clicker", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblTimeIntervals_1 = new JLabel("Time Intervals: ");
		lblTimeIntervals_1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblTimeIntervals_1.setBounds(10, 70, 116, 14);
		panel_3.add(lblTimeIntervals_1);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(120, 69, 50, 20);
		panel_3.add(spinner_1);
		
		JLabel lblSeconds_1 = new JLabel("Seconds");
		lblSeconds_1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblSeconds_1.setBounds(180, 70, 65, 14);
		panel_3.add(lblSeconds_1);
		
		JCheckBox checkBox_2 = new JCheckBox("Paint On");
		checkBox_2.setFont(new Font("Arial", Font.PLAIN, 17));
		checkBox_2.setBounds(79, 129, 97, 23);
		panel_3.add(checkBox_2);
		
				JPanel panel = new JPanel();
				tabbedPane.addTab("Dropper", null, panel, null);
				panel.setLayout(null);
				
				textField = new JTextField();
				textField.setBounds(96, 53, 125, 20);
				panel.add(textField);
				textField.setColumns(10);
				
				JLabel lblItemId = new JLabel("Item ID: ");
				lblItemId.setFont(new Font("Arial", Font.PLAIN, 17));
				lblItemId.setBounds(23, 54, 64, 14);
				panel.add(lblItemId);
				
				JCheckBox chckbxNewCheckBox = new JCheckBox("Only Drop When Full");
				chckbxNewCheckBox.setFont(new Font("Arial", Font.PLAIN, 17));
				chckbxNewCheckBox.setBounds(36, 137, 185, 23);
				panel.add(chckbxNewCheckBox);
				
				JCheckBox chckbxPaintOn = new JCheckBox("Paint On");
				chckbxPaintOn.setFont(new Font("Arial", Font.PLAIN, 17));
				chckbxPaintOn.setBounds(75, 100, 97, 23);
				panel.add(chckbxPaintOn);
				
				JPanel panel_5 = new JPanel();
				tabbedPane.addTab("Info", null, panel_5, null);
				panel_5.setLayout(null);
				
				JLabel lblThankYouFor = new JLabel("Thank you for choosing BBToolKit");
				lblThankYouFor.setFont(new Font("Arial", Font.PLAIN, 16));
				lblThankYouFor.setBounds(10, 11, 255, 19);
				panel_5.add(lblThankYouFor);
				
				JLabel lblCredits = new JLabel("Credits: ");
				lblCredits.setFont(new Font("Arial", Font.PLAIN, 16));
				lblCredits.setBounds(96, 41, 87, 14);
				panel_5.add(lblCredits);
				
				JLabel lblBautistaMain = new JLabel("Bautista123 - Main Coder");
				lblBautistaMain.setFont(new Font("Arial", Font.PLAIN, 12));
				lblBautistaMain.setBounds(66, 64, 166, 14);
				panel_5.add(lblBautistaMain);
				
				JLabel lblBearsPaints = new JLabel("Bears - Paints and Spanwer");
				lblBearsPaints.setFont(new Font("Arial", Font.PLAIN, 12));
				lblBearsPaints.setBounds(59, 89, 155, 14);
				panel_5.add(lblBearsPaints);
				
				JLabel lblIfYouHave = new JLabel("If you have any suggestions");
				lblIfYouHave.setFont(new Font("Arial", Font.PLAIN, 16));
				lblIfYouHave.setBounds(32, 124, 200, 19);
				panel_5.add(lblIfYouHave);
				
				JLabel lblForFutureFeatures = new JLabel("for future features, please ");
				lblForFutureFeatures.setFont(new Font("Arial", Font.PLAIN, 16));
				lblForFutureFeatures.setBounds(42, 145, 179, 19);
				panel_5.add(lblForFutureFeatures);
				
				JLabel lblPostOnThe = new JLabel("post on the thread.");
				lblPostOnThe.setFont(new Font("Arial", Font.PLAIN, 16));
				lblPostOnThe.setBounds(66, 164, 148, 19);
				panel_5.add(lblPostOnThe);
		btnNewButton.setBounds(0, 234, 260, 28);
		contentPane.add(btnNewButton);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 28, 260, 206);
		contentPane.add(panel_4);
	}
}
