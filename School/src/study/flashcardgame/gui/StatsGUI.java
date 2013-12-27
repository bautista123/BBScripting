package study.flashcardgame.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import study.flashcardgame.data.Variables;

public class StatsGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatsGUI frame = new StatsGUI();
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
	public StatsGUI() {
		setTitle("Statistics");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 272, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNumberOfQuestions = new JLabel("Number of Questions: "
				+ Variables.getStatistics().get(0));
		lblNumberOfQuestions.setBounds(10, 11, 155, 14);
		contentPane.add(lblNumberOfQuestions);

		JLabel lblNumberOfEssay = new JLabel("Number of Essay Questions: "
				+ Variables.getStatistics().get(1));
		lblNumberOfEssay.setBounds(10, 45, 179, 14);
		contentPane.add(lblNumberOfEssay);

		JLabel lblNumberOfBroken = new JLabel("Number of Broken Questions: "
				+ Variables.getStatistics().get(2));
		lblNumberOfBroken.setBounds(10, 83, 179, 14);
		contentPane.add(lblNumberOfBroken);

		JLabel lblNumberOfCategories = new JLabel("Number of Categories: "
				+ Variables.getStatistics().get(3));
		lblNumberOfCategories.setBounds(10, 121, 155, 14);
		contentPane.add(lblNumberOfCategories);

		JLabel lblNumberOfQuestions_1 = new JLabel("Number of Questions Seen: "
				+ Variables.getQuestionsSeen());
		lblNumberOfQuestions_1.setBounds(10, 155, 179, 14);
		contentPane.add(lblNumberOfQuestions_1);

		JLabel lblSubject = new JLabel("Subject: " + Variables.getSubject());
		lblSubject.setBounds(10, 187, 179, 14);
		contentPane.add(lblSubject);
	}
}
