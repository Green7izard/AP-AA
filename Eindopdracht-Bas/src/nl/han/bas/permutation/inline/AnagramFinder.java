package nl.han.bas.permutation.inline;

import nl.han.bas.permutation.WordSplitter;

import java.util.*;

/**
 * Anagram finder that will finder anagrams in the current text
 * If the input contains "een" and "ene" they will be marked
 * If the input continas "een" but not "ene" it will not be marked
 *
 * @Author Bas van Summeren<BasVanSummeren@home.nl> 479334
 */
public class AnagramFinder
{

    protected static boolean sameLetters(String first, String second)
    {
        StringBuilder list = new StringBuilder(second);
        for (char c : first.toCharArray())
        {
            boolean found = false;
            for (int i = 0; i < list.length(); i++)
            {
                if (list.charAt(i) == c)
                {
                    list.deleteCharAt(i);
                    found = true;
                    break;
                }
            }
            if (! found)
            {
                return false;
            }
        }
        return list.length() == 0;
    }

    /**
     * Gets all possible words using the WordSplitter
     *
     * @param input the input String
     * @return set of words
     */
    public SortedSet<String> getWords(String input)
    {
        return WordSplitter.splitTextLengthSorted(input);
    }

    /**
     * Uses getWords and calls getAnagrams with a set
     *
     * @param input the string that contains the words
     * @return the possible anagrams
     *
     * @see #getAnagrams(SortedSet)
     */
    public Map<String, Set<String>> getAnagrams(String input)
    {
        return getAnagrams(getWords(input));
    }

    /**
     * Gets all possible Anagrams from a list of words
     *
     * @param words The words
     * @return list of Anagrams
     */
    private Map<String, Set<String>> getAnagrams(SortedSet<String> words)
    {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        for (Iterator<String> checkTo = words.iterator(); checkTo.hasNext(); )
        {
            String compareAgainst = checkTo.next();
            checkTo.remove();
            insertInto(map, compareAgainst, compareAgainst);
            for (Iterator<String> currentIt = words.iterator(); currentIt.hasNext(); )
            {
                String currentWord = currentIt.next();
                if (currentWord.length() == compareAgainst.length())
                {
                    if (sameLetters(compareAgainst, currentWord))
                    {
                        insertInto(map, compareAgainst, currentWord);
                        insertInto(map, currentWord, compareAgainst);
                    }
                } else if (currentWord.length() > compareAgainst.length())
                {
                    break;
                }
            }
        }
        return map;
    }

    private void insertInto(Map<String, Set<String>> map, String key, String value)
    {
        if (! map.containsKey(key))
        {
            map.put(key, new TreeSet<String>());
        }
        map.get(key).add(value);
    }
}
