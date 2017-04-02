package com.mlpinit.set;

import java.util.Arrays;
import java.util.ArrayList;

public class Deck {

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

    // This algorithm avoids the cumbersome use of multiple if statements. The
    // numValue() function returns a numerical representation of a card.  We
    // have a valid set only when each attribute of a card is the same on all of
    // the cards or different on all of the cards. For each attribute, the
    // summed numerical representation gives a digit divisible by 3 for a valid
    // set and not divisible by 3 for an invalid set.
    public boolean isValid(Card one, Card two, Card three) {
        int numValueSum = one.numValue() + two.numValue() + three.numValue();
        while (numValueSum != 0) {
            if ((numValueSum % 10) % 3 != 0) return false;
            numValueSum /= 10;
        }
        return true;
    }

    public int possibleSets(ArrayList<Card> cards) {
        int possibleSets = 0;
        for (int i = 0; i < cards.size() - 2; i++) {
            for (int j = i + 1; j < cards.size() - 1; j++) {
                for (int k = j + 1; k < cards.size(); k++) {
                    if (isValid(cards.get(i), cards.get(j), cards.get(k))) {
                        possibleSets++;
                    }
                }
            }
        }
        return possibleSets;
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
