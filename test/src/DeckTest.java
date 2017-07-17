import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import com.mlpinit.set.Deck;
import com.mlpinit.set.Card;
import com.mlpinit.set.Color;
import com.mlpinit.set.Filling;
import com.mlpinit.set.Shape;
import com.mlpinit.set.Misc;

import java.util.HashSet;
import java.util.Set;

public class DeckTest {

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
        int size = 0;
        while (deck.nextCard() != null) {
            size++;
        }
        assertEquals(81, size);
    }

    @Test
    public void test_restart() {
        Deck deck = Deck.create();
        while (deck.hasMoreCards()) {
            Card card = deck.nextCard();
            if (Misc.randomIntInRange(0, 1) == 0) {
                card.toggleSelection();
            }
        }
        deck.restart();
        assertTrue(deck.hasMoreCards());
        int selectedCardsCount = 0;
        while (deck.hasMoreCards()) {
            Card card = deck.nextCard();
            if (card.getSelected()) {
                selectedCardsCount++;
            }
        }
        assertEquals(0, selectedCardsCount);
    }
}
