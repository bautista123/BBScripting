package study.flashcardgame.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import study.flashcardgame.data.filemanager.FileManager;

public class OptionsGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionsGUI frame = new OptionsGUI();
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
	public OptionsGUI() {
		setTitle("Options");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 272, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileManager.SaveFile();
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(79, 50, 89, 23);
		contentPane.add(btnSave);

		JLabel lblQuestionsOptions = new JLabel("Questions Options");
		lblQuestionsOptions.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuestionsOptions.setBounds(64, 25, 118, 19);
		contentPane.add(lblQuestionsOptions);

		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileManager.OpenFile();
			}
		});
		btnOpen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOpen.setBounds(79, 95, 89, 27);
		contentPane.add(btnOpen);
	}

}
