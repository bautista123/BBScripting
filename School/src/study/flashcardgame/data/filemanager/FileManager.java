package study.flashcardgame.data.filemanager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;

import study.flashcardgame.data.Variables;

public class FileManager {
	static JFileChooser chooser = new JFileChooser();

	public static void SaveFile() {
		int retrival = chooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			try (FileWriter writer = new FileWriter(chooser.getSelectedFile()
					+ ".txt")) {
				writeFile();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void OpenFile() {
		int retrival = chooser.showOpenDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			try (BufferedReader br = new BufferedReader(new FileReader(
					chooser.getSelectedFile()))) {
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) {
					System.out.println(sCurrentLine);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void writeFile() {
		try (FileWriter writer = new FileWriter(chooser.getSelectedFile()
				+ ".txt")) {
			writer.write("Card Array Subject: " + Variables.getSubject()
					+ "\r\n");
			writer.write("____________________________________________________________"
					+ "\r\n");
			for (int i = 0; i < Variables.getStatistics().size(); i++) {
				if (i == 0) {
					writer.write("Amount of Questions: "
							+ Variables.getStatistics().get(i) + "\r\n");
				} else if (i == 1) {
					writer.write("Amount of Essay Questions: "
							+ Variables.getStatistics().get(i) + "\r\n");
				} else if (i == 2) {
					writer.write("Amount of Broken Questions: "
							+ Variables.getStatistics().get(i) + "\r\n");
				} else if (i == 3) {
					writer.write("Amount of Categories: "
							+ Variables.getStatistics().get(i) + "\r\n");
				}
			}
			writer.write("____________________________________________________________"
					+ "\r\n");
			writer.write("");
			for (int j = 0; j < Variables.getCardNumber().size(); j++) {
				writer.write("Question: " + Variables.getCards()[j][0] + "\r\n");
				writer.write("Answer: " + Variables.getCards()[j][1] + "\r\n");
				writer.write("Category: " + Variables.getCards()[j][2] + "\r\n");
				if (Variables.getCards()[j].length > 3) {
					writer.write("Essay Question Possibility: "
							+ Variables.getCards()[j][3] + "\r\n");
				} else {
					writer.write("Essay Question Possibility: False" + "\r\n");
				}
				writer.write("____________________________________________________________"
						+ "\r\n");
			}
			writer.close();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
