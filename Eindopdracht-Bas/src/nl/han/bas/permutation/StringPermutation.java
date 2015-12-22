package nl.han.bas.permutation;

import java.util.Set;

/**
 * Interface for permutations engines
 * Created by Bas on 22-12-2015.
 */
public interface StringPermutation
{

    /**
     * Get all possible permutations of a string
     *
     * @param input the string
     * @return all possible permutations, empty set if non valid input
     */
    public Set<String> getPermutations(String input);
}
