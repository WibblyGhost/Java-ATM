package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Atm {
    private JButton button_msg;
    private JPanel panelMain;
    private JTextField textField1;
    private JButton buttonName;
    private JButton showBalanceButton;
    private JButton withdrawCashButton;
    private JButton depositCashButton;
    private JPanel panelMenu;
    private JPanel panelLogin;
    private JLabel labelName;
    private JTextField txtName;
    private JLabel message;

    private AtmHandler atm;

    public Atm(AtmHandler atm) {
        this.atm = atm;
    }

    public void checkDrawer(Double withdrawAmount) {
        Integer withdraw = (int) (withdrawAmount * 100);
        // Check if ATM has enough money
        if (withdraw >= atm.atmTotal()) {
            // ERROR not enough money in the ATM
        } else {
            for (AtmDrawer item : atm.drawer) {

            }
        }
    }


    public void Atm() {
        buttonName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = textField1.getText();
                JOptionPane.showMessageDialog(null,"Welcome, " + txt);
                panelLogin.setVisible(false);
                panelMenu.setVisible(true);
            }
        });

        withdrawCashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double ans = Double.parseDouble(JOptionPane.showInputDialog(null, "Input value:"));
                checkDrawer(ans);
            }
        });
    }

    public static void main(String[] args) {
        //    List is in cents, $100 = 10000 c
        Integer defaultNoteStocks = 20;
        Integer[] noteRange = {10000, 5000, 2000, 1000, 500, 100, 50, 20};
        AtmHandler atmMachine = new AtmHandler(defaultNoteStocks, noteRange);

        JFrame frame = new JFrame("Atm");
        // Pass in the atm machine
        frame.setContentPane(new Atm(atmMachine).panelMain);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
