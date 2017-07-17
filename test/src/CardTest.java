import static org.junit.Assert.*;
import org.junit.Test;

import com.mlpinit.set.Card;
import com.mlpinit.set.Color;
import com.mlpinit.set.Filling;
import com.mlpinit.set.Shape;

public class CardTest {

    private Card card;

    public CardTest() {
        card = new Card(Color.Red, Shape.Oval, Filling.Empty, 2);
    }

    @Test
    public void test_numerical_value() {
        assertEquals(1312, card.numValue()); 
    }

    @Test
    public void test_dark_image_location() {
        assertEquals("/ROE2D.png", card.getDarkImageLocation());
    }

    @Test
    public void test_light_image_location() {
        assertEquals("/ROE2L.png", card.getLightImageLocation());
    }

    @Test
    public void test_unslecte() {
        card = new Card(Color.Red, Shape.Oval, Filling.Empty, 2);
        card.toggleSelection();
        assertTrue(card.getSelected());
        card.unselect();
        assertFalse(card.getSelected());
    }

    @Test
    public void test_count_whitelist() {
        // throw error if values not within 1..3
    }
}
