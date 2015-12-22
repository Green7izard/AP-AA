package nl.han.bas.permutation;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that uses Brute force and recursion to get its permutations
 * Created by Bas on 22-12-2015.
 */
public class BruteForceRecursionPermutation implements StringPermutation {
    @Override
    public Set<String> getPermutations(String element) {
        Set<String> permutations = new HashSet<String>(element.length());
        if(element!=null&& !element.isEmpty())
        {
            //Check if there are permutations
            if (element.length() > 1)
            {
                //Get the first char
                String firstChar = element.substring(0,1);
                //Get the tail
                String subString = element.substring(1);
                //Get all possible permutations for the tail
                Set<String> permSet = getPermutations(subString);
                //For every permutation of the substring
                for (String x : permSet)
                {
                    //For every letter
                    for (int i = 0; i <= x.length(); i++)
                    {
                        //Insert the current character at the given spot
                        permutations.add(x.substring(0, i) + firstChar + x.substring(i));
                    }
                }
            }
            else
            {
                //Add the words if there is only one option
                permutations.add(element);
            }
        }
        return permutations;
    }
}
