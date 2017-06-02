import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

import com.mlpinit.set.Deck;
import com.mlpinit.set.Card;
import com.mlpinit.set.Color;
import com.mlpinit.set.Filling;
import com.mlpinit.set.Shape;
import com.mlpinit.set.SetFinder;

public class SetFinderTest {
    private final Deck deck = new Deck();

    @Test
    public void test_find_two_sets() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Color.Red, Shape.Oval, Filling.Empty, 1, deck));
        cards.add(new Card(Color.Red, Shape.Oval, Filling.Empty, 2, deck));
        cards.add(new Card(Color.Red, Shape.Oval, Filling.Empty, 3, deck));
        cards.add(new Card(Color.Red, Shape.Squiggle, Filling.Empty, 1, deck));
        cards.add(new Card(Color.Red, Shape.Diamond, Filling.Empty, 1, deck));
        SetFinder setFinder = new SetFinder(cards);

        assertEquals(2, setFinder.possibleSetsSize());
    }

}

