package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class AtmProgram {
    private JPanel panelMain;
    private JTextField textName;
    private JButton buttonName;
    private JButton showBalanceButton;
    private JButton withdrawCashButton;
    private JPanel panelMenu;
    private JPanel panelLogin;
    private JLabel panelUsername;
    private JLabel labelName;
    private AtmHandler atmInstance;

    /**
     * Defines the ATM program which dispenses notes to teh users.
     * @param newAtm, inputs an AtmHandler object which will run the ATM simulation.
     */
    public AtmProgram(AtmHandler newAtm) {
        this.atmInstance = newAtm;
        buttonName.addActionListener(e -> {
            panelUsername.setText("Welcome " + textName.getText());
            panelLogin.setVisible(false);
            panelMenu.setVisible(true);
        });
        textName.addActionListener(e -> {
            panelUsername.setText("Welcome " + textName.getText());
            panelLogin.setVisible(false);
            panelMenu.setVisible(true);
        });
        withdrawCashButton.addActionListener(e -> {
            String response = JOptionPane.showInputDialog(null, "Withdrawal Amount:");
            try {
                double amount = Double.parseDouble(response);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Withdrawal amount should be greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    var checkResult = atmInstance.checkDrawer(amount);
                    JOptionPane.showMessageDialog(null, checkResult.message);
                    if (!checkResult.isSuccess) {
                        checkBalance(); //show the drawer contents
                    }
                }
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Withdrawal should be numeric", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        showBalanceButton.addActionListener(e -> checkBalance());
    }

    /** Shows a breakdown of the drawer type and the number of notes in the drawer and checks whether the withdrawal can be made. */
    private void checkBalance () {
        DecimalFormat df = new DecimalFormat("$0.00");
        StringBuilder result = new StringBuilder("The ATM has " + df.format(atmInstance.atmTotal() / 100.00));
        for (var item : atmInstance.GetAllDrawers()) {
            result.append("\n")
                    .append(item.getQuantity())
                    .append(" * ")
                    .append(df.format((double) item.getNoteValue() / 100.00));
        }
        JOptionPane.showMessageDialog(null, result.toString());
    }

    /** Initial function which is called on startup. */
    public static void main(String[] args) {
        //    List is in cents, $100 = 10000 c
        Integer[] defaultNoteStocks = {20, 20, 20, 20, 20, 20, 20, 20, 20};
        Integer[] noteRange = {10000, 5000, 2000, 1000, 500, 100, 50, 20, 10};
        AtmHandler atmMachine = new AtmHandler(defaultNoteStocks, noteRange);
        JFrame frame = new JFrame("Atm");
        // Pass in the atm machine
        frame.setContentPane(new AtmProgram(atmMachine).panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
