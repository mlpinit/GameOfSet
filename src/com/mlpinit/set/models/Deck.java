package com.mlpinit.set;

import java.util.Arrays;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> flippedCards = new ArrayList<Card>(16);
    private ArrayList<ArrayList<Card>> possibleSets = new ArrayList<ArrayList<Card>>();

    private static final int MAX_CARDS = 81; // 81 cards in a game of set.
    private final int START_CARDS = 12; // 12 cards flipped in the first round.

    private Card[] cards;
    private int size;
    private int index;
    private int setsCount;

    public static Deck create() {
        Deck deck = new Deck();
        for (Color color : Color.values()) {
            for (Shape shape : Shape.values()) {
                for (Filling filling : Filling.values()) {
                    for (int count = 1; count <= 3; count++) {
                        deck.add(new Card(color, shape, filling, count, deck));
                    }
                }
            }
        }
        return deck;
    }

    public Deck() {
        this.cards = new Card[MAX_CARDS];
        this.index = 0;
        this.size = 0;
        this.setsCount = 0;
    }

    public int size() {
        return size;
    }

    public int getSetsCount() {
        return setsCount;
    }

    public void populateFlippedCards() {
        for (int i = 0; i < START_CARDS; i++) flippedCards.add(nextCard());
        identifyPossibleSets();
    }

    public void add(Card card) {
        cards[size++] = card;
    }

    public void clearSelected() {
        for (Card card : getSelectedCards()) card.toggleSelection();
    }

    public void shuffle() {
        for (int i = 0; i < cards.length - 1; i++) {
            Card one = cards[i];
            int positionTwo = Misc.randomIntInRange(0, cards.length - 1);
            Card two = cards[positionTwo];
            cards[i] = two;
            cards[positionTwo] = one;
        }
    }

    public int getPossibleSetsCount() {
        return possibleSets.size();
    }

    public ArrayList<Card> getPossibleSet() {
        int randomSetIndex = Misc.randomIntInRange(0, possibleSets.size() - 1);
        return possibleSets.get(randomSetIndex);
    }

    public Card nextCard() {
        if (index != size) return cards[index++];
        else return null;
    }

    public boolean hasMoreCards() {
        return index < MAX_CARDS;
    }

    public String toString() {
        return Arrays.toString(cards);
    }

    public ArrayList<Card> getFlippedCards() {
        return flippedCards;
    }

    public void flipCards() {
        setsCount++;
        if (flippedCards.size() == 12 && this.hasMoreCards()) {
            for (Card card : getSelectedCards()) {
                int cardIndex = flippedCards.indexOf(card);
                flippedCards.set(cardIndex, nextCard());
            }
        } else {
            for (Card card : getSelectedCards()) {
                flippedCards.remove(card);
            }
        }
        identifyPossibleSets();
    }

    public void flipThreeMoreCards() {
        for (int i = 0; i < 3; i++) flippedCards.add(nextCard());
        identifyPossibleSets();
    }

    public boolean gameEnded() {
        return getPossibleSetsCount() == 0 && !this.hasMoreCards();
    }

    public boolean cardFlippingNotAllowed() {
        return flippedCards.size() == 15;
    }

    public boolean incompleteSet() {
        return getSelectedCards().size() != 3;
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

    private void identifyPossibleSets() {
        possibleSets.clear();
        for (int i = 0; i < flippedCards.size() - 2; i++) {
            for (int j = i + 1; j < flippedCards.size() - 1; j++) {
                for (int k = j + 1; k < flippedCards.size(); k++) {
                    if (SetValidator.isValid(flippedCards.get(i), flippedCards.get(j), flippedCards.get(k))) {
                        ArrayList<Card> set = new ArrayList<Card>(3);
                        set.add(flippedCards.get(i));
                        set.add(flippedCards.get(j));
                        set.add(flippedCards.get(k));
                        possibleSets.add(set);
                    }
                }
            }
        }
    }
}
