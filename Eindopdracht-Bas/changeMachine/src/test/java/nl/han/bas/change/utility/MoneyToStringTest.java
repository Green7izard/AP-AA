package nl.han.bas.change.utility;

import nl.han.bas.change.currency.Euro;
import nl.han.bas.change.currency.Gulden;
import org.junit.Test;

import static nl.han.bas.change.utility.MoneyToString.toText;
import static org.junit.Assert.assertEquals;

/**
 * Created by Bas on 6-1-2016.
 */
public class MoneyToStringTest
{

    @Test
    public void testZero()
    {
        assertEquals("0.00", toText(0));
    }

    @Test
    public void testFive()
    {
        assertEquals("0.05", toText(5));
    }

    @Test
    public void testNinetySeven()
    {
        assertEquals("0.97", toText(97));
    }

    @Test
    public void testOneHunderd()
    {
        assertEquals("1.00", toText(100));
    }

    @Test
    public void testNineHunderdFourtyFive()
    {
        assertEquals("9.45", toText(945));
    }

    @Test
    public void test1000()
    {
        assertEquals("10.00", toText(1000));
    }

    @Test
    public void test10000()
    {
        assertEquals("100.00", toText(10000));
    }

    @Test
    public void test100000()
    {
        assertEquals("1,000.00", toText(100000));
    }

    @Test
    public void test100000000()
    {
        assertEquals("1,000,000.00", toText(100000000));
    }


    @Test
    public void testWithEuro()
    {
        assertEquals("\u20ac1,000,000.00", toText(100000000, new Euro(false)));
    }

    @Test
    public void testWithGulden()
    {
        assertEquals("\u01921,000,000.00", toText(100000000, new Gulden(false)));
    }

}
