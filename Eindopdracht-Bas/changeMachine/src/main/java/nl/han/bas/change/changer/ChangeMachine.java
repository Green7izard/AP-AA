package nl.han.bas.change.changer;

import nl.han.bas.change.currency.Currency;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Bas on 6-1-2016.
 */
public class ChangeMachine implements ChangeGiver
{

    private Currency currentCurrency;

    /**
     * Create the machine
     *
     * @param currency the desired currency
     */
    public ChangeMachine(Currency currency)
    {
        this.currentCurrency = currency;
    }

    public void setCurrency(Currency currency)
    {
        this.currentCurrency = currency;
    }

    public Change getChange(int amount)
    {
        Map<Integer, Integer> returnCoins = new LinkedHashMap<Integer, Integer>();
        for (Integer currentValue : currentCurrency.getCoins())
        {
            int numberOfCoins = 0;
            while (amount >= currentValue)
            {
                amount -= currentValue;
                numberOfCoins++;
            }
            returnCoins.put(currentValue, numberOfCoins);
        }
        if (amount > 0)
        {
            returnCoins.put(- 1, amount);
        }
        return new Change(currentCurrency, returnCoins);
    }
}
