package com.codebind;

import java.util.ArrayList;
import java.util.List;

public class AtmHandler {
    List<AtmDrawer> drawer;
    public AtmHandler(Integer[] defaultNoteStocks, Integer[] noteRange) {
        List<AtmDrawer> drawer = new ArrayList<AtmDrawer>();
        for (Integer i = 0; i < noteRange.length; i++) {
            drawer.add(new AtmDrawer(noteRange[i], defaultNoteStocks[i]));
        }
        this.drawer = drawer;
    }


    public Integer atmTotal() {
        Integer runningTotal = 0;
        for (AtmDrawer item : drawer) {
            runningTotal += item.getTotal();
        }
        return runningTotal;
    }
}


