package org.parabot.csThiever.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.parabot.csThiever.data.csThieverVariables;

public class csThieverGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	public csThieverGUI() {
		initComponents();
		this.setTitle("csThiever");
		this.setResizable(false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jButton1 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setFont(new java.awt.Font("EDGE", 0, 20)); // NOI18N
		jLabel1.setText("csThiever");

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Food Stall", "Scimitar Stall", "Fish Stall", "Silver Stall",
				"Crafting Stall" }));
		jComboBox1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox1ActionPerformed(evt);
			}
		});

		jButton1.setText("Start");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(37, 37,
																		37)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jComboBox1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						127,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel1)))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(67, 67,
																		67)
																.addComponent(
																		jButton1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		66,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(18, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										58,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jComboBox1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										29,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButton1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										34,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));

		pack();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

		if ("Food Stall".equals(jComboBox1.getSelectedItem().toString())) {
			csThieverVariables.setStallChosen(0);
		}

		if ("Scimitar Stall".equals(jComboBox1.getSelectedItem().toString())) {
			csThieverVariables.setStallChosen(1);
		}

		if ("Fish Stall".equals(jComboBox1.getSelectedItem().toString())) {
			csThieverVariables.setStallChosen(2);
		}
		if ("Silver Stall".equals(jComboBox1.getSelectedItem().toString())) {
			csThieverVariables.setStallChosen(3);
		}
		if ("Crafting Stall".equals(jComboBox1.getSelectedItem().toString())) {
			csThieverVariables.setStallChosen(4);
		}
		csThieverVariables.setIsRunning(false);
		dispose();
	}

	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
	}

	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					csThieverGUI frame = new csThieverGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private javax.swing.JButton jButton1;
	@SuppressWarnings("rawtypes")
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JLabel jLabel1;
}
