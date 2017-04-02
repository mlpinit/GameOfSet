package com.mlpinit.set;

public class DeckCreator {

    public static Deck generate() {
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

}
