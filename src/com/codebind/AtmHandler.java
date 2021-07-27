package com.codebind;

import java.util.ArrayList;
import java.util.List;

public class AtmHandler {
    List<AtmDrawer> drawer;
    public AtmHandler(Integer defaultNoteStocks, Integer[] noteRange) {
        List<AtmDrawer> drawer = new ArrayList<AtmDrawer>();
        for (Integer integer : noteRange) {
            drawer.add(new AtmDrawer(integer, defaultNoteStocks));
        }
    }

    public Integer getDrawerTotal(Integer index) {
        return drawer.get(index).getTotal();
    }

    public Integer atmTotal() {
        Integer runningTotal = 0;
        for (AtmDrawer item : drawer) {
            runningTotal += item.getTotal();
        }
        return runningTotal;
    }
}


