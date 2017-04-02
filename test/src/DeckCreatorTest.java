import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import com.mlpinit.set.DeckCreator;
import com.mlpinit.set.Deck;
import com.mlpinit.set.Card;

import java.util.HashSet;
import java.util.Set;

public class DeckCreatorTest {

    @Test
    public void test_generate() {
        Deck deck = new DeckCreator().generate();
        assertEquals(81, deck.size());
    }

    @Test
    public void test_generates_unique_cards() {
        Deck deck = new DeckCreator().generate();
        Set<Card> set = new HashSet<Card>();
        for (Card card = deck.nextCard(); card != null; card = deck.nextCard()) {
            set.add(card);
        }
        assertEquals(set.size(), deck.size());
    }
}
