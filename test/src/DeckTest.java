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

    @Test
    public void test_incomplete_set() {
        Deck deck = Deck.create();
        deck.populateFlippedCards();
        Card card1 = deck.getFlippedCards().get(0);
        Card card2 = deck.getFlippedCards().get(1);
        Card card3 = deck.getFlippedCards().get(2);
        assertTrue(deck.incompleteSet());
        card1.toggleSelection();
        assertTrue(deck.incompleteSet());
        card2.toggleSelection();
        assertTrue(deck.incompleteSet());
        card3.toggleSelection();
        assertFalse(deck.incompleteSet());
    }

    @Test
    public void test_card_flipping_not_allowed() {
        Deck deck = Deck.create();
        deck.populateFlippedCards();
        assertFalse(deck.cardFlippingNotAllowed());
        deck.flipThreeMoreCards();
        assertTrue(deck.cardFlippingNotAllowed());
    }

    @Test
    public void test_flip_three_more_cards() {
        Deck deck = Deck.create();
        assertEquals(0, deck.getFlippedCards().size());
        deck.flipThreeMoreCards();
        assertEquals(3, deck.getFlippedCards().size());
    }

    @Test
    public void test_flip_cards_with_12_cards_and_more_cards_left() {
        Deck deck = Deck.create();
        deck.populateFlippedCards();
        ArrayList<Card> flippedCards = new ArrayList<Card>();
        for (Card card : deck.getFlippedCards()) flippedCards.add(card);
        for (int i = 0; i < 3; i++) flippedCards.get(i).toggleSelection();
        deck.flipCards();
        assertTrue(deck.incompleteSet()); // test selected was cleared
        assertEquals(1, deck.getSetsCount()); // test sets count was increased
        // test that only three cards were replaced
        int diff = 0;
        ArrayList<Card> flippedCardsTwo = deck.getFlippedCards();
        for (int i = 0; i < 12; i++) {
            Card one = flippedCards.get(i);
            Card two = flippedCardsTwo.get(i);
            if (!one.equals(two)) diff++;
        }
        assertEquals(3, diff);
    }

   @Test
   public void test_flip_cards_with_15() {
       Deck deck = Deck.create();
       deck.populateFlippedCards();
       deck.flipThreeMoreCards();
       ArrayList<Card> flippedCards = deck.getFlippedCards();
       for (int i = 0; i < 3; i++) flippedCards.get(i).toggleSelection();
       assertEquals(15, deck.getFlippedCards().size());
       deck.flipCards();
       assertTrue(deck.incompleteSet()); // test selected was cleared
       assertEquals(1, deck.getSetsCount()); // test sets count was increased
       assertEquals(12, deck.getFlippedCards().size());
   }
}
