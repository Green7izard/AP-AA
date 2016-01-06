package nl.han.bas.change.currency;

import java.util.Comparator;
import java.util.SortedSet;

/**
 * Created by Bas on 6-1-2016.
 */
public interface Currency
{

    /**
     * Comparator to sort by the highest value up!
     */
    public static final Comparator<Integer> CURRENCY_COMPARATOR = new Comparator<Integer>()
    {
        public int compare(Integer first, Integer second)
        {
            return second - first;
        }
    };

    /**
     * Gets a set of coins. Values are in Cents (example: 1 Euro is 100)
     * Returns a set sorted with the highest value first!
     *
     * @return
     */
    SortedSet<Integer> getCoins();

    /**
     * Return the name of this currency
     *
     * @return the name in String format!
     */
    String getName();

    /**
     * Sign that should be before the number!
     * example: For euro: '€'
     *
     * @return
     */
    String getPrefix();

}
