package nl.han.bas.permutation.inline;

import nl.han.bas.permutation.WordSplitter;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

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
        if (first != null && first.equals(second))
        {
            return true;
        }
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
        return WordSplitter.splitTextSorted(input, new SortByLength());
    }

    /**
     * Uses getWords and calls getAnagrams with a set
     *
     * @param input the string that contains the words
     * @return the possible anagrams
     *
     * @see #getAnagrams(SortedSet, boolean)
     */
    public Map<String, Set<String>> getAnagrams(String input)
    {
        return getAnagrams(getWords(input), false);
    }


    /**
     * Uses getWords and calls getAnagrams with a set
     *
     * @param input the string that contains the words
     * @return the possible unique(No Ene and Een) anagrams
     *
     * @see #getAnagrams(SortedSet, boolean)
     */
    public Map<String, Set<String>> getUniqueAnagrams(String input)
    {
        return getAnagrams(getWords(input), true);
    }

    /**
     * Gets all possible Anagrams from a list of words
     *
     * @param words The words
     * @return list of Anagrams
     */
    public Map<String, Set<String>> getAnagrams(SortedSet<String> words, boolean ignoreDoubles)
    {
        Map<String, Set<String>> map = new LinkedHashMap<String, Set<String>>();
        Set<String> toIgnore = new LinkedHashSet<String>();
        for (Iterator<String> checkTo = words.iterator(); checkTo.hasNext(); )
        {
            String compareAgainst = checkTo.next();
            checkTo.remove();
            if (! ignoreDoubles || ! toIgnore.contains(compareAgainst))
            {
                insertInto(map, compareAgainst, compareAgainst);
                for (Iterator<String> currentIt = words.iterator(); currentIt.hasNext(); )
                {
                    String currentWord = currentIt.next();
                    if (currentWord.length() == compareAgainst.length())
                    {
                        if (sameLetters(compareAgainst, currentWord))
                        {
                            insertInto(map, compareAgainst, currentWord);
                            if (! ignoreDoubles)
                            {
                                insertInto(map, currentWord, compareAgainst);
                            } else
                            {
                                toIgnore.add(currentWord);
                            }
                        }
                    } else if (currentWord.length() > compareAgainst.length())
                    {
                        break;
                    }
                }
            } else
            {
                toIgnore.remove(compareAgainst);
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


    private static class SortByLength implements Comparator<String>
    {

        @Override
        public int compare(String o1, String o2)
        {
            int deltaLength = o1.length() - o2.length();
            if (deltaLength == 0)
            {
                return o1.compareTo(o2);
            }
            return deltaLength;
        }

        @Override
        public Comparator<String> reversed()
        {
            return null;
        }

        @Override
        public Comparator<String> thenComparing(Comparator<? super String> other)
        {
            return null;
        }

        @Override
        public <U> Comparator<String> thenComparing(Function<? super String, ? extends U> keyExtractor, Comparator<? super U> keyComparator)
        {
            return null;
        }

        @Override
        public <U extends Comparable<? super U>> Comparator<String> thenComparing(Function<? super String, ? extends U> keyExtractor)
        {
            return null;
        }

        @Override
        public Comparator<String> thenComparingInt(ToIntFunction<? super String> keyExtractor)
        {
            return null;
        }

        @Override
        public Comparator<String> thenComparingLong(ToLongFunction<? super String> keyExtractor)
        {
            return null;
        }

        @Override
        public Comparator<String> thenComparingDouble(ToDoubleFunction<? super String> keyExtractor)
        {
            return null;
        }
    }
}
