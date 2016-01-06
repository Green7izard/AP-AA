package nl.han.bas.change.utility;

import nl.han.bas.change.currency.Currency;

/**
 * Created by Bas on 6-1-2016.
 */
public final class MoneyToString
{

    public static final String toText(int number, Currency currency)
    {
        return currency.getPrefix() + toText(number);
    }

    public static final String toText(int number)
    {
        StringBuffer value = new StringBuffer(String.valueOf(number));
        if (value.length() > 2)
        {
            for (int i = 5; i < value.length(); i += 4)
            {
                value.insert(value.length() - i, ',');
            }
            value.insert(value.length() - 2, '.');
        } else if (value.length() == 2)
        {
            value.insert(0, "0.");
        } else if (value.length() == 1)
        {
            value.insert(0, "0.0");
        } else
        {
            value.insert(0, "0.0");
        }
        return value.toString();
    }
}
