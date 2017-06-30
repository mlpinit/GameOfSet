import static org.junit.Assert.*;
import org.junit.Test;

import com.mlpinit.set.SetBoard;
import com.mlpinit.set.Deck;
import com.mlpinit.set.Card;

import java.util.ArrayList;

public class SetBoardTest {
    @Test
    public void test_incomplete_set() {
        Deck deck = Deck.create();
        SetBoard setBoard = new SetBoard(deck);
        Card card1 = setBoard.getFlippedCards().get(0);
        Card card2 = setBoard.getFlippedCards().get(1);
        Card card3 = setBoard.getFlippedCards().get(2);
        assertTrue(setBoard.isSetIncomplete());
        card1.toggleSelection();
        assertTrue(setBoard.isSetIncomplete());
        card2.toggleSelection();
        assertTrue(setBoard.isSetIncomplete());
        card3.toggleSelection();
        assertFalse(setBoard.isSetIncomplete());
    }

    @Test
    public void test_card_flipping_not_allowed() {
        Deck deck = Deck.create();
        SetBoard setBoard = new SetBoard(deck);
        assertFalse(setBoard.cardFlippingNotAllowed());
        setBoard.flipThreeMoreCards();
        assertFalse(setBoard.cardFlippingNotAllowed());
        setBoard.flipThreeMoreCards();
        assertFalse(setBoard.cardFlippingNotAllowed());
        setBoard.flipThreeMoreCards();
        assertTrue(setBoard.cardFlippingNotAllowed());
    }

    @Test
    public void test_flip_three_more_cards() {
        Deck deck = Deck.create();
        SetBoard setBoard = new SetBoard(deck);
        assertEquals(12, setBoard.getFlippedCards().size());
        setBoard.flipThreeMoreCards();
        assertEquals(15, setBoard.getFlippedCards().size());
    }

    @Test
    public void test_flip_cards_with_12_cards_and_more_cards_left() {
        Deck deck = Deck.create();
        SetBoard setBoard = new SetBoard(deck);
        ArrayList<Card> flippedCards = new ArrayList<Card>();
        for (Card card : setBoard.getFlippedCards()) flippedCards.add(card);
        for (int i = 0; i < 3; i++) flippedCards.get(i).toggleSelection();
        setBoard.flipCards();
        assertTrue(setBoard.isSetIncomplete()); // test selected was cleared
        assertEquals(1, setBoard.getSetsCount()); // test sets count was increased
        // test that only three cards were replaced
        int diff = 0;
        ArrayList<Card> flippedCardsTwo = setBoard.getFlippedCards();
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
        SetBoard setBoard = new SetBoard(deck);
        setBoard.flipThreeMoreCards();
        ArrayList<Card> flippedCards = setBoard.getFlippedCards();
        for (int i = 0; i < 3; i++) flippedCards.get(i).toggleSelection();
        assertEquals(15, setBoard.getFlippedCards().size());
        setBoard.flipCards();
        assertTrue(setBoard.isSetIncomplete()); // test selected was cleared
        assertEquals(1, setBoard.getSetsCount()); // test sets count was increased
        assertEquals(12, setBoard.getFlippedCards().size());
   }
}
