package nl.han.bas.permutation.allpossible;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Bas on 22-12-2015.
 */
public class BackTrackingRecursionPermutationTest
{

    @Test
    public void getPermutationsForEenTest()
    {
        Set<String> set = new BacktrackRecursionPermutation().getPermutations("een");
        assertEquals(3, set.size());
        Iterator<String> it = set.iterator();
        assertEquals(it.next(), "ene");
        assertEquals(it.next(), "een");
        assertEquals(it.next(), "nee");
    }


    @Test
    public void getPermutationsForAAABTest()
    {
        Set<String> set = new BacktrackRecursionPermutation().getPermutations("AAAB");
        assertEquals(4, set.size());
        Iterator<String> it = set.iterator();
        assertEquals(it.next(), "AABA");
        assertEquals(it.next(), "BAAA");
        assertEquals(it.next(), "ABAA");
        assertEquals(it.next(), "AAAB");
    }

    @Test
    public void testSkipInfluenceUnique()
    {
        String text = "abcdefghij";
        long timeBefore = System.nanoTime();
        Set<String> setSkipped = new BacktrackRecursionPermutation(true).getPermutations(text);
        long timeBetween = System.nanoTime();
        Set<String> setUnskipped = new BacktrackRecursionPermutation(false).getPermutations(text);
        long timeAfter = System.nanoTime();
        assertEquals(setSkipped.size(), setUnskipped.size());
        assertEquals(3628800, setUnskipped.size());
        assertArrayEquals(setSkipped.toArray(), setUnskipped.toArray());
        long skipTime = timeBetween - timeBefore;
        long unskipTime = timeAfter - timeBetween;
        System.out.println("Unique characters| Skipped " + skipTime + "  | unskipped " + unskipTime);
        assertTrue("Skipped was not faster!", skipTime < unskipTime);
    }


    @Test
    public void testSkipInfluenceOneDifference()
    {
        String text = "aaaaaaaaab";
        long timeBefore = System.nanoTime();
        Set<String> setSkipped = new BacktrackRecursionPermutation(true).getPermutations(text);
        long timeBetween = System.nanoTime();
        Set<String> setUnskipped = new BacktrackRecursionPermutation(false).getPermutations(text);
        long timeAfter = System.nanoTime();
        assertEquals(setSkipped.size(), setUnskipped.size());
        assertEquals(10, setUnskipped.size());
        assertArrayEquals(setSkipped.toArray(), setUnskipped.toArray());
        long skipTime = timeBetween - timeBefore;
        long unskipTime = timeAfter - timeBetween;
        System.out.println("One Difference   | Skipped " + skipTime + "      | unskipped " + unskipTime);
        assertTrue("Skipped was not faster!", skipTime < unskipTime);
    }

    @Test
    public void testSkipInfluenceNoDifference()
    {
        String text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        long timeBefore = System.nanoTime();
        Set<String> setSkipped = new BacktrackRecursionPermutation(true).getPermutations(text);
        long timeBetween = System.nanoTime();
        Set<String> setUnskipped = new BacktrackRecursionPermutation(false).getPermutations(text);
        long timeAfter = System.nanoTime();
        assertEquals(setSkipped.size(), setUnskipped.size());
        assertEquals(1, setUnskipped.size());
        assertArrayEquals(setSkipped.toArray(), setUnskipped.toArray());
        long skipTime = timeBetween - timeBefore;
        long unskipTime = timeAfter - timeBetween;
        System.out.println("No difference    | Skipped " + skipTime + "      | unskipped " + unskipTime);
        assertTrue("Skipped was not faster!", skipTime < unskipTime);
    }


    @Test
    public void testSkipInfluenceRandom()
    {
        String text = "kdafjla";
        long timeBefore = System.nanoTime();
        Set<String> setSkipped = new BacktrackRecursionPermutation(true).getPermutations(text);
        long timeBetween = System.nanoTime();
        Set<String> setUnskipped = new BacktrackRecursionPermutation(false).getPermutations(text);
        long timeAfter = System.nanoTime();
        assertEquals(setSkipped.size(), setUnskipped.size());
        assertEquals(2520, setUnskipped.size());
        assertTrue(setUnskipped.containsAll(setSkipped));
        assertArrayEquals(setSkipped.toArray(), setUnskipped.toArray());
        long skipTime = timeBetween - timeBefore;
        long unskipTime = timeAfter - timeBetween;
        System.out.println(text+"| Skipped " + skipTime + "  | unskipped " + unskipTime);


        text = "sdtserr";
        timeBefore = System.nanoTime();
        setSkipped = new BacktrackRecursionPermutation(true).getPermutations(text);
        timeBetween = System.nanoTime();
        setUnskipped = new BacktrackRecursionPermutation(false).getPermutations(text);
        timeAfter = System.nanoTime();
        assertEquals(setSkipped.size(), setUnskipped.size());
        assertEquals(1260, setUnskipped.size());
        assertTrue(setUnskipped.containsAll(setSkipped));
        skipTime = timeBetween - timeBefore;
        unskipTime = timeAfter - timeBetween;
        System.out.println(text+"| Skipped " + skipTime + "  | unskipped " + unskipTime);

        text = "sfgsdfg";
        timeBefore = System.nanoTime();
        setSkipped = new BacktrackRecursionPermutation(true).getPermutations(text);
        timeBetween = System.nanoTime();
        setUnskipped = new BacktrackRecursionPermutation(false).getPermutations(text);
        timeAfter = System.nanoTime();
        assertEquals(setSkipped.size(), setUnskipped.size());
        assertEquals(630, setUnskipped.size());
        assertTrue(setUnskipped.containsAll(setSkipped));
        skipTime = timeBetween - timeBefore;
        unskipTime = timeAfter - timeBetween;
        System.out.println(text+"| Skipped " + skipTime + "  | unskipped " + unskipTime);

        text = "srtsrhf";
        timeBefore = System.nanoTime();
        setSkipped = new BacktrackRecursionPermutation(true).getPermutations(text);
        timeBetween = System.nanoTime();
        setUnskipped = new BacktrackRecursionPermutation(false).getPermutations(text);
        timeAfter = System.nanoTime();
        assertEquals(setSkipped.size(), setUnskipped.size());
        assertEquals(1260, setUnskipped.size());
        assertTrue(setUnskipped.containsAll(setSkipped));
        skipTime = timeBetween - timeBefore;
        unskipTime = timeAfter - timeBetween;
        System.out.println(text+"| Skipped " + skipTime + "  | unskipped " + unskipTime);

        text = "546544";
        timeBefore = System.nanoTime();
        setSkipped = new BacktrackRecursionPermutation(true).getPermutations(text);
        timeBetween = System.nanoTime();
        setUnskipped = new BacktrackRecursionPermutation(false).getPermutations(text);
        timeAfter = System.nanoTime();
        assertEquals(setSkipped.size(), setUnskipped.size());
        assertEquals(60, setUnskipped.size());
        assertTrue(setUnskipped.containsAll(setSkipped));
        skipTime = timeBetween - timeBefore;
        unskipTime = timeAfter - timeBetween;
        System.out.println(text + "| Skipped " + skipTime + "  | unskipped " + unskipTime);


    }
}
