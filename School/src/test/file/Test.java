package test.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class Test {
	static JFileChooser chooser = new JFileChooser();
	static ArrayList<String> field = new ArrayList<String>();
	static String[][] card;

	public static void OpenFile(String i) {
		int retrival = chooser.showOpenDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			String line;

			try (BufferedReader br = new BufferedReader(new FileReader(
					chooser.getSelectedFile()))) {
				while ((line = br.readLine()) != null) {
					String[] splitted = line.split(i);

					if (splitted.length == 2) {
						field.add(splitted[1]);

					}

				}
				br.close();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}
}
