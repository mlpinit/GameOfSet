package com.mlpinit.set;

public enum Shape {
    Squiggle (0),
    Diamond (1),
    Oval (2);

    private int value;
    private Shape(int v) {
        this.value = v;
    }

    public int getValue() {
        return this.value;
    }

    public String initial() {
        if (value == 0) return "S";
        if (value == 1) return "D";
        if (value == 2) return "O";
        return "";
    }
}
