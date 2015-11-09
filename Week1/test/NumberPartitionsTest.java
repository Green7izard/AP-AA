import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bas on 9-11-2015.
 */
public class NumberPartitionsTest
{
    @Test
    public void testPartitionsZero()
    {
        ArrayList<int[]> possibilities = new ArrayList<int[]>(0);
        NumberPartitions.partition(0, 0, possibilities, new ArrayList<Integer>());
        assertEquals(0, possibilities.size());
    }

    @Test
    public void testPartitionsOne()
    {
        ArrayList<int[]> possibilities = new ArrayList<int[]>(1);
        NumberPartitions.partition(1, 1, possibilities, new ArrayList<Integer>());
        assertEquals(1, possibilities.size());
        assertEquals(1, possibilities.get(0).length);
        assertEquals(1, possibilities.get(0)[0]);
    }

    @Test
    public void testPartitionsTwo()
    {
        ArrayList<int[]> possibilities = new ArrayList<int[]>(1);
        NumberPartitions.partition(2, 2, possibilities, null);
        assertEquals(2, possibilities.size());
        assertEquals(1, possibilities.get(0).length);
        assertEquals(2, possibilities.get(0)[0]);

        assertEquals(2, possibilities.get(1).length);
        assertEquals(1, possibilities.get(1)[0]);
    }

    @Test
    public void testPartitionsFive()
    {
        ArrayList<int[]> possibilities = new ArrayList<int[]>(1);
        NumberPartitions.partition(5, 5, possibilities, new ArrayList<Integer>());
        assertEquals(7, possibilities.size());
        assertEquals(1, possibilities.get(0).length);
        assertEquals(5, possibilities.get(0)[0]);

        assertEquals(5, possibilities.get(6).length);
        assertEquals(1, possibilities.get(6)[0]);
        assertEquals(1, possibilities.get(6)[4]);
    }
}
