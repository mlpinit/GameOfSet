package com.mlpinit.set;

public enum Filling {
    Empty (0),
    Striped (1),
    Full (2);

    private int value;
    private Filling(int v) {
        this.value = v;
    }

    public int getValue() {
        return this.value;
    }

    public String initial() {
        if (value == 0) return "E";
        if (value == 1) return "S";
        if (value == 2) return "F";
        return "";
    }
}
