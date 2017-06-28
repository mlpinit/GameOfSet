package com.mlpinit.set;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;


import java.util.ArrayList;

public class CardsPanel extends JPanel {

    private MainFrame mainFrame;
    private final String endGameMessage = "Congratulations! You have finished the game!";

    public CardsPanel(MainFrame frame) {
        this.mainFrame = frame;
        this.setLayout(new GridBagLayout());
        this.setSize(600, 600);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
    }

    public void updateDisplay(ArrayList<Card> cards) {
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
                CardButton button = new CardButton(cards.get(cardIndex), mainFrame);
                gridBagLayout().setConstraints(button, constraints);
                this.add(button);
                cardIndex++;
            }
        }
        this.validate();
        this.repaint();
    }

    public void displayEndGame() {
        this.removeAll();
        this.setBackground(Color.gray);
        this.setOpaque(true);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel endGameMessageLabel = new JLabel(endGameMessage);
        endGameMessageLabel.setHorizontalAlignment(JLabel.CENTER);
        endGameMessageLabel.setBackground(Color.gray);
        gridBagLayout().setConstraints(endGameMessageLabel, constraints);
        this.add(endGameMessageLabel);

        constraints.gridy = 1;
        JButton newGameButton = new JButton("New Game");
        gridBagLayout().setConstraints(newGameButton, constraints);
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.updatePanels();
            }
        });
        this.add(newGameButton);

        constraints.gridy = 2;
        JButton exitGameButton = new JButton("Exit Game");
        gridBagLayout().setConstraints(exitGameButton, constraints);
        exitGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(exitGameButton);

        this.validate();
        this.repaint();
    }

    private GridBagLayout gridBagLayout() {
        return (GridBagLayout) this.getLayout();
    }
}
