package nl.han.bas;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class with a utility function to split sentences into words!
 * Created by Bas on 22-12-2015.
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
    public static Set<String> splitText(String input)
    {
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        if (input != null)
        {
            Pattern p = Pattern.compile("[\\w']+");
            Matcher m = p.matcher(input);
            while (m.find())
            {
                set.add(input.substring(m.start(), m.end()).toLowerCase());
            }
        }
        return set;
    }


}
