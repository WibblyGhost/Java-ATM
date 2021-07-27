package com.codebind;

public class AtmDrawer {
    private Integer notes;
    private Integer quantity;
    public AtmDrawer(Integer value, Integer amount) {
        notes = value;
        quantity = amount;
    }

    public Integer getValue() {
        return notes;
    }

    public Integer getAmount() {
        return quantity;
    }

    public Integer getTotal() {
        return notes * quantity;
    }
}
