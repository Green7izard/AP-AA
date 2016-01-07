package nl.han.bas.change.currency;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Currency set that holds the coins of the Dutch Gulden
 * Source: https://nl.wikipedia.org/wiki/Nederlandse_gulden
 * Created by Bas on 6-1-2016.
 */
public class Gulden implements Currency
{

    private final SortedSet<Integer> coins;

    /**
     * creates the gulden
     *
     * @param useSpecialPublication After the war Netherlands had special, but legal coins. Setting this to true will
     *                              add them
     */
    public Gulden(boolean useSpecialPublication)
    {
        coins = new TreeSet<Integer>(CURRENCY_COMPARATOR);
        if (useSpecialPublication)
        {
            coins.add(5000);
            coins.add(1000);
        }
        coins.add(500);
        coins.add(300);
        coins.add(250);
        coins.add(100);
        coins.add(50);
        coins.add(25);
        coins.add(10);
        coins.add(5);
        coins.add(1);
    }

    public SortedSet<Integer> getCoins()
    {
        return new TreeSet<Integer>(coins);
    }

    public String getName()
    {
        return "Gulden";
    }

    public String getPrefix()
    {
        //Unicode for the Florin Sighn
        return "\u0192";
    }
}
