package nl.han.bas.change.changer;

import nl.han.bas.change.currency.Euro;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Created by Bas on 6-1-2016.
 */
public class ChangeMachineTest
{
    private ChangeMachine machine = new ChangeMachine(new Euro(false));


    @Test
    public void testOne()
    {
        Change change = machine.getChange(1);
        for (Integer i : change.getChange().keySet())
        {
            if (i == - 1)
            {
                assertEquals((Integer) 1, change.getChange().get(i));
            } else
            {
                assertEquals((Integer) 0, change.getChange().get(i));
            }
        }
    }


    @Test
    public void testFive()
    {
        Change change = machine.getChange(5);
        for (Integer i : change.getChange().keySet())
        {
            if (i == 5)
            {
                assertEquals((Integer) 1, change.getChange().get(i));
            } else
            {
                assertEquals((Integer) 0, change.getChange().get(i));
            }
        }
        assertFalse(change.getChange().containsKey(- 1));
    }

    @Test
    public void test454534585()
    {
        Change change = machine.getChange(454534585);
        for (Integer i : change.getChange().keySet())
        {
            if (i == 200)
            {
                assertEquals((Integer) 2272672, change.getChange().get(i));
            } else
            {
                assertEquals((Integer) 1, change.getChange().get(i));
            }
        }
        assertFalse(change.getChange().containsKey(- 1));
    }

    @Test
    public void test454534589()
    {
        Change change = machine.getChange(454534589);
        for (Integer i : change.getChange().keySet())
        {
            if (i == 200)
            {
                assertEquals((Integer) 2272672, change.getChange().get(i));
            } else if (i == - 1)
            {
                assertEquals((Integer) 4, change.getChange().get(i));
            } else
            {
                assertEquals((Integer) 1, change.getChange().get(i));
            }
        }
    }
}
