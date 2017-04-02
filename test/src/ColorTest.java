import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.mlpinit.set.Color;

public class ColorTest {

    @Test
    public void test_initial() {
        assertEquals("R", Color.Red.initial());
        assertEquals("G", Color.Green.initial());
        assertEquals("M", Color.Mauve.initial());
    }
}
