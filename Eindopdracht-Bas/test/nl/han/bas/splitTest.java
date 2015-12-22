package nl.han.bas;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

import static nl.han.bas.WordSplitter.splitText;
import static org.junit.Assert.*;

/**
 * Created by Bas on 22-12-2015.
 */
public class SplitTest {

    @Test
    public void testNull(){
        Set<String> result = splitText(null);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testEmpty(){
        Set<String> result = splitText("");
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testBanaan(){
        Set<String> result = splitText("Banaan");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("banaan", result.iterator().next());
    }

    @Test
    public void testDoubleBanaan(){
        Set<String> result = splitText("Banaan banaan");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("banaan", result.iterator().next());
    }

    @Test
    public void testBanaanAndSign(){
        Set<String> result = splitText("Banaan &%");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("banaan", result.iterator().next());
    }

    @Test
    public void testBanaanSign(){
        Set<String> result = splitText("Banaan&%");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("banaan", result.iterator().next());
    }

    @Test
    public void testSentence(){
        Set<String> result = splitText("Een aap at een banaan. De banaan was lekker!");
        assertNotNull(result);
        assertEquals(7, result.size());
        Iterator<String> it = result.iterator();

        assertEquals("een",it.next());
        assertEquals("aap",it.next());
        assertEquals("at",it.next());
        assertEquals("banaan",it.next());
        assertEquals("de", it.next());
        assertEquals("was",it.next());
        assertEquals("lekker", it.next());

        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorRemoveCurrent(){
        Set<String> result = splitText("test code");
        assertNotNull(result);
        assertEquals(2, result.size());
        Iterator<String> it = result.iterator();
        String current = it.next();
        assertEquals("test", current);
        result.remove(current);

        assertTrue(it.hasNext());

    }
}
