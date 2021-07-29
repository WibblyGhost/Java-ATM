package com.codebind;

public class AtmDrawer {
    private Integer noteValue;
    private Integer quantity;

    /**
     * This class contains all necessary components to implement cash draws, each draw contains a value or $ amount, and a quantity.
     * @param value, defines the cash value of drawer.
     * @param amount, defines the number of notes contained in the drawer.
     */
    public AtmDrawer(Integer value, Integer amount) {
        noteValue = value;
        quantity = amount;
    }

    /** When removing a note from the ATM, the drawer will subtract one from its quantity. */
    public void subtractQuantity(Integer newValue) {
        quantity = quantity - newValue;
    }

    /** When called, returns the value of the draw, way $50. */
    public Integer getNoteValue() {
        return noteValue;
    }

    /** When called, returns the number of notes in the drawer. */
    public Integer getQuantity() {
        return quantity;
    }

    /** Sets the quantity of notes in given drawer. */
    public void setQuantity(Integer newQuantity) {
        quantity  = newQuantity;
    }

    /** Returns a total evaluation of cash in the drawer. */
    public Integer getTotal() {
        return noteValue * quantity;
    }
}
