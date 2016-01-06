package nl.han.bas.change.currency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bas on 6-1-2016.
 */
public class CurrencyComparatorTest
{

    @Test
    public void compareSingleValuesTest()
    {
        //Same number
        assertEquals(0, Currency.CURRENCY_COMPARATOR.compare(4, 4));
        assertEquals(0, Currency.CURRENCY_COMPARATOR.compare(0, 0));
        assertEquals(0, Currency.CURRENCY_COMPARATOR.compare(- 7, - 7));

        //First is larger
        assertEquals(- 1, Currency.CURRENCY_COMPARATOR.compare(5, 4));
        assertEquals(- 1, Currency.CURRENCY_COMPARATOR.compare(1, 0));
        assertEquals(- 1, Currency.CURRENCY_COMPARATOR.compare(- 6, - 7));

        //Second is larger
        assertEquals(1, Currency.CURRENCY_COMPARATOR.compare(4, 5));
        assertEquals(1, Currency.CURRENCY_COMPARATOR.compare(0, 1));
        assertEquals(1, Currency.CURRENCY_COMPARATOR.compare(- 7, - 6));
    }

    @Test
    public void orderListTest()
    {
        Integer number = 20;
        ArrayList<Integer> list = new ArrayList<Integer>(number);
        for (int i = 0; i < number; i++)
        {
            list.add(i);
        }
        Collections.sort(list, Currency.CURRENCY_COMPARATOR);
        for (int i = 0; i < number; i++)
        {
            Integer current = list.get(i);
            assertEquals((Integer) (number - i - 1), current);
        }
    }

}
