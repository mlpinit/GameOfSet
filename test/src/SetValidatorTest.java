import static org.junit.Assert.*;
import org.junit.Test;

import com.mlpinit.set.Card;
import com.mlpinit.set.Color;
import com.mlpinit.set.Filling;
import com.mlpinit.set.Shape;
import com.mlpinit.set.SetValidator;

public class SetValidatorTest {

    @Test
    public void test_is_valid_all_different_count() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card two = new Card(Color.Red, Shape.Oval, Filling.Empty, 2);
        Card three = new Card(Color.Red, Shape.Oval, Filling.Empty, 3);
        assertTrue(SetValidator.isValid(one, two, three));
    }

    @Test
    public void test_is_valid_all_different_shape() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card four = new Card(Color.Red, Shape.Squiggle, Filling.Empty, 1);
        Card five = new Card(Color.Red, Shape.Diamond, Filling.Empty, 1);
        assertTrue(SetValidator.isValid(one, four, five));
    }

    @Test
    public void test_is_valid_all_different_color() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card six = new Card(Color.Green, Shape.Oval, Filling.Empty, 1);
        Card seven = new Card(Color.Mauve, Shape.Oval, Filling.Empty, 1);
        assertTrue(SetValidator.isValid(one, six, seven));
    }

    @Test
    public void test_is_valid_all_different_filling() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card eight = new Card(Color.Red, Shape.Oval, Filling.Striped, 1);
        Card nine = new Card(Color.Red, Shape.Oval, Filling.Full, 1);
        assertTrue(SetValidator.isValid(one, eight, nine));
    }

    @Test
    public void test_is_not_valid_count() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card two = new Card(Color.Red, Shape.Oval, Filling.Empty, 2);
        Card three = new Card(Color.Red, Shape.Oval, Filling.Empty, 2);
        assertFalse(SetValidator.isValid(one, two, three));
    }

    @Test
    public void test_is_not_valid_shape() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card four = new Card(Color.Red, Shape.Diamond, Filling.Empty, 1);
        Card five = new Card(Color.Red, Shape.Diamond, Filling.Empty, 1);
        assertFalse(SetValidator.isValid(one, four, five));
    }

    @Test
    public void test_is_not_valid_color() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card six = new Card(Color.Green, Shape.Oval, Filling.Empty, 1);
        Card seven = new Card(Color.Green, Shape.Oval, Filling.Empty, 1);
        assertFalse(SetValidator.isValid(one, six, seven));
    }

    @Test
    public void test_is_not_valid_filling() {
        Card one = new Card(Color.Red, Shape.Oval, Filling.Empty, 1);
        Card eight = new Card(Color.Red, Shape.Oval, Filling.Full, 1);
        Card nine = new Card(Color.Red, Shape.Oval, Filling.Full, 1);
        assertFalse(SetValidator.isValid(one, eight, nine));
    }

}
