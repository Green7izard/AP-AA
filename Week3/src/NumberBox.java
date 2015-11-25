/**
 * Created by Remco on 25-11-2015.
 */
public class NumberBox {
    int value = 0;

    void setValue(int newValue)
    {
        value = newValue;
    }

    String getValue()
    {
        if(value != 0)
        {
            return value + "";
        }
        else
        {
            return " ";
        }
    }
}
