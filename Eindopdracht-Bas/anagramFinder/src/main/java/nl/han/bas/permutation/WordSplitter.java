package nl.han.bas.permutation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class with a utility function to split sentences into words!
 *
 * @Author Bas van Summeren<BasVanSummeren@home.nl> 479334
 */
public class WordSplitter
{
    /**
     * Utility classes dont need constructors
     */
    private WordSplitter()
    {

    }


    /**
     * Split a text into words.
     * Every word can only occur ONCE!
     * prevents doubles!
     * Ignores special signs like dots and exclamation marks
     *
     * @param input The text as a input
     * @return a Set of words
     */
    public static Set<String> splitTextUnsorted(String input)
    {
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        if (input != null)
        {
            splitText(set, input);
        }
        return set;
    }

    /**
     * Splits the text into words and puts it into the set
     * @param input
     */
    private static void splitText(Set<String> set, String input)
    {
        Pattern p = Pattern.compile("[\\w']+");
        Matcher m = p.matcher(input);
        while (m.find())
        {
            set.add(input.substring(m.start(), m.end()).toLowerCase());
        }
    }

    /**
     * Split the text into words and sorts them based on the natural order
     * @param input the text
     * @return a sorted set of words
     */
    public static SortedSet<String> splitTextSorted(String input)
    {
        return splitTextSorted(input, null);
    }

    /**
     * Split the text into words and sorts them based on the Comparator
     * @param input the text
     * @param comp the compartor
     * @return a sorted set of words
     */
    public static SortedSet<String> splitTextSorted(String input, Comparator<String> comp)
    {
        SortedSet<String> set;
        if (comp != null)
        {
            set = new TreeSet<String>();
        } else
        {
            set = new TreeSet<String>(comp);
        }
        if (input != null)
        {
            splitText(set, input);
        }
        return set;
    }

}
