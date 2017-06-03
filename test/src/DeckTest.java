import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import com.mlpinit.set.Deck;
import com.mlpinit.set.Card;
import com.mlpinit.set.Color;
import com.mlpinit.set.Filling;
import com.mlpinit.set.Shape;

import java.util.HashSet;
import java.util.Set;

public class DeckTest {

    @Test
    public void test_add_card() {
        Deck deck = new Deck();
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1, deck);
        Card eight = new Card(Color.Red, Shape.Oval, Filling.Full, 1, deck);
        Card nine = new Card(Color.Red, Shape.Oval, Filling.Full, 1, deck);
        deck.add(one);
        assertEquals(1, deck.size());
        deck.add(eight);
        assertEquals(2, deck.size());
        deck.add(nine);
        assertEquals(3, deck.size());
    }

    @Test
    public void test_shuffle() {
        Deck deck = new Deck();
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1, deck);
        Card eight = new Card(Color.Red, Shape.Oval, Filling.Full, 1, deck);
        Card nine = new Card(Color.Red, Shape.Oval, Filling.Full, 1, deck);
        deck.add(one);
        deck.add(eight);
        deck.add(nine);
        String initialDeck = deck.toString();
        deck.shuffle();
        String shuffledDeck = deck.toString();
        assertThat(initialDeck, not(equalTo(shuffledDeck)));
    }

    @Test
    public void test_next_card() {
        Deck deck = new Deck();
        assertEquals(null, deck.nextCard());
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1, deck);
        deck.add(one);
        assertEquals(one, deck.nextCard());
    }

    @Test
    public void test_cards_are_unique_after_shuffle() {
        Deck deck = Deck.create();
        deck.shuffle();
        Set<Card> set = new HashSet<Card>();
        for (Card card = deck.nextCard(); card != null; card = deck.nextCard()) {
            set.add(card);
        }
        assertEquals(set.size(), deck.size());
    }

    @Test
    public void test_has_more_cards() {
        Deck deck = Deck.create();
        assertTrue(deck.hasMoreCards());
    }

    @Test
    public void test_does_not_have_more_cards() {
        Deck deck = Deck.create();
        for (int i = 0; i < 81; i++) deck.nextCard();
        assertFalse(deck.hasMoreCards());
    }

    @Test
    public void test_create() {
        Deck deck = Deck.create();
        assertEquals(81, deck.size());
    }

    @Test
    public void test_create_unique_cards() {
        Deck deck = Deck.create();
        Set<Card> set = new HashSet<Card>();
        for (Card card = deck.nextCard(); card != null; card = deck.nextCard()) {
            set.add(card);
        }
        assertEquals(set.size(), deck.size());
    }

}
