package nl.han.bas.change.currency;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Currency Class holding all the Euro Coins and Bills
 * Created by Bas on 6-1-2016.
 */
public class EuroBills extends Euro
{
    private final SortedSet<Integer> bills;

    /**
     * Creates a Euro value including bills
     *
     * @param useLegacy if this is true, the 2 and 1 cent coins are added
     */
    public EuroBills(boolean useLegacy)
    {
        super(useLegacy);
        bills = super.getCoins();
        bills.add(50000);
        bills.add(20000);
        bills.add(10000);
        bills.add(5000);
        bills.add(2000);
        bills.add(1000);
        bills.add(500);
    }

    @Override
    public SortedSet<Integer> getCoins()
    {
        return new TreeSet<Integer>(bills);
    }
}
