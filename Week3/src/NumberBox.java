/**
 * Created by Remco on 25-11-2015.
 */
public class NumberBox {
    Integer value;
    boolean isStartValue = false;

    void setValue(int newValue, boolean newIsStartValue)
    {
        value = newValue;
        isStartValue = newIsStartValue;
    }

    Integer getValue()
    {
        return value;
    }

    boolean isStartValue()
    {
        return isStartValue;
    }

    void increaseValue()
    {
        value ++;
    }

    boolean isEqual(int otherValue)
    {
        if(value == otherValue)
        {
            return true;
        }
        return false;
    }
}
