package com.mlpinit.set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import java.util.ArrayList;

public class MainFrame extends JFrame {

    private final String endGameMessage = "Congratulations! You have finished the game!";
    private final String invalidSetWarning = "That is not a valid set.";
    private final CardsPanel cardsPanel;
    private final InfoPanel infoPanel;

    public final Deck deck;

    public MainFrame(Deck deck) {
        super("Game of Set");
        this.cardsPanel = new CardsPanel(this);
        this.infoPanel = new InfoPanel(this);
        this.setLayout(new BorderLayout());
        this.deck = deck;
        this.setSize(600, 720);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(cardsPanel, BorderLayout.CENTER);
        this.add(infoPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        deck.populateFlippedCards();
        cardsPanel.updateDisplay(deck.getFlippedCards());
        infoPanel.updatePossibleSetsLabel(deck.getPossibleSetsCount());
    }

    public void nextThreeCards() {
        // only attempt to flip cards when a set is selected
        if (deck.incompleteSet()) return;
        if (deck.isValid()) {
            deck.flipCards();
            infoPanel.updatePossibleSetsLabel(deck.getPossibleSetsCount());
            infoPanel.updateSetsCount(deck.getSetsCount());
            if (deck.gameEnded()) {
                JOptionPane.showMessageDialog(this, endGameMessage);
            }
        } else {
            JOptionPane.showMessageDialog(this, invalidSetWarning);
            deck.clearSelected();
        }
        cardsPanel.updateDisplay(deck.getFlippedCards());
    }

    public void flipThreeMoreCards() {
        if (!deck.hasMoreCards()) {
            JOptionPane.showMessageDialog(this, "No cards left.");
        } else if (deck.cardFlippingNotAllowed()) {
            JOptionPane.showMessageDialog(this, "Maximum 15 cards at a time.");
        } else {
            deck.flipThreeMoreCards();
            cardsPanel.updateDisplay(deck.getFlippedCards());
            infoPanel.updatePossibleSetsLabel(deck.getPossibleSetsCount());
        }
    }

    public static void main(String[] args) {
        Deck deck = Deck.create();
        deck.shuffle();
        new MainFrame(deck);
    }
}
