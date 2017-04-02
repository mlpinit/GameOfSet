import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.mlpinit.set.Filling;

public class FillingTest {

    @Test
    public void test_initial() {
        assertEquals("E", Filling.Empty.initial());
        assertEquals("S", Filling.Striped.initial());
        assertEquals("F", Filling.Full.initial());
    }
}
