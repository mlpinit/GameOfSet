package com.mlpinit.set;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import java.util.ArrayList;

public class CardsPanel extends JPanel {

    public CardsPanel() {
        this.setLayout(new GridBagLayout());
        this.setSize(600, 600);
        this.setBackground(Color.white);
    }

    public void createDisplay(ArrayList<Card> cards) {
        this.removeAll();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;

        int height = 3;
        int width = cards.size() / height;
        int cardIndex = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                constraints.gridx = j;
                constraints.gridy = i;
                CardButton button = new CardButton(cards.get(cardIndex));
                GridBagLayout gridBagLayout = (GridBagLayout) this.getLayout();
                gridBagLayout.setConstraints(button, constraints);
                this.add(button);
                cardIndex++;
            }
        }
    }

}
