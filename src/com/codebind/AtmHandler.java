package com.codebind;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;



public class AtmHandler {
    private List<AtmDrawer> allDrawers;

    /**
     * This class runs the entire ATM logic, an ATM class will also hold many drawers.
     * @param defaultNoteStocks, defines the amount of notes contained in each drawer.
     * @param noteRange, defines the types of notes held within the drawers.
     */
    public AtmHandler(Integer[] defaultNoteStocks, Integer[] noteRange) {
        List<AtmDrawer> drawer = new ArrayList<>();
        for (int i = 0; i < noteRange.length; i++) {
            drawer.add(new AtmDrawer(noteRange[i], defaultNoteStocks[i]));
        }
        allDrawers = drawer;
    }

    /** Sums the value of all drawers. */
    public Integer atmTotal() {
        Integer runningTotal = 0;
        for (AtmDrawer item : allDrawers) {
            runningTotal += item.getTotal();
        }
        return runningTotal;
    }

    /** Returns a read-only list of all drawers. */
    public List<AtmDrawer> GetAllDrawers() {
        return Collections.unmodifiableList(allDrawers);
    }

    /** Checks whether the drawers contain enough notes */
    public CheckResult checkDrawer(Double withdrawalAmount) {
       List<AtmDrawer> allDrawersCopy = new ArrayList<>();
        for (AtmDrawer item : allDrawers) {
            allDrawersCopy.add(new AtmDrawer(item.getNoteValue(), item.getQuantity()));
        }
        StringBuilder resultTxt = new StringBuilder("Notes: ");
        int withdraw = (int) (withdrawalAmount * 100);
        DecimalFormat df = new DecimalFormat("$#.00");
        // Check if ATM has enough money
        if (withdraw > atmTotal()) {
            // ERROR not enough money in the ATM
            return new CheckResult(false, "Not enough money in the ATM");
        } else {
            for (AtmDrawer item : allDrawersCopy) {
                Integer count = 0;
                var noteValue = item.getNoteValue();
                while (item.getQuantity() > 0 && withdraw >= noteValue){
                        count = count + 1;
                        withdraw = withdraw - noteValue;
                        item.subtractQuantity(1);
                }
                if (count > 0) {
                    resultTxt.append("\n");
                    resultTxt.append(count);
                    resultTxt.append(" * ");
                    resultTxt.append(df.format(item.getNoteValue() / 100));
                    resultTxt.append(" ");
                }
            }
        }
        if (withdraw != 0) {
            return new CheckResult(false, "Withdrawal amount could not be dispensed");
        } else {
            //update the ATM drawers to the new quantities
            for (int i = 0; i < allDrawers.size(); i++) {
                var item = allDrawers.get(i);
                item.setQuantity( allDrawersCopy.get(i).getQuantity());
            }
            return new CheckResult(true, resultTxt.toString());
        }
    }
}


