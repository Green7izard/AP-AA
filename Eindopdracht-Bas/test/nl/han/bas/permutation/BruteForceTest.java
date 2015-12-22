package nl.han.bas.permutation;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bas on 22-12-2015.
 */
public class BruteForceTest
{

    @Test
    public void getPermutationsForEenTest()
    {
        Set<String> set = new BruteForceRecursionPermutation().getPermutations("een");
        assertEquals(3, set.size());
        Iterator<String> it = set.iterator();
        assertEquals(it.next(), "ene");
        assertEquals(it.next(), "een");
        assertEquals(it.next(), "nee");
    }
}
