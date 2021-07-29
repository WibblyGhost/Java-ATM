package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AtmProgram {
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

    public AtmProgram(AtmHandler newAtm) {
        this.atm = newAtm;
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

    public void checkDrawer(Double withdrawAmount) {
        Integer remainder = 0;
        Integer result = 0;
        AtmHandler oldAtm = atm;
        Boolean succeeded = true;
        StringBuilder resultTxt = new StringBuilder("Notes: ");

        long withdraw = (long) (withdrawAmount * 100);
        // Check if ATM has enough money
        if (withdraw > atm.atmTotal()) {
            // ERROR not enough money in the ATM
            succeeded = false;
        } else {
            for (AtmDrawer item : atm.drawer) {
                Integer count = 0;
                while (item.getAmount() > 0 && withdraw > 0){
                    count = count + 1;
                    withdraw = withdraw - item.getValue();
                    item.subtractQuantity(1);
                }
                if (count > 0) {
                    resultTxt.append(count);
                    resultTxt.append(" * $");
                    resultTxt.append(item.getValue() / 100);
                    resultTxt.append(" ");
                }
            } if (!succeeded) {
                // If the draws didn't have cash, return the ATM to it's old state.
                atm = oldAtm;
            } else {
                this.atm = atm;
                JOptionPane.showMessageDialog(null, resultTxt);
            }
        }
    }




    public static void main(String[] args) {
        //    List is in cents, $100 = 10000 c
        Integer defaultNoteStocks = 20;
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
