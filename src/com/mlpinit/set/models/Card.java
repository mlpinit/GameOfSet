package com.mlpinit.set;

import java.net.URL;

public class Card {
    private Shape shape;
    private Filling filling;
    private Color color;
    private int count;
    private boolean selected;
    private String darkImageLocation;
    private String lightImageLocation;

    public Card(Color color, Shape shape, Filling filling, int count) {
        this.shape = shape;
        this.color = color;
        this.filling = filling;
        this.count = count;
        this.selected = false;
        this.darkImageLocation = toString() + "D" + ".png";
        this.lightImageLocation = toString() + "L" + ".png";
    }

    public URL getImageURL() {
        return getClass().getResource(getImageLocation());
    }

    public String getDarkImageLocation() {
        return darkImageLocation;
    }

    public String getLightImageLocation() {
        return lightImageLocation;
    }

    public int numValue() {
        int value = 0;
        value = value * 10 + (color.getValue() + 1);
        value = value * 10 + (shape.getValue() + 1);
        value = value * 10 + (filling.getValue() + 1);
        value = value * 10 + count;
        return value;
    }

    public void toggleSelection() {
        this.selected = !selected;
    }

    public void unselect() {
        this.selected = false;
    }

    public boolean getSelected() {
        return selected;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(5);
        sb.append("/");
        sb.append(color.initial());
        sb.append(shape.initial());
        sb.append(filling.initial());
        sb.append(count);
        return sb.toString();
    }

    private String getImageLocation() {
        return selected ? darkImageLocation : lightImageLocation;
    }

}
