package nl.han.bas.change.changer;

import nl.han.bas.change.currency.Currency;
import nl.han.bas.change.currency.Euro;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bas on 6-1-2016.
 */
public class ChangeTest
{

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalConstructorDouble()
    {
        new Change(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalConstructor1()
    {
        new Change(new Euro(false), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalConstructor2()
    {
        new Change(null, new HashMap<Integer, Integer>());
    }

    @Test
    public void testConstructor()
    {
        Currency cur = new Euro(false);
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 0);
        Change change = new Change(cur, map);
        assertEquals(cur, change.getCurrency());
        assertEquals(map, change.getChange());
        assertEquals((Integer) 0, change.getChange().get(0));

    }

}
