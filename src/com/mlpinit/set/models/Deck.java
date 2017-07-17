package com.mlpinit.set;

import java.util.Arrays;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> flippedCards = new ArrayList<Card>(16);

    private static final int MAX_CARDS = 81; // 81 cards in a game of set.

    private Card[] cards;
    private int size;
    private int index;

    public static Deck create() {
        Deck deck = new Deck();
        for (Color color : Color.values()) {
            for (Shape shape : Shape.values()) {
                for (Filling filling : Filling.values()) {
                    for (int count = 1; count <= 3; count++) {
                        deck.add(new Card(color, shape, filling, count));
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
    }

    public int size() {
        return size;
    }

    public void add(Card card) {
        cards[size++] = card;
    }

    public void shuffle() {
        for (int i = 0; i < cards.length - 1; i++) {
            Card one = cards[i];
            int positionTwo = Misc.randomIntInRange(i, cards.length - 1);
            Card two = cards[positionTwo];
            cards[i] = two;
            cards[positionTwo] = one;
        }
    }

    public void restart() {
        shuffle();
        for (Card card : cards) {
            card.unselect();
        }
        this.index = 0;
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
}
