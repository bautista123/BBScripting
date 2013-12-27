package org.parabot.bbherblore.guis;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.parabot.bbherblore.data.HerbloreConstants;
import org.parabot.bbherblore.data.HerbloreVariables;
import org.parabot.core.ui.components.LogArea;

public class HerbloreMainGUI extends JFrame {
	private JPanel contentPane;
	public static String[] devious = { "Extremes", "Overloads", "Clean Herbs",
			"Spawn Supplies" };
	public static String[] reckless = { "Extremes", "Clean Herbs",
			"Spawn Supplies" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HerbloreMainGUI frame = new HerbloreMainGUI();
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
	public HerbloreMainGUI() {
		setEnabled(false);
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						HerbloreMainGUI.class
								.getResource("/org/parabot/core/ui/images/category/herblore.png")));
		setTitle("bbHerblore");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Extremes",
				"Spawn Supplies", "Clean Herbs" }));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setBounds(272, 30, 156, 23);
		contentPane.add(comboBox);

		JSeparator separator_2 = new JSeparator(SwingConstants.VERTICAL);
		separator_2.setBounds(220, 64, 10, 211);
		contentPane.add(separator_2);

		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setBounds(111, 0, 10, 275);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 64, 442, 2);
		contentPane.add(separator_1);

		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Guam",
				"Ranarr", "Torstol" }));
		comboBox_1.setBounds(111, 148, 108, 20);
		contentPane.add(comboBox_1);

		JLabel lblHerbType = new JLabel("Herb Type: ");
		lblHerbType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHerbType.setBounds(131, 89, 79, 20);
		contentPane.add(lblHerbType);

		final JCheckBox chckbxStopAt = new JCheckBox("Use best herb");
		chckbxStopAt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxStopAt.isSelected()) {
					comboBox_1.setEnabled(false);
					HerbloreVariables.setUseBest(true);
				} else if (!chckbxStopAt.isSelected()) {
					comboBox_1.setEnabled(true);
					HerbloreVariables.setUseBest(false);
				}
			}
		});
		chckbxStopAt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxStopAt.setBounds(113, 195, 108, 23);
		contentPane.add(chckbxStopAt);

		JLabel lblWhatToSpawn = new JLabel("What to spawn: ");
		lblWhatToSpawn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWhatToSpawn.setBounds(272, 72, 132, 21);
		contentPane.add(lblWhatToSpawn);

		final JCheckBox chckbxTorstols = new JCheckBox("Torstols");
		chckbxTorstols.setBounds(238, 114, 81, 20);
		contentPane.add(chckbxTorstols);

		final JCheckBox chckbxLant = new JCheckBox("Lantadyme");
		chckbxLant.setBounds(228, 145, 81, 23);
		contentPane.add(chckbxLant);

		final JCheckBox chckbxAvantoe = new JCheckBox("Avantoe");
		chckbxAvantoe.setBounds(228, 171, 97, 23);
		contentPane.add(chckbxAvantoe);

		final JCheckBox chckbxNewCheckBox = new JCheckBox("Dwarf Weed");
		chckbxNewCheckBox.setBounds(316, 113, 93, 23);
		contentPane.add(chckbxNewCheckBox);

		final JCheckBox chckbxMudRunes = new JCheckBox("Mud Runes");
		chckbxMudRunes.setBounds(327, 171, 97, 23);
		contentPane.add(chckbxMudRunes);

		final JCheckBox chckbxGrenwallSpikes = new JCheckBox("Grenwall Spikes");
		chckbxGrenwallSpikes.setBounds(327, 145, 108, 23);
		contentPane.add(chckbxGrenwallSpikes);

		final JCheckBox chckbxSuperAttack = new JCheckBox("Super Attack");
		chckbxSuperAttack.setBounds(228, 196, 97, 23);
		contentPane.add(chckbxSuperAttack);

		final JCheckBox chckbxSuperStrength = new JCheckBox("Super Strength");
		chckbxSuperStrength.setBounds(327, 196, 108, 23);
		contentPane.add(chckbxSuperStrength);

		final JCheckBox chckbxSuperDefense = new JCheckBox("Super Defense");
		chckbxSuperDefense.setBounds(228, 222, 97, 23);
		contentPane.add(chckbxSuperDefense);

		final JCheckBox chckbxRangePotion = new JCheckBox("Range Potion");
		chckbxRangePotion.setBounds(327, 222, 97, 23);
		contentPane.add(chckbxRangePotion);

		final JCheckBox chckbxMagicPotion = new JCheckBox("Magic Potion");
		chckbxMagicPotion.setBounds(228, 244, 97, 23);
		contentPane.add(chckbxMagicPotion);

		final JCheckBox chckbxEverything = new JCheckBox("Everything");
		chckbxEverything.setBounds(327, 248, 97, 23);
		contentPane.add(chckbxEverything);

		final JComboBox comboBox_2 = new JComboBox();
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_2.getSelectedItem().toString()
						.equalsIgnoreCase("recklesspk")) {
					comboBox.setModel(new DefaultComboBoxModel(reckless));
					HerbloreVariables.setRecklessPK(true);
					LogArea.log("true");
				} else if (comboBox_2.getSelectedItem().toString()
						.equalsIgnoreCase("deviouspk")) {
					comboBox.setModel(new DefaultComboBoxModel(devious));
					HerbloreVariables.setRecklessPK(false);
					LogArea.log("false");
				}

			}
		});
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {
				"RecklessPK", "DeviousPK" }));
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_2.setBounds(131, 30, 116, 23);
		contentPane.add(comboBox_2);

		JLabel lblServer = new JLabel("Server: ");
		lblServer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblServer.setBounds(164, 5, 66, 14);
		contentPane.add(lblServer);

		JLabel lblWhatToDo = new JLabel("What To Do: ");
		lblWhatToDo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWhatToDo.setBounds(303, 5, 101, 14);
		contentPane.add(lblWhatToDo);

		JButton btnStart = new JButton("Start");
		btnStart.setIcon(new ImageIcon(
				HerbloreMainGUI.class
						.getResource("/org/parabot/core/ui/images/category/herblore.png")));
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString()
						.equals("Spawn Supplies")) {
					HerbloreVariables.setSpawn(true);
					HerbloreVariables.setMakeOvls(false);
					HerbloreVariables.setMakeXT(false);
					HerbloreVariables.setCleanHerbs(false);
					if (chckbxTorstols.isSelected()) {
						HerbloreVariables.setSpawnTorstols(true);
						HerbloreConstants.stackList
								.add(HerbloreConstants.torstol + 1);
					}
					if (chckbxLant.isSelected()) {
						HerbloreVariables.setSpawnLanta(true);
						HerbloreConstants.stackList
								.add(HerbloreConstants.lanta + 1);
					}
					if (chckbxAvantoe.isSelected()) {
						HerbloreVariables.setSpawnAvan(true);
						HerbloreConstants.stackList
								.add(HerbloreConstants.avantoe + 1);
					}
					if (chckbxNewCheckBox.isSelected()) {
						HerbloreVariables.setSpawnDwarf(true);
						HerbloreConstants.stackList
								.add(HerbloreConstants.dwarfWeed + 1);
					}
					if (chckbxMudRunes.isSelected()) {
						HerbloreVariables.setSpawnMud(true);
						HerbloreConstants.unstackList
								.add(HerbloreConstants.mudRunes);
					}
					if (chckbxGrenwallSpikes.isSelected()) {
						HerbloreVariables.setSpawnSpikes(true);
						HerbloreConstants.unstackList
								.add(HerbloreConstants.gSpikes);
					}
					if (chckbxSuperAttack.isSelected()) {
						HerbloreVariables.setSpawnAtt(true);
						HerbloreConstants.stackList
								.add(HerbloreConstants.supAtt + 1);
					}
					if (chckbxSuperStrength.isSelected()) {
						HerbloreVariables.setSpawnStr(true);
						HerbloreConstants.stackList
								.add(HerbloreConstants.supStr + 1);
					}
					if (chckbxSuperDefense.isSelected()) {
						HerbloreVariables.setSpawnDef(true);
						HerbloreConstants.stackList
								.add(HerbloreConstants.supDef + 1);
					}
					if (chckbxRangePotion.isSelected()) {
						HerbloreVariables.setSpawnRange(true);
						HerbloreConstants.stackList
								.add(HerbloreConstants.rangePot + 1);
					}
					if (chckbxMagicPotion.isSelected()) {
						HerbloreVariables.setSpawnMage(true);
						HerbloreConstants.stackList
								.add(HerbloreConstants.magePot + 1);
					}
					if (chckbxEverything.isSelected()) {
						HerbloreConstants.stackList
								.add(HerbloreConstants.avantoe + 1);
						HerbloreConstants.stackList
								.add(HerbloreConstants.dwarfWeed + 1);
						HerbloreConstants.unstackList
								.add(HerbloreConstants.mudRunes);
						HerbloreConstants.unstackList
								.add(HerbloreConstants.gSpikes);
						HerbloreConstants.stackList
								.add(HerbloreConstants.supAtt + 1);
						HerbloreConstants.stackList
								.add(HerbloreConstants.supStr + 1);
						HerbloreConstants.stackList
								.add(HerbloreConstants.supDef + 1);
						HerbloreConstants.stackList
								.add(HerbloreConstants.rangePot + 1);
						HerbloreConstants.stackList
								.add(HerbloreConstants.magePot + 1);
						HerbloreConstants.stackList
								.add(HerbloreConstants.lanta + 1);
						HerbloreConstants.stackList
								.add(HerbloreConstants.torstol + 1);
						HerbloreVariables.setSpawnLanta(true);
						HerbloreVariables.setSpawnAvan(true);
						HerbloreVariables.setSpawnMud(true);
						HerbloreVariables.setSpawnDwarf(true);
						HerbloreVariables.setSpawnSpikes(true);
						HerbloreVariables.setSpawnAtt(true);
						HerbloreVariables.setSpawnStr(true);
						HerbloreVariables.setSpawnDef(true);
						HerbloreVariables.setSpawnRange(true);
						HerbloreVariables.setSpawnAtt(true);
						HerbloreVariables.setSpawnMage(true);
					}
				} else if (comboBox.getSelectedItem().toString()
						.equals("Overloads")) {
					HerbloreVariables.setSpawn(false);
					HerbloreVariables.setMakeOvls(true);
					HerbloreVariables.setMakeXT(false);
					HerbloreVariables.setCleanHerbs(false);
				} else if (comboBox.getSelectedItem().toString()
						.equals("Extremes")) {
					HerbloreVariables.setXtCount(0);
					HerbloreVariables.setMakeXT(true);
					HerbloreVariables.setSpawn(false);
					HerbloreVariables.setMakeOvls(false);
					HerbloreVariables.setCleanHerbs(false);
				} else if (comboBox.getSelectedItem().toString()
						.equals("Clean Herbs")) {
					HerbloreVariables.setCleanHerbs(true);
					HerbloreVariables.setMakeXT(false);
					HerbloreVariables.setSpawn(false);
					HerbloreVariables.setMakeOvls(false);
					if (!HerbloreVariables.getUseBest()) {
						if (comboBox_1.getSelectedItem().toString()
								.equals("Guam")) {
							HerbloreVariables.setHerb(199);
							HerbloreVariables.setHerbName("Guam");
						} else if (comboBox_1.getSelectedItem().toString()
								.equals("Torstol")) {
							HerbloreVariables.setHerbName("Torstol");
							HerbloreVariables.setHerb(219);
						}
						if (comboBox_1.getSelectedItem().toString()
								.equals("Ranarr")) {
							HerbloreVariables.setHerb(207);
							HerbloreVariables.setHerbName("Ranarr");
						}
					}
				}
				HerbloreVariables.setWait(false);
				dispose();
			}
		});
		btnStart.setBounds(111, 274, 331, 23);
		contentPane.add(btnStart);

		JLabel lblAuthor = new JLabel("When needed.");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAuthor.setBounds(10, 152, 93, 14);
		contentPane.add(lblAuthor);

		JLabel lblBautista = new JLabel("Will be added");
		lblBautista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBautista.setBounds(10, 115, 91, 14);
		contentPane.add(lblBautista);

		JLabel lblOther = new JLabel("Other ");
		lblOther.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOther.setBounds(31, 11, 56, 14);
		contentPane.add(lblOther);

		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSettings.setBounds(21, 30, 66, 23);
		contentPane.add(lblSettings);
	}
}
