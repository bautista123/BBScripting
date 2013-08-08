package org.parabot.bbhunter.gui;

import org.parabot.bbhunter.data.Var;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * DO NOT STEAL MY CODE ASSHOLE
 * User: Bautista
 * Date: 8/2/13
 * DO NOT STEAL MY CODE, I'LL KILL YOUR FAMILY
 */
public class GUI extends JFrame {


    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public void main(String[] args) {
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

        setTitle("BBHunter");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 185, 171);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 169, 133);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblImpToMine = new JLabel("Imp To Catch:");
        lblImpToMine.setBounds(38, 34, 97, 14);
        panel.add(lblImpToMine);
        lblImpToMine.setFont(new Font("Tahoma", Font.PLAIN, 14));

        final JComboBox comboBox = new JComboBox();
        comboBox.setBounds(38, 59, 89, 29);
        panel.add(comboBox);
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Baby",
                "Young", "Gourmet", "Earth", "Essence", "Eclectic",
                "Nature", "Magpie", "Ninja", "Dragon"}));
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JButton btnStart = new JButton("Start");
        btnStart.setBounds(38, 99, 89, 23);
        panel.add(btnStart);
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String chosenImp = comboBox.getSelectedItem().toString();
                if (chosenImp.equalsIgnoreCase("Dragon")) {
                    Var.setImpling(6064);
                    Var.setMoney(10000);
                } else if (chosenImp.equalsIgnoreCase("Ninja")) {
                    Var.setImpling(6063);
                    Var.setMoney(7000);
                } else if (chosenImp.equalsIgnoreCase("Magpie")) {
                    Var.setImpling(6062);
                    Var.setMoney(5000);
                } else if (chosenImp.equalsIgnoreCase("nature")) {
                    Var.setImpling(6061);
                    Var.setMoney(3000);
                } else if (chosenImp.equalsIgnoreCase("eclectic")) {
                    Var.setImpling(6060);
                    Var.setMoney(3000);
                } else if (chosenImp.equalsIgnoreCase("essence")) {
                    Var.setImpling(6059);
                    Var.setMoney(3000);
                } else if (chosenImp.equalsIgnoreCase("earth")) {
                    Var.setImpling(6058);
                    Var.setMoney(3000);
                } else if (chosenImp.equalsIgnoreCase("gourmet")) {
                    Var.setImpling(6057);
                    Var.setMoney(3000);
                } else if (chosenImp.equalsIgnoreCase("young")) {
                    Var.setImpling(6056);
                    Var.setMoney(3000);
                } else if (chosenImp.equalsIgnoreCase("baby")) {
                    Var.setImpling(6055);
                    Var.setMoney(3000);
                }
                Var.setGuiwait(false);
                dispose();
            }
        });
        btnStart.setFont(new Font("Tahoma", Font.PLAIN, 14));

    }
}


