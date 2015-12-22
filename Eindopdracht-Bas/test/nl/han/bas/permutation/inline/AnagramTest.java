package nl.han.bas.permutation.inline;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Bas on 22-12-2015.
 */
public class AnagramTest
{
    @Test
    public void sameLetterTestWrongSize()
    {
        assertFalse(AnagramFinder.sameLetters("baan", "ban"));
        assertFalse(AnagramFinder.sameLetters("sap", "saap"));
    }

    @Test
    public void sameLetterTestSameWords()
    {
        assertTrue("de", AnagramFinder.sameLetters("de", "de"));
        assertTrue("het", AnagramFinder.sameLetters("het", "het"));
        assertTrue("friet", AnagramFinder.sameLetters("friet", "friet"));
        assertTrue("koffieboon", AnagramFinder.sameLetters("koffieboon", "koffieboon"));
        assertTrue("hefboomconstructie", AnagramFinder.sameLetters("hefboomconstructie", "hefboomconstructie"));
        assertTrue("minimumtemperaturen", AnagramFinder.sameLetters("minimumtemperaturen", "minimumtemperaturen"));
    }

    @Test
    public void sameLetterTestDifferentWords()
    {
        assertTrue("de", AnagramFinder.sameLetters("ed", "de"));
        assertTrue("het", AnagramFinder.sameLetters("het", "teh"));
        assertTrue("friet", AnagramFinder.sameLetters("friet", "fetri"));
        assertTrue("koffieboon", AnagramFinder.sameLetters("koffieboon", "okboffonie"));
        assertTrue("hefboomconstructie", AnagramFinder.sameLetters("hefboomconstructie", "hefbooconstructiem"));
        assertTrue("minimumtemperaturen", AnagramFinder.sameLetters("minimumtemperaturen", "eminimumtmperaturen"));
    }

    @Test
    public void anagramsEen()
    {
        AnagramFinder finder = new AnagramFinder();
        Map<String, Set<String>> result = finder.getAnagrams("een");
        assertEquals(1, result.size());
        assertNotNull(result.get("een"));
        assertEquals(1, result.get("een").size());
        assertEquals("een", result.get("een").iterator().next());
    }

    @Test
    public void anagramsEenEneNee()
    {
        AnagramFinder finder = new AnagramFinder();
        Map<String, Set<String>> result = finder.getAnagrams("een ene nee");
        assertEquals(3, result.size());


        assertNotNull(result.get("een"));
        assertEquals(3, result.get("een").size());
        Iterator<String> s = result.get("een").iterator();
        assertEquals("een", s.next());
        assertEquals("ene", s.next());
        assertEquals("nee", s.next());
        assertFalse(s.hasNext());

        assertNotNull(result.get("ene"));
        assertEquals(3, result.get("ene").size());
        s = result.get("ene").iterator();
        assertEquals("een", s.next());
        assertEquals("ene", s.next());
        assertEquals("nee", s.next());
        assertFalse(s.hasNext());

        assertNotNull(result.get("nee"));
        assertEquals(3, result.get("nee").size());
        s = result.get("nee").iterator();
        assertEquals("een", s.next());
        assertEquals("ene", s.next());
        assertEquals("nee", s.next());
        assertFalse(s.hasNext());
    }

    @Test
    public void anagramsBigWordDouble()
    {
        final String bigWord = "zandzeepsodemineraalwatersteenstralen";
        AnagramFinder finder = new AnagramFinder();
        Map<String, Set<String>> result = finder.getAnagrams(bigWord + " " + bigWord + " " + bigWord + " " + bigWord);
        assertEquals(1, result.size());
        assertNotNull(result.get(bigWord));
        assertEquals(1, result.get(bigWord).size());
        assertEquals(bigWord, result.get(bigWord).iterator().next());
    }

    @Test
    public void anagramsBigWordsSameLetters()
    {
        final String bigWord = "zandzeepsodemineraalwatersteenstralen";
        final String shuffledWord = "aaltrloeiazrneretadtsnepeeznsdwemsean";
        Iterator<String> s;
        AnagramFinder finder = new AnagramFinder();
        Map<String, Set<String>> result = finder.getAnagrams(bigWord + " " + shuffledWord + " " + bigWord + " " + shuffledWord);


        assertEquals(2, result.size());

        assertNotNull(result.get(bigWord));
        assertEquals(2, result.get(bigWord).size());
        s = result.get(bigWord).iterator();
        assertEquals(shuffledWord, s.next());
        assertEquals(bigWord, s.next());
        assertFalse(s.hasNext());

        assertNotNull(result.get(shuffledWord));
        assertEquals(2, result.get(shuffledWord).size());
        s = result.get(shuffledWord).iterator();
        assertEquals(shuffledWord, s.next());
        assertEquals(bigWord, s.next());
        assertFalse(s.hasNext());

    }
}
