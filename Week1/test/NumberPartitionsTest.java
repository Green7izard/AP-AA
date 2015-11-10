import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bas on 9-11-2015.
 */
public class NumberPartitionsTest
{
    @Test
    public void testPartitionsZero()
    {
        List<ArrayList<Integer>> possibilities = new ArrayList<ArrayList<Integer>>(0);
        NumberPartitions.partition(possibilities, 0, 0, new ArrayList<Integer>());
        assertEquals(0, possibilities.size());
    }

    @Test
    public void testPartitionsOne()
    {
        List<ArrayList<Integer>> possibilities = new ArrayList<ArrayList<Integer>>(1);
        NumberPartitions.partition(possibilities, 1, 1, new ArrayList<Integer>());
        assertEquals(1, possibilities.size());
        assertEquals(1, possibilities.get(0).size());
        assertEquals(1, possibilities.get(0).get(0).intValue());
    }

    @Test
    public void testPartitionsTwo()
    {
        List<ArrayList<Integer>> possibilities = new ArrayList<ArrayList<Integer>>(2);
        NumberPartitions.partition(possibilities, 2, 2, null);
        assertEquals(2, possibilities.size());
        assertEquals(1, possibilities.get(0).size());
        assertEquals(2, possibilities.get(0).get(0).intValue());

        assertEquals(2, possibilities.get(1).size());
        assertEquals(1, possibilities.get(1).get(0).intValue());
    }

    @Test
    public void testPartitionsFive()
    {
        List<ArrayList<Integer>> possibilities = new ArrayList<ArrayList<Integer>>(5);
        NumberPartitions.partition(possibilities, 5, 5, new ArrayList<Integer>());
        assertEquals(7, possibilities.size());
        assertEquals(1, possibilities.get(0).size());
        assertEquals(5, possibilities.get(0).get(0).intValue());

        assertEquals(5, possibilities.get(6).size());
        assertEquals(1, possibilities.get(6).get(0).intValue());
        assertEquals(1, possibilities.get(6).get(4).intValue());
    }
}
