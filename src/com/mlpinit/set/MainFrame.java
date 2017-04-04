package com.mlpinit.set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import java.util.ArrayList;

public class MainFrame extends JFrame {

    private final int START_CARDS = 12;
    private final CardsPanel cardsPanel;
    private final InfoPanel infoPanel;

    private final Deck deck;
    private int setsCount = 0;
    private ArrayList<Card> displayedCards = new ArrayList<Card>(16);
    public ArrayList<Card> selectedCards = new ArrayList<Card>(3);

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
    }

    public void start() {
        for (int i = 0; i < START_CARDS; i++) displayedCards.add(deck.nextCard());
        cardsPanel.createDisplay(displayedCards);
        infoPanel.updatePossibleSetsLabel(Deck.possibleSets(displayedCards));
    }


    public void nextThreeCards() {
        if (Deck.isValid(selectedCards)) {
            setsCount++;
            refreshCardPanel();
            if (Deck.possibleSets(displayedCards) == 0 && !deck.hasMoreCards()) {
                JOptionPane.showMessageDialog(this,
                        "Congratulations! You have finished the game. You found " +
                        setsCount + " sets.");
            }
            infoPanel.updatePossibleSetsLabel(Deck.possibleSets(displayedCards));
            infoPanel.updateSetsCount(setsCount);
        } else {
            for (Card card : selectedCards) card.toggleSelected();
            JOptionPane.showMessageDialog(this, "That is not a valid set.");
        }
        cardsPanel.createDisplay(displayedCards);
        selectedCards.clear();
    }

    public void addThreeMoreCards() {
        if (displayedCards.size() == 12 && deck.hasMoreCards()) {
            for (int i = 0; i < 3; i++) displayedCards.add(deck.nextCard());
            cardsPanel.createDisplay(displayedCards);
            cardsPanel.validate();
            infoPanel.updatePossibleSetsLabel(Deck.possibleSets(displayedCards));
        } else if (!deck.hasMoreCards()) {
            JOptionPane.showMessageDialog(this, "No cards left.");
        } else {
            JOptionPane.showMessageDialog(this, "Maximum 15 cards at a time.");
        }
    }

    public static void main(String[] args) {
        Deck deck = Deck.create();
        deck.shuffle();
        MainFrame mainFrame = new MainFrame(deck);
        mainFrame.start();
    }

    private void refreshCardPanel() {
        if (displayedCards.size() == 12 && deck.hasMoreCards()) {
            for (Card card : selectedCards) {
                int cardIndex = displayedCards.indexOf(card);
                Card nextCard = deck.nextCard();
                displayedCards.set(cardIndex, nextCard);
            }
        } else {
            for (Card card : selectedCards) {
                displayedCards.remove(card);
            }
        }
    }

}
