import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bas on 25-11-2015.
 */
public class KeyValueTest
{

    @Test
    public void creationTest()
    {
        String value = "Koffieboon";
        KeyValuePair<Integer, String> pair = new KeyValuePair<Integer, String>(1, value);
        assertEquals(1, pair.getKey().intValue());
        assertEquals(value, pair.getValue());
    }

    @Test
    public void replaceTest()
    {
        String value = "Koffieboon";
        String value2 = "Thee";
        KeyValuePair<Integer, String> pair = new KeyValuePair<Integer, String>(1, value);
        assertEquals(value, pair.getValue());
        assertEquals(value, pair.setValue(value2));
        assertEquals(value2, pair.getValue());
    }

    @Test
    public void keyCompareTest()
    {
        String value = "Koffieboon";
        KeyValuePair<Integer, String> pair = new KeyValuePair<Integer, String>(3, value);
        assertEquals(0, pair.compareTo(3));
        assertEquals(1, pair.compareTo(2));
        assertEquals(- 1, pair.compareTo(4));
    }

    @Test
    public void keyValueCompareTest()
    {
        String value = "Koffieboon";
        KeyValuePair<Integer, String> pair = new KeyValuePair<Integer, String>(3, value);
        assertEquals(0, pair.compareTo(new KeyValuePair<Integer, String>(3, value)));
        assertEquals(1, pair.compareTo(new KeyValuePair<Integer, String>(2, value)));
        assertEquals(- 1, pair.compareTo(new KeyValuePair<Integer, String>(4, value)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void faultCompareTest()
    {
        String value = "Koffieboon";
        KeyValuePair<Integer, String> pair = new KeyValuePair<Integer, String>(1, value);
        pair.compareTo(value);
    }
}
