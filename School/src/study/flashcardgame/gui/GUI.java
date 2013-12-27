package study.flashcardgame.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import study.flashcardgame.core.Core;
import study.flashcardgame.data.Variables;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setBackground(UIManager.getColor("Button.background"));
		setTitle("Bautista's Religion Review");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JTextArea txtrHi = new JTextArea();
		txtrHi.setText("");
		txtrHi.setEditable(false);
		txtrHi.setBounds(44, 42, 499, 310);
		contentPane.add(txtrHi);

		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(227, 11, 129, 20);
		contentPane.add(lblNewLabel);

		final JLabel lblNewLabel_1 = new JLabel("");
		final JButton btnShowAnswer = new JButton("Show Answer");
		btnShowAnswer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		final JButton btnShowQuestion = new JButton("Show Question");
		btnShowQuestion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnShowQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Core.CheckEssay();
				if (btnShowQuestion.getText() == "Next Question") {
					lblNewLabel_1.setText("");
					Variables.setEssay(false);
					btnShowQuestion.setText("Show Question");
					txtrHi.setText("");
					btnShowAnswer.setEnabled(false);
					lblNewLabel.setText("");
				} else if (btnShowQuestion.getText() == "Show Question") {
					if (Variables.getEssay()) {
						lblNewLabel_1.setText("POSSIBLE ESSAY QUESTION");
					} else if (Variables.getEmptyArray()) {
						lblNewLabel_1.setText("YOU HAVE NO QUESTIONS");
					} else {
						lblNewLabel_1.setText("");
					}
					Variables.setQuestionsSeen(Variables.getQuestionsSeen() + 1);
					btnShowQuestion.setEnabled(false);
					txtrHi.setText("Category: " + Core.getCardCatagory()
							+ "\n \n" + Core.getCardQuestion());
					btnShowAnswer.setEnabled(true);
					lblNewLabel.setText("Question");
				}
			}
		});
		btnShowQuestion.setBounds(44, 363, 134, 23);
		contentPane.add(btnShowQuestion);

		btnShowAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Core.CheckEssay();
				if (txtrHi.getText().contains(Core.getCardQuestion())) {
					if (Variables.getEssay()) {
						lblNewLabel_1.setText("POSSIBLE ESSAY QUESTION");
					} else if (Variables.getEmptyArray()) {
						lblNewLabel_1.setText("YOU HAVE NO QUESTIONS");
					} else {
						lblNewLabel_1.setText("");
					}
					btnShowQuestion.setEnabled(true);
					txtrHi.setText("Category: " + Core.getCardCatagory()
							+ "\n \n" + Core.getCardAnswer());
					btnShowQuestion.setText("Next Question");
					btnShowAnswer.setEnabled(false);
					Variables.setIndex(Variables.getIndex() + 1);
					Core.LoopIndex();
					lblNewLabel.setText("Answer");
				}
			}
		});
		btnShowAnswer.setBounds(422, 363, 121, 23);
		contentPane.add(btnShowAnswer);

		lblNewLabel_1.setForeground(new Color(204, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(188, 363, 224, 23);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Statistics");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatsGUI g = new StatsGUI();
				g.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(227, 406, 129, 23);
		contentPane.add(btnNewButton);

		JButton btnOptions = new JButton("Options");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OptionsGUI gui = new OptionsGUI();
				gui.setVisible(true);
			}
		});
		btnOptions.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOptions.setBounds(422, 407, 121, 21);
		contentPane.add(btnOptions);

		JButton btnNewButton_1 = new JButton("Previous");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(44, 406, 134, 23);
		contentPane.add(btnNewButton_1);
	}
}