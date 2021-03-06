package nl.han.bas.permutation;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

import static nl.han.bas.permutation.WordSplitter.splitTextUnsorted;
import static org.junit.Assert.*;

/**
 * Created by Bas on 22-12-2015.
 */
public class SplitTest
{

    @Test
    public void testNull()
    {
        Set<String> result = splitTextUnsorted(null);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testEmpty()
    {
        Set<String> result = splitTextUnsorted("");
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testBanaan()
    {
        Set<String> result = splitTextUnsorted("Banaan");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("banaan", result.iterator().next());
    }

    @Test
    public void testDoubleBanaan()
    {
        Set<String> result = splitTextUnsorted("Banaan banaan");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("banaan", result.iterator().next());
    }

    @Test
    public void testBanaanAndSign()
    {
        Set<String> result = splitTextUnsorted("Banaan &%");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("banaan", result.iterator().next());
    }

    @Test
    public void testBanaanSign()
    {
        Set<String> result = splitTextUnsorted("Banaan&%");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("banaan", result.iterator().next());
    }

    @Test
    public void testSentence()
    {
        Set<String> result = splitTextUnsorted("Een aap at een banaan. De banaan was lekker!");
        assertNotNull(result);
        assertEquals(7, result.size());
        Iterator<String> it = result.iterator();

        assertEquals("een", it.next());
        assertEquals("aap", it.next());
        assertEquals("at", it.next());
        assertEquals("banaan", it.next());
        assertEquals("de", it.next());
        assertEquals("was", it.next());
        assertEquals("lekker", it.next());

        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorRemoveCurrent()
    {
        Set<String> result = splitTextUnsorted("test code");
        assertNotNull(result);
        assertEquals(2, result.size());
        Iterator<String> it = result.iterator();
        String current = it.next();
        assertEquals("test", current);
        result.remove(current);

        assertTrue(it.hasNext());

    }
}
