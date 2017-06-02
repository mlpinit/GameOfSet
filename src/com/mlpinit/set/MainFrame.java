package com.mlpinit.set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import java.lang.Thread;

public class MainFrame extends JFrame {

    private final String endGameMessage = "Congratulations! You have finished the game!";
    private final String invalidSetWarning = "That is not a valid set.";
    private final CardsPanel cardsPanel;
    private final InfoPanel infoPanel;

    private Timer timer;

    public final Deck deck;

    public MainFrame(Deck deck) {
        super("Game of Set");
        this.cardsPanel = new CardsPanel(this);
        this.infoPanel = new InfoPanel(this);
        this.setLayout(new BorderLayout());
        this.deck = deck;
        this.setSize(600, 780);
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
        if (SetValidator.isValid(deck.getSelectedCards())) {
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

    public void showHint() {
        if (deck.getPossibleSetsCount() == 0) {
            JOptionPane.showMessageDialog(this, "No sets available.");
        } else {
            // deck.clearSelected();
            ArrayList<Card> cards = deck.getPossibleSet();
            ActionListener action = new ActionListener() {   
                @Override
                public void actionPerformed(ActionEvent event) {
                    timer.stop();
                    updateHintDisplay(cards);
                }
            };
            timer = new Timer(500, action);
            timer.setRepeats(false);
            timer.start();
            updateHintDisplay(cards);
        }
    }

    public static void main(String[] args) {
        Deck deck = Deck.create();
        deck.shuffle();
        new MainFrame(deck);
    }

    private void updateHintDisplay(ArrayList<Card> cards) {
        for (Card card : cards) {
            card.toggleSelection();
            cardsPanel.updateDisplay(deck.getFlippedCards());
        }
    }

}
