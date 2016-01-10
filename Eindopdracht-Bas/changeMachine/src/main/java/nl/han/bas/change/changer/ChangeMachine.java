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
        return getChange(amount, true);
    }

    public Change getChange(int amount, boolean round)
    {
        Map<Integer, Integer> returnCoins = new LinkedHashMap<Integer, Integer>();
        for (Integer currentValue : currentCurrency.getCoins())
        {
            int numberOfCoins = amount/((int)currentValue);
            returnCoins.put(currentValue, numberOfCoins);
            amount-=numberOfCoins*((int)currentValue);
        }
        if (amount > 0)
        {
            if(round)
            {
                int lastValue = currentCurrency.getCoins().last();
                if(amount>lastValue/2)
                {
                    returnCoins.put(lastValue, returnCoins.get(lastValue)+1);
                }
            }
            else
            {
                returnCoins.put(- 1, amount);
            }
        }
        return new Change(currentCurrency, returnCoins);
    }
}
