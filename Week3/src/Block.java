import java.util.ArrayList;

/**
 * Created by Remco on 25-11-2015.
 */
public class Block {

    ArrayList<NumberBox> numbers = new ArrayList<NumberBox>(9);

    boolean doesContain(int value)
    {
        for(int i = 0; i < numbers.size(); i++)
        {
            if(numbers.get(i).getValue() == value)
            {
                return true;
            }
        }
        return false;
    }

    void addNumber(NumberBox number)
    {
        numbers.add(number);
    }
}
