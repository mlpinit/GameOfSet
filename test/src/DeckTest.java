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
import java.util.ArrayList;

public class DeckTest {

    @Test
    public void test_is_valid_all_different_count() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card two = new Card(Color.Red, Shape.Oval, Filling.Empty, 2);
        Card three = new Card(Color.Red, Shape.Oval, Filling.Empty, 3);
        Deck deck = new Deck();

        assertTrue(Deck.isValid(one, two, three));
    }

    @Test
    public void test_is_valid_all_different_shape() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card four = new Card(Color.Red, Shape.Squiggle, Filling.Empty, 1);
        Card five = new Card(Color.Red, Shape.Diamond, Filling.Empty, 1);
        Deck deck = new Deck();

        assertTrue(Deck.isValid(one, four, five));
    }

    @Test
    public void test_is_valid_all_different_color() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card six = new Card(Color.Green, Shape.Oval, Filling.Empty, 1);
        Card seven = new Card(Color.Mauve, Shape.Oval, Filling.Empty, 1);
        Deck deck = new Deck();

        assertTrue(Deck.isValid(one, six, seven));
    }

    @Test
    public void test_is_valid_all_different_filling() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card eight = new Card(Color.Red, Shape.Oval, Filling.Striped, 1);
        Card nine = new Card(Color.Red, Shape.Oval, Filling.Full, 1);
        Deck deck = new Deck();

        assertTrue(Deck.isValid(one, eight, nine));
    }

    @Test
    public void test_is_not_valid_count() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card two = new Card(Color.Red, Shape.Oval, Filling.Empty, 2);
        Card three = new Card(Color.Red, Shape.Oval, Filling.Empty, 2);
        Deck deck = new Deck();

        assertFalse(Deck.isValid(one, two, three));
    }

    @Test
    public void test_is_not_valid_shape() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card four = new Card(Color.Red, Shape.Diamond, Filling.Empty, 1);
        Card five = new Card(Color.Red, Shape.Diamond, Filling.Empty, 1);
        Deck deck = new Deck();

        assertFalse(Deck.isValid(one, four, five));
    }

    @Test
    public void test_is_not_valid_color() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card six = new Card(Color.Green, Shape.Oval, Filling.Empty, 1);
        Card seven = new Card(Color.Green, Shape.Oval, Filling.Empty, 1);
        Deck deck = new Deck();

        assertFalse(Deck.isValid(one, six, seven));
    }

    @Test
    public void test_is_not_valid_filling() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card eight = new Card(Color.Red, Shape.Oval, Filling.Full, 1);
        Card nine = new Card(Color.Red, Shape.Oval, Filling.Full, 1);
        Deck deck = new Deck();

        assertFalse(Deck.isValid(one, eight, nine));
    }

    @Test

    public void test_add_card() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card eight = new Card(Color.Red, Shape.Oval, Filling.Full, 1);
        Card nine = new Card(Color.Red, Shape.Oval, Filling.Full, 1);
        Deck deck = new Deck();

        deck.add(one);
        assertEquals(1, deck.size());

        deck.add(eight);
        assertEquals(2, deck.size());

        deck.add(nine);
        assertEquals(3, deck.size());
    }

    @Test
    public void test_shuffle() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card eight = new Card(Color.Red, Shape.Oval, Filling.Full, 1);
        Card nine = new Card(Color.Red, Shape.Oval, Filling.Full, 1);
        Deck deck = new Deck();

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

        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
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
    public void test_possible_sets() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card two = new Card(Color.Red, Shape.Oval, Filling.Empty, 2);
        Card three = new Card(Color.Red, Shape.Oval, Filling.Empty, 3);
        Card four = new Card(Color.Red, Shape.Oval, Filling.Striped, 2);
        Card five = new Card(Color.Red, Shape.Oval, Filling.Full, 2);
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(one);
        cards.add(two);
        cards.add(three);
        cards.add(four);
        cards.add(five);
        Deck deck = new Deck();

        assertEquals(2, Deck.possibleSets(cards));
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
