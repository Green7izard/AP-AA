package nl.han.bas.change.changer;

import nl.han.bas.change.currency.Currency;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bas on 6-1-2016.
 */
public class Change
{

    private final Currency currency;
    /**
     * Map giving the coins and Integers
     * Stored as <Value of Coins, Amount of Coins>
     */
    private final Map<Integer, Integer> change;

    /**
     * @param currency The currency that was used
     * @param change   the actual change
     * @throws IllegalArgumentException throws a exception if you supply null values
     */
    public Change(Currency currency, Map<Integer, Integer> change) throws IllegalArgumentException
    {
        if (currency == null || change == null)
        {
            throw new IllegalArgumentException("Cant make a change with a NULL value");
        }
        this.currency = currency;
        this.change = change;
    }

    /**
     * Gets the currency that was used!
     *
     * @return the currency
     */
    public Currency getCurrency()
    {
        return currency;
    }

    /**
     * Return the change
     *
     * @return a copy of the change
     */
    public Map<Integer, Integer> getChange()
    {
        return new HashMap<Integer, Integer>(change);
    }

}
