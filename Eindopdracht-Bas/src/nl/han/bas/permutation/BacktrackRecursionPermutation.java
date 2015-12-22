package nl.han.bas.permutation;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that uses Brute force and recursion to get its permutations
 * @Author Bas van Summeren<BasVanSummeren@home.nl> 479334
 */
public class BacktrackRecursionPermutation implements StringPermutation
{
    private boolean couldSkip;

    public BacktrackRecursionPermutation()
    {
        this(true);
    }

    public BacktrackRecursionPermutation(boolean shouldSkip)
    {
        couldSkip = shouldSkip;
    }

    public void setCouldSkip(boolean couldSkip)
    {
        this.couldSkip = couldSkip;
    }

    @Override
    public Set<String> getPermutations(String element)
    {
        Set<String> permutations = new HashSet<String>(element.length());
        if (element != null && ! element.isEmpty())
        {
            //Check if there are permutations
            if (element.length() > 1)
            {
                //Get the first char
                char firstChar = element.charAt(0);
                //Get the tail
                String subString = element.substring(1);
                //Get all possible permutations for the tail
                Set<String> permSet = getPermutations(subString);
                //For every permutation of the substring
                for (String currentSubPermutation : permSet)
                {
                    //For every letter
                    for (int i = 0; i <= currentSubPermutation.length(); i++)
                    {
                        //Insert the current character at the given spot
                        permutations.add(currentSubPermutation.substring(0, i) + firstChar + currentSubPermutation.substring(i));
                        if (couldSkip)
                        {
                            while (i < currentSubPermutation.length() - 1 && element.charAt(i + 1) == firstChar)
                            {
                                i++;
                            }
                        }
                    }
                }
            } else
            {
                //Add the words if there is only one option
                permutations.add(element);
            }
        }
        return permutations;
    }
}
