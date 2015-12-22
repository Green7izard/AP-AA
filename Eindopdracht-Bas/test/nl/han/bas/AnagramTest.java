package nl.han.bas;

import nl.han.bas.permutation.BacktrackRecursionPermutation;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bas on 22-12-2015.
 */
public class AnagramTest
{
    @Test
    public void getWordsComparisonTest()
    {
        assertEquals(WordSplitter.splitText("Ik heb honger. Ik ga Eten"), new AnagramFinder().getWords("Ik heb honger. Ik ga Eten"));
    }

    @Test
    public void getAnagramsComparisonTest()
    {
        assertEquals(new AnagramFinder(new BacktrackRecursionPermutation()).getAnagrams(WordSplitter.splitText("Ik heb honger. Ik ga Eten")), new AnagramFinder(new BacktrackRecursionPermutation()).getAnagrams("Ik heb honger. Ik ga Eten"));
    }


    @Test
    public void getAnagramsEenTest()
    {
        Set<String> set = new AnagramFinder(new BacktrackRecursionPermutation()).getAnagrams("een");
        assertEquals(3, set.size());
        Iterator<String> it = set.iterator();
        assertEquals(it.next(), "een");
        assertEquals(it.next(), "ene");
        assertEquals(it.next(), "nee");
    }
}
