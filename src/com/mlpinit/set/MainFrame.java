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
    private final String invalidSetWarning = "That is not a valid set.";
    private final String maxFlippedCardsMessage = "Maximum " +
        SetBoard.MAX_CARDS + " cards at a time.";

    private final CardsPanel cardsPanel;
    private final InfoPanel infoPanel;

    private SetFinder setFinder;
    public SetBoard setBoard;

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
        this.setBoard = new SetBoard(deck);
        this.setFinder = new SetFinder(setBoard.getFlippedCards());

        if (noPossibleSets()) {
            flipThreeMoreCards();
        }
        updatePanels();
    }

    public void nextThreeCards() {
        if (validSet()) {
            setBoard.flipCards();
            setFinder.findPossibleSets(setBoard.getFlippedCards());
            if (isGameOver()) {
                endGame();
                return;
            } else {
                infoPanel.updatePossibleSetsLabel(setFinder.possibleSetsSize());
                infoPanel.updateSetsCount(setBoard.getSetsCount());
            }
        } else {
            JOptionPane.showMessageDialog(this, invalidSetWarning);
            setBoard.clearSelectedCards();
        }
        cardsPanel.updateDisplay(setBoard.getFlippedCards());
        this.setSize(frameWidth(), 780);
        if (noPossibleSets()) {
            flipThreeMoreCards();
        }
    }

    public void flipThreeMoreCards() {
        if (!deck.hasMoreCards()) {
            JOptionPane.showMessageDialog(this, "No cards left.");
        } else if (setBoard.cardFlippingNotAllowed()) {
            JOptionPane.showMessageDialog(this, maxFlippedCardsMessage);
        } else {
            setBoard.flipThreeMoreCards();
            setFinder.findPossibleSets(setBoard.getFlippedCards());
            cardsPanel.updateDisplay(setBoard.getFlippedCards());
            infoPanel.updatePossibleSetsLabel(setFinder.possibleSetsSize());
            this.setSize(frameWidth(), 780);
        }
        if (isGameOver()) {
            endGame();
        }
    }

    public void showHint() {
        if (setFinder.possibleSetsSize() == 0) {
            JOptionPane.showMessageDialog(this, "No sets available.");
        } else {
            setBoard.clearSelectedCards();
            ArrayList<Card> cards = setFinder.getRandomSet();
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

    private void updateHintDisplay(ArrayList<Card> cards) {
        for (Card card : cards) {
            card.toggleSelection();
            cardsPanel.updateDisplay(setBoard.getFlippedCards());
        }
    }

    public boolean isGameOver() {
        return !deck.hasMoreCards() && setFinder.possibleSetsSize() == 0;
    }

    public void updatePanels() {
        cardsPanel.updateDisplay(setBoard.getFlippedCards());
        infoPanel.displayRegularLayout();
        infoPanel.updatePossibleSetsLabel(setFinder.possibleSetsSize());
        infoPanel.updatePossibleSetsLabel(setFinder.possibleSetsSize());
        infoPanel.updateSetsCount(setBoard.getSetsCount());
    }

    private int frameWidth() {
        return Math.max(600, 600 + (setBoard.getFlippedCardsCount() - 12) / 3 * 150);
    }
    
    private boolean noPossibleSets() {
        return setFinder.possibleSetsSize() == 0;
    }

    private boolean validSet() {
        return SetValidator.isValid(setBoard.getSelectedCards());
    }

    private void endGame() {
        deck.restart();
        this.setBoard = new SetBoard(deck);
        this.setFinder = new SetFinder(setBoard.getFlippedCards());
        cardsPanel.displayEndGame();
        infoPanel.displayEndGameLayout();
    }

    public static void main(String[] args) {
        Deck deck = Deck.create();
        deck.shuffle();
        new MainFrame(deck);
    }
}
