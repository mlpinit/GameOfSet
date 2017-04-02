package com.mlpinit.set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import java.util.ArrayList;

public class MainFrame extends JFrame {

    private final int START_CARDS = 12;
    private final CardsPanel cardsPanel = new CardsPanel();
    private final InfoPanel infoPanel = new InfoPanel();

    private final Deck deck;
    private int setsCount = 0;
    private ArrayList<Card> displayedCards = new ArrayList<Card>(16);
    public ArrayList<CardButton> selectedButtons = new ArrayList<CardButton>(3);

    public MainFrame(Deck deck) {
        super("Game of Set");
        this.setLayout(new BorderLayout());
        this.deck = deck;
        this.setSize(600, 720);
        this.setLocationRelativeTo(null);
        this.add(cardsPanel, BorderLayout.CENTER);
        this.add(infoPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void start() {
        for (int i = 0; i < START_CARDS; i++) displayedCards.add(deck.nextCard());
        cardsPanel.createDisplay(displayedCards);
        cardsPanel.validate();
        infoPanel.updatePossibleSetsLabel(deck.possibleSets(displayedCards));
    }


    public void nextThreeCards() {
        if (deck.isValid(selectedButtons.get(0).card,
            selectedButtons.get(1).card, selectedButtons.get(2).card)) {
            setsCount++;
            refreshCardPanel();
            if (deck.possibleSets(displayedCards) == 0) {
                JOptionPane.showMessageDialog(this,
                        "Congratulations! You have finished the game. You found " +
                        setsCount + " sets.");
            }
        } else {
            for (CardButton cardButton : selectedButtons) {
                cardButton.unselect();
                cardButton.updateIcon();
            }
            JOptionPane.showMessageDialog(this, "That is not a valid set.");
        }
        selectedButtons.clear();
        cardsPanel.validate();
        cardsPanel.repaint();
        infoPanel.updatePossibleSetsLabel(deck.possibleSets(displayedCards));
        infoPanel.updateSetsCount(setsCount);
    }

    public void addThreeMoreCards() {
        if (displayedCards.size() == 12 && deck.hasMoreCards()) {
            for (int i = 0; i < 3; i++) displayedCards.add(deck.nextCard());
            cardsPanel.createDisplay(displayedCards);
            cardsPanel.validate();
            infoPanel.updatePossibleSetsLabel(deck.possibleSets(displayedCards));
        } else if (!deck.hasMoreCards()) {
            JOptionPane.showMessageDialog(this, "No cards left.");
        } else {
            JOptionPane.showMessageDialog(this, "Maximum 15 cards at a time.");
        }
    }

    public static void main(String[] args) {
        Deck deck = DeckCreator.generate();
        deck.shuffle();
        MainFrame mainFrame = new MainFrame(deck);
        mainFrame.start();
    }

    private void refreshCardPanel() {
        if (displayedCards.size() == 12 && deck.hasMoreCards()) {
            for (CardButton cardButton : selectedButtons) {
                displayedCards.remove(cardButton.card);
                Card nextCard = deck.nextCard();
                displayedCards.add(nextCard);
                cardButton.card = nextCard;
                cardButton.unselect();
                cardButton.updateIcon();
            }
        } else {
            for (CardButton cardButton : selectedButtons) {
                displayedCards.remove(cardButton.card);
                cardsPanel.remove(cardButton);
            }
            cardsPanel.createDisplay(displayedCards);
        }
    }

}
