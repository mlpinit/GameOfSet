import static org.junit.Assert.*;
import org.junit.Test;

import com.mlpinit.set.Deck;
import com.mlpinit.set.Card;
import com.mlpinit.set.Color;
import com.mlpinit.set.Filling;
import com.mlpinit.set.Shape;
import com.mlpinit.set.SetValidator;

public class SetValidatorTest {
    private final Deck deck = new Deck();

    @Test
    public void test_is_valid_all_different_count() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1, deck);
        Card two = new Card(Color.Red, Shape.Oval, Filling.Empty, 2, deck);
        Card three = new Card(Color.Red, Shape.Oval, Filling.Empty, 3, deck);
        assertTrue(SetValidator.isValid(one, two, three));
    }

    @Test
    public void test_is_valid_all_different_shape() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1, deck);
        Card four = new Card(Color.Red, Shape.Squiggle, Filling.Empty, 1, deck);
        Card five = new Card(Color.Red, Shape.Diamond, Filling.Empty, 1, deck);
        assertTrue(SetValidator.isValid(one, four, five));
    }

    @Test
    public void test_is_valid_all_different_color() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1, deck);
        Card six = new Card(Color.Green, Shape.Oval, Filling.Empty, 1, deck);
        Card seven = new Card(Color.Mauve, Shape.Oval, Filling.Empty, 1, deck);
        assertTrue(SetValidator.isValid(one, six, seven));
    }

    @Test
    public void test_is_valid_all_different_filling() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1, deck);
        Card eight = new Card(Color.Red, Shape.Oval, Filling.Striped, 1, deck);
        Card nine = new Card(Color.Red, Shape.Oval, Filling.Full, 1, deck);
        assertTrue(SetValidator.isValid(one, eight, nine));
    }

    @Test
    public void test_is_not_valid_count() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1, deck);
        Card two = new Card(Color.Red, Shape.Oval, Filling.Empty, 2, deck);
        Card three = new Card(Color.Red, Shape.Oval, Filling.Empty, 2, deck);
        assertFalse(SetValidator.isValid(one, two, three));
    }

    @Test
    public void test_is_not_valid_shape() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1, deck);
        Card four = new Card(Color.Red, Shape.Diamond, Filling.Empty, 1, deck);
        Card five = new Card(Color.Red, Shape.Diamond, Filling.Empty, 1, deck);
        assertFalse(SetValidator.isValid(one, four, five));
    }

    @Test
    public void test_is_not_valid_color() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1, deck);
        Card six = new Card(Color.Green, Shape.Oval, Filling.Empty, 1, deck);
        Card seven = new Card(Color.Green, Shape.Oval, Filling.Empty, 1, deck);
        assertFalse(SetValidator.isValid(one, six, seven));
    }

    @Test
    public void test_is_not_valid_filling() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1, deck);
        Card eight = new Card(Color.Red, Shape.Oval, Filling.Full, 1, deck);
        Card nine = new Card(Color.Red, Shape.Oval, Filling.Full, 1, deck);
        assertFalse(SetValidator.isValid(one, eight, nine));
    }

}
