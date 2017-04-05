package com.mlpinit.set;

import java.util.Arrays;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> flippedCards = new ArrayList<Card>(16);
    private ArrayList<Card> selectedCards = new ArrayList<Card>(3);

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
    }

    public void add(Card card) {
        cards[size++] = card;
    }

    public void clearSelected() {
        selectedCards.clear();
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
        int possibleSets = 0;
        for (int i = 0; i < flippedCards.size() - 2; i++) {
            for (int j = i + 1; j < flippedCards.size() - 1; j++) {
                for (int k = j + 1; k < flippedCards.size(); k++) {
                    if (isValid(flippedCards.get(i), flippedCards.get(j),
                                flippedCards.get(k))) {
                        possibleSets++;
                    }
                }
            }
        }
        return possibleSets;
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
            for (Card card : selectedCards) {
                int cardIndex = flippedCards.indexOf(card);
                flippedCards.set(cardIndex, nextCard());
            }
        } else {
            for (Card card : selectedCards) {
                flippedCards.remove(card);
            }
        }
        selectedCards.clear();
    }

    public void flipThreeMoreCards() {
        for (int i = 0; i < 3; i++) flippedCards.add(nextCard());
    }

    public void updateSelectionStatus(Card card) {
        if (selectedCards.contains(card)) {
            selectedCards.remove(card);
        } else {
            selectedCards.add(card);
        }
    }

    // This algorithm avoids the cumbersome use of multiple if statements. The
    // numValue() function returns a numerical representation of a card.  We
    // have a valid set only when each attribute of a card is the same on all of
    // the cards or different on all of the cards. For each attribute, the
    // summed numerical representation gives a digit divisible by 3 for a valid
    // set and not divisible by 3 for an invalid set.
    public boolean isValid(ArrayList<Card> cards) {
        int numValueSum = 0;
        for (Card card : cards) numValueSum += card.numValue(); 
        while (numValueSum != 0) {
            if ((numValueSum % 10) % 3 != 0) return false;
            numValueSum /= 10;
        }
        return true;
    }

    public boolean isValid(Card one, Card two, Card three) {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(one);
        cards.add(two);
        cards.add(three);
        return isValid(cards);
    }

    public boolean isValid() {
        return isValid(selectedCards);
    }

    public boolean gameEnded() {
        return getPossibleSetsCount() == 0 && !this.hasMoreCards();
    }

    public boolean cardFlippingNotAllowed() {
        return flippedCards.size() == 15;
    }

    public boolean isSelected(Card card) {
        return selectedCards.contains(card);
    }

    public boolean incompleteSet() {
        return selectedCards.size() != 3;
    }
}
