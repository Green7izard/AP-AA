package nl.han.bas.change.currency;


import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Currency class holding all the Euro Coins
 * Created by Bas on 6-1-2016.
 */
public class Euro implements Currency
{

    private final SortedSet<Integer> coins;

    /**
     * Creates a Euro value
     *
     * @param useLegacy if this is true, the 2 and 1 cent coins are added
     */
    public Euro(boolean useLegacy)
    {
        coins = new TreeSet<Integer>(CURRENCY_COMPARATOR);
        coins.add(200);
        coins.add(100);
        coins.add(50);
        coins.add(20);
        coins.add(10);
        coins.add(5);
        if (useLegacy)
        {
            coins.add(2);
            coins.add(1);
        }
    }

    public SortedSet<Integer> getCoins()
    {
        return new TreeSet<Integer>(coins);
    }

    public String getName()
    {
        return "Euro";
    }

    public String getPrefix()
    {
        //Unicode character for the Euro Sign
        return "\u20ac";
    }
}
