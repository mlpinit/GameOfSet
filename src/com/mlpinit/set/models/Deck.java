package com.mlpinit.set;

import java.util.Arrays;
import java.util.ArrayList;

public class Deck {

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

    // This algorithm avoids the cumbersome use of multiple if statements. The
    // numValue() function returns a numerical representation of a card.  We
    // have a valid set only when each attribute of a card is the same on all of
    // the cards or different on all of the cards. For each attribute, the
    // summed numerical representation gives a digit divisible by 3 for a valid
    // set and not divisible by 3 for an invalid set.
    public static boolean isValid(ArrayList<Card> cards) {
        int numValueSum = 0;
        for (Card card : cards) numValueSum += card.numValue(); 
        while (numValueSum != 0) {
            if ((numValueSum % 10) % 3 != 0) return false;
            numValueSum /= 10;
        }
        return true;
    }

    public static boolean isValid(Card one, Card two, Card three) {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(one);
        cards.add(two);
        cards.add(three);
        return isValid(cards);
    }

    public static int possibleSets(ArrayList<Card> cards) {
        int possibleSets = 0;
        for (int i = 0; i < cards.size() - 2; i++) {
            for (int j = i + 1; j < cards.size() - 1; j++) {
                for (int k = j + 1; k < cards.size(); k++) {
                    if (Deck.isValid(cards.get(i), cards.get(j), cards.get(k))) {
                        possibleSets++;
                    }
                }
            }
        }
        return possibleSets;
    }

    // There are a total of 81 cards in a game of set.
    private static final int MAX_CARDS = 81;

    private Card[] cards;
    private int size;
    private int index;

    public Deck() {
        this.cards = new Card[MAX_CARDS];
        this.index = 0;
        this.size = 0;
    }

    public void add(Card card) {
        cards[size++] = card;
    }

    public int size() {
        return size;
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
