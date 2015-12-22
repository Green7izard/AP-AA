package nl.han.bas;

import nl.han.bas.permutation.BacktrackRecursionPermutation;
import nl.han.bas.permutation.StringPermutation;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Author Bas van Summeren<BasVanSummeren@home.nl> 479334
 */
public class AnagramFinder
{

    /**
     * The permutation engine
     */
    private final StringPermutation permutation;

    /**
     * Creates a anagram finder with a specific permutation
     *
     * @param perm the StringPermutation implementation
     */
    public AnagramFinder(StringPermutation perm)
    {
        this.permutation = perm;
    }

    /**
     * Creates a anagramFinder with the default permutation
     */
    public AnagramFinder()
    {
        this(new BacktrackRecursionPermutation());
    }


    /**
     * Gets all possible words using the WordSplitter
     *
     * @param input the input String
     * @return set of words
     */
    public Set<String> getWords(String input)
    {
        return WordSplitter.splitText(input);
    }

    /**
     * Uses getWords and calls getAnagrams with a set
     *
     * @param input the string that contains the words
     * @return the possible anagrams
     *
     * @see #getAnagrams(Set)
     */
    public Set<String> getAnagrams(String input)
    {
        return getAnagrams(getWords(input));
    }

    /**
     * Gets all possible Anagrams from a list of words
     *
     * @param words The words
     * @return list of Anagrams
     */
    public Set<String> getAnagrams(Set<String> words)
    {
        //Tree set to increase the speed of lookups. For longer sentences they will be frequent
        Set<String> anagrams = new TreeSet<String>();
        //For every word
        for (Iterator<String> it = words.iterator(); it.hasNext(); )
        {
            String element = it.next();
            //Ignore if its already in the resultset
            if (! anagrams.contains(element))
            {
                //Get all possible permutations for the word
                Set<String> permutations = permutation.getPermutations(element);

                //TODO Check validity?

                //Add them to the list, doubles will be ignored
                anagrams.addAll(permutations);
            }
        }


        return anagrams;
    }
}
