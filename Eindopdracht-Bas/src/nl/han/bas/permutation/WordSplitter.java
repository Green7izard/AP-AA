package nl.han.bas.permutation;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class with a utility function to split sentences into words!
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

    private static void splitText(Set<String> set, String input)
    {
        Pattern p = Pattern.compile("[\\w']+");
        Matcher m = p.matcher(input);
        while (m.find())
        {
            set.add(input.substring(m.start(), m.end()).toLowerCase());
        }
    }

    public static SortedSet<String> splitTextSorted(String input)
    {
        return splitTextSorted(input, null);
    }

    public static SortedSet<String> splitTextSorted(String input, Comparator<String> comp)
    {
        SortedSet<String> set;
        if(comp!=null)
        {
            set= new TreeSet<String>(comp);
        }
        else
        {
            set = new TreeSet<String>(comp);
        }
        if (input != null)
        {
            splitText(set, input);
        }
        return set;
    }
    public static SortedSet<String> splitTextLengthSorted(String input)
    {
        return splitTextSorted(input, new SortByLength());
    }

    private static class SortByLength implements Comparator<String>{

        @Override
        public int compare(String o1, String o2)
        {
            int deltaLength= o1.length()-o2.length();
            if(deltaLength==0)
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
