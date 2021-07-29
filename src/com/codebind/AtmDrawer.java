package com.codebind;

public class AtmDrawer {
    private Integer notes;
    private Integer quantity;
    public AtmDrawer(Integer value, Integer amount) {
        notes = value;
        quantity = amount;
    }

    public void subtractQuantity(Integer newValue) {
        quantity = quantity - newValue;
    }

    public Integer getNoteValue() {
        return notes;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getTotal() {
        return notes * quantity;
    }
}
