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
        assertTrue(deck.isValid(one, two, three));
    }

    @Test
    public void test_is_valid_all_different_shape() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card four = new Card(Color.Red, Shape.Squiggle, Filling.Empty, 1);
        Card five = new Card(Color.Red, Shape.Diamond, Filling.Empty, 1);
        Deck deck = new Deck();
        assertTrue(deck.isValid(one, four, five));
    }

    @Test
    public void test_is_valid_all_different_color() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card six = new Card(Color.Green, Shape.Oval, Filling.Empty, 1);
        Card seven = new Card(Color.Mauve, Shape.Oval, Filling.Empty, 1);
        Deck deck = new Deck();
        assertTrue(deck.isValid(one, six, seven));
    }

    @Test
    public void test_is_valid_all_different_filling() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card eight = new Card(Color.Red, Shape.Oval, Filling.Striped, 1);
        Card nine = new Card(Color.Red, Shape.Oval, Filling.Full, 1);
        Deck deck = new Deck();
        assertTrue(deck.isValid(one, eight, nine));
    }

    @Test
    public void test_is_not_valid_count() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card two = new Card(Color.Red, Shape.Oval, Filling.Empty, 2);
        Card three = new Card(Color.Red, Shape.Oval, Filling.Empty, 2);
        Deck deck = new Deck();
        assertFalse(deck.isValid(one, two, three));
    }

    @Test
    public void test_is_not_valid_shape() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card four = new Card(Color.Red, Shape.Diamond, Filling.Empty, 1);
        Card five = new Card(Color.Red, Shape.Diamond, Filling.Empty, 1);
        Deck deck = new Deck();
        assertFalse(deck.isValid(one, four, five));
    }

    @Test
    public void test_is_not_valid_color() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card six = new Card(Color.Green, Shape.Oval, Filling.Empty, 1);
        Card seven = new Card(Color.Green, Shape.Oval, Filling.Empty, 1);
        Deck deck = new Deck();
        assertFalse(deck.isValid(one, six, seven));
    }

    @Test
    public void test_is_not_valid_filling() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card eight = new Card(Color.Red, Shape.Oval, Filling.Full, 1);
        Card nine = new Card(Color.Red, Shape.Oval, Filling.Full, 1);
        Deck deck = new Deck();
        assertFalse(deck.isValid(one, eight, nine));
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
        Deck deck = new Deck();
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card two = new Card(Color.Red, Shape.Oval, Filling.Empty, 2);
        Card three = new Card(Color.Red, Shape.Oval, Filling.Empty, 3);
        Card four = new Card(Color.Red, Shape.Oval, Filling.Striped, 2);
        Card five = new Card(Color.Red, Shape.Oval, Filling.Full, 2);
        ArrayList<Card> cards = deck.getFlippedCards();
        cards.add(one);
        cards.add(two);
        cards.add(three);
        cards.add(four);
        cards.add(five);
        assertEquals(2, deck.getPossibleSetsCount());
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
    public void test_game_ended() {
        Deck deck = Deck.create();
        assertFalse(deck.gameEnded());
        while (deck.nextCard() != null);
        assertTrue(deck.gameEnded());
    }

    @Test
    public void test_is_selected() {
        Deck deck = Deck.create();
        Card card = deck.nextCard();
        assertFalse(deck.isSelected(card));
        deck.updateSelectionStatus(card);
        assertTrue(deck.isSelected(card));

    }

    @Test
    public void test_incomplete_set() {
        Deck deck = Deck.create();
        Card card1 = deck.nextCard();
        Card card2 = deck.nextCard();
        Card card3 = deck.nextCard();
        assertTrue(deck.incompleteSet());
        deck.updateSelectionStatus(card1);
        assertTrue(deck.incompleteSet());
        deck.updateSelectionStatus(card2);
        assertTrue(deck.incompleteSet());
        deck.updateSelectionStatus(card3);
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
        for (int i = 0; i < 3; i++) deck.updateSelectionStatus(flippedCards.get(i));
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
       for (int i = 0; i < 3; i++) deck.updateSelectionStatus(flippedCards.get(i));
       assertEquals(15, deck.getFlippedCards().size());
       deck.flipCards();
       assertTrue(deck.incompleteSet()); // test selected was cleared
       assertEquals(1, deck.getSetsCount()); // test sets count was increased
       assertEquals(12, deck.getFlippedCards().size());
   }

}
