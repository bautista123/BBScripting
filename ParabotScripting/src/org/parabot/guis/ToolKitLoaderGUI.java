package org.parabot.guis;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Font;

public class ToolKitLoaderGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToolKitLoaderGUI frame = new ToolKitLoaderGUI();
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
	public ToolKitLoaderGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 182, 124);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 36, 146, 14);
		contentPane.add(progressBar);
		
		JLabel lblBbtoolkit = new JLabel("bbToolKit");
		lblBbtoolkit.setFont(new Font("Arial", Font.PLAIN, 16));
		lblBbtoolkit.setBounds(53, 11, 73, 14);
		contentPane.add(lblBbtoolkit);
		
		JLabel lblLoading = new JLabel("Loading...");
		lblLoading.setFont(new Font("Arial", Font.PLAIN, 16));
		lblLoading.setBounds(53, 61, 73, 18);
		contentPane.add(lblLoading);
	}
}
