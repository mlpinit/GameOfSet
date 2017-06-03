package com.mlpinit.set;

import java.util.ArrayList;

public class SetBoard {
    private final int START_CARDS = 12; // 12 cards flipped in the first round.

    private int setsCount;
    private Deck deck;

    private ArrayList<Card> flippedCards = new ArrayList<Card>();

    public SetBoard(Deck deck) {
        this.deck = deck;
        this.setsCount = 0;
        populateFlippedCards();
    }

    public void populateFlippedCards() {
        for (int i = 0; i < START_CARDS; i++) flippedCards.add(deck.nextCard());
    }

    public ArrayList<Card> getSelectedCards() {
        ArrayList<Card> cards = new ArrayList<>();
        for (Card card : flippedCards) {
            if (card.getSelected()) {
                cards.add(card);
            }
        }
        return cards;
    }

    public void flipThreeMoreCards() {
        for (int i = 0; i < 3; i++) flippedCards.add(deck.nextCard());
    }

    public boolean isSetIncomplete() {
        return getSelectedCards().size() != 3;
    }

    public void clearSelectedCards() {
        for (Card card : getSelectedCards()) card.toggleSelection();
    }

    public int getSetsCount() {
        return setsCount;
    }

    public void flipCards() {
        setsCount++;
        if (flippedCards.size() == 12 && deck.hasMoreCards()) {
            for (Card card : getSelectedCards()) {
                int cardIndex = flippedCards.indexOf(card);
                flippedCards.set(cardIndex, deck.nextCard());
            }
        } else {
            for (Card card : getSelectedCards()) {
                flippedCards.remove(card);
            }
        }
    }

    public ArrayList<Card> getFlippedCards() {
        return flippedCards;
    }

    public boolean cardFlippingNotAllowed() {
        return flippedCards.size() == 15;
    }
}
