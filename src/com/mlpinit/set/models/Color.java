package com.mlpinit.set;

public enum Color {
    Red (0),
    Green (1),
    Mauve (2);

    private int value;
    private Color(int v) {
        this.value = v;
    }

    public int getValue() {
        return this.value;
    }

    public String initial() {
        if (value == 0) return "R";
        if (value == 1) return "G";
        if (value == 2) return "M";
        return "";
    }
}
