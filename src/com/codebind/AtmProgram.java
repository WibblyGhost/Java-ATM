package com.codebind;

import javax.swing.*;

public class AtmProgram {
    private JPanel panelMain;
    private JTextField textName;
    private JButton buttonName;
    private JButton showBalanceButton;
    private JButton withdrawCashButton;
    private JPanel panelMenu;
    private JPanel panelLogin;
    private JLabel labelName;

    private AtmHandler atm;

    public AtmProgram(AtmHandler newAtm) {
        this.atm = newAtm;
        buttonName.addActionListener(e -> {
            String txt = textName.getText();
            JOptionPane.showMessageDialog(null,"Welcome, " + txt);
            panelLogin.setVisible(false);
            panelMenu.setVisible(true);
        });
        withdrawCashButton.addActionListener(e -> {
            String response = JOptionPane.showInputDialog(null, "Input value:");
            try {
                double ans = Double.parseDouble(response);
                if (ans < 0) {
                    JOptionPane.showMessageDialog(null, "Input is negative.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (ans == 0) {
                    JOptionPane.showMessageDialog(null, "Input must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                checkDrawer(ans);
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Input not in number format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        showBalanceButton.addActionListener(e -> checkBalance());
    }

    public void checkDrawer(Double withdrawAmount) {
        AtmHandler oldAtm = atm;
        boolean succeeded = true;
        StringBuilder resultTxt = new StringBuilder("Notes: ");
        int withdraw = (int) (withdrawAmount * 100);
        // Check if ATM has enough money
        if (withdraw > atm.atmTotal()) {
            // ERROR not enough money in the ATM
            succeeded = false;
            JOptionPane.showMessageDialog(null, "Not enough notes.");
        } else {
            for (AtmDrawer item : atm.drawer) {
                Integer count = 0;
                while (item.getQuantity() > 0 && withdraw > 0){
                    if (item.getQuantity() <= withdraw) {
                        count = count + 1;
                        withdraw = withdraw - item.getNoteValue();
                        item.subtractQuantity(1);
                    }
                }
                if (count > 0) {
                    resultTxt.append(count);
                    resultTxt.append(" * $");
                    resultTxt.append(item.getNoteValue() / 100);
                    resultTxt.append(" ");
                }
            }
        }
        if (!succeeded) {
            // If the draws didn't have cash, return the ATM to it's old state.
            atm = oldAtm;
            checkBalance();
        } else {
            JOptionPane.showMessageDialog(null, resultTxt);
        }
    }

    public void checkBalance () {
        StringBuilder result = new StringBuilder("The ATM has $" + atm.atmTotal() / 100);
        for (var item : atm.drawer) {
            result.append("\n")
                    .append(item.getQuantity())
                    .append(" * $")
                    .append((double) item.getNoteValue() / 100.00);
        }
        JOptionPane.showMessageDialog(null, result.toString());
    }



    public static void main(String[] args) {
        //    List is in cents, $100 = 10000 c
        Integer[] defaultNoteStocks = {20, 20, 20, 20, 20, 20, 20, 20};
        Integer[] noteRange = {10000, 5000, 2000, 1000, 500, 100, 50, 20};
        AtmHandler atmMachine = new AtmHandler(defaultNoteStocks, noteRange);

        JFrame frame = new JFrame("Atm");
        // Pass in the atm machine
        frame.setContentPane(new AtmProgram(atmMachine).panelMain);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
