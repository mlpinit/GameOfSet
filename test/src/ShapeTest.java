import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.mlpinit.set.Shape;

public class ShapeTest {

    @Test
    public void test_initial() {
        assertEquals("S", Shape.Squiggle.initial());
        assertEquals("D", Shape.Diamond.initial());
        assertEquals("O", Shape.Oval.initial());
    }
}
