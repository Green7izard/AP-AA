package nl.han.bas.permutation;


import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Opdracht 10: Anagrammen
 * <p>
 * Anagrammen zijn woorden die uit dezelfde letters zijn samengesteld.
 * Voorbeeld: 'een', 'nee', en 'ene' zijn anagrammen van elkaar.
 * Schrijf een algoritme dat alle anagrammen vindt die voorkomen in een stuk tekst, die je invoert.
 * Het verschil tussen hoofd- en kleine letters mag je negeren.
 * Elk woord komt maar een keer voor in zijn lijst van anagrammen.
 *
 * @Author Bas van Summeren<BasVanSummeren@home.nl> 479334
 */
public class Main
{

    private static final int LINE_LENGTH = 100;

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Anagram Finder by Bas van Summeren");
        System.out.println("Type \"all\" for all permutations edition");
        System.out.println("Type anything else for the inline edition");
        if (scan.nextLine().toLowerCase().trim().contains("all"))
        {
            allPossible(scan);
        } else
        {
            inline(scan);
        }

        System.out.println("End of the program!");
    }


    private static void allPossible(Scanner scan)
    {
        nl.han.bas.permutation.allpossible.AnagramFinder finder = new nl.han.bas.permutation.allpossible.AnagramFinder();
        System.out.println("Anagram Finder By Bas van Summeren:\n Type \"quit\" to shut down. Type anything else to get all possible permutations!");
        while (true)
        {
            String input = scan.nextLine();
            if (input.equals("quit"))
            {
                break;
            } else
            {
                Set<String> anagrams = finder.getAnagrams(input);
                System.out.println("Anagrams for the words in: \"" + input + "\"");
                String currentLine = "";
                for (Iterator<String> it = anagrams.iterator(); it.hasNext(); )
                {
                    String current = it.next();
                    if (currentLine.length() + current.length() > LINE_LENGTH)
                    {
                        System.out.println(currentLine);
                        currentLine = "";
                    }
                    currentLine += current + ", ";
                }
                if (currentLine.length() > 2)
                {
                    System.out.println(currentLine.substring(0, currentLine.length() - 2) + "\n");
                }
            }
        }
    }

    private static void inline(Scanner scan)
    {
        nl.han.bas.permutation.inline.AnagramFinder finder = new nl.han.bas.permutation.inline.AnagramFinder();
        System.out.println("Anagram Finder By Bas van Summeren:\n"+"Type \"quit\" to shut down. \n"+
                                   "Type \"unique\" to toggle getting unique anagrams.\n"+
                                   "Type anything else to get all anagrams within the sentance!");
        boolean unique= true;
        while (true)
        {
            String input = scan.nextLine();
            if (input.equals("quit"))
            {
                break;
            } else if(input.equals("unique"))
            {
                unique=!unique;
                if(unique)
                {
                    System.out.println("Now getting unique Anagrams!");
                }
                else
                {
                    System.out.println("Now getting ALL Anagrams!");
                }
            }else
            {
                Map<String, Set<String>> anagrams;
                if(unique)
                {
                    anagrams = finder.getUniqueAnagrams(input);
                }else{
                    anagrams = finder.getAnagrams(input);
                }
                System.out.println("Anagrams for the words in: \"" + input + "\"\n");
                for (Map.Entry<String, Set<String>> entry : anagrams.entrySet())
                {
                    System.out.println("  Anagrams for: " + entry.getKey());
                    String currentLine = "  ";
                    for (Iterator<String> it = entry.getValue().iterator(); it.hasNext(); )
                    {
                        String current = it.next();
                        if (currentLine.length() + current.length() > LINE_LENGTH)
                        {
                            System.out.println(currentLine);
                            currentLine = "  ";
                        }
                        currentLine += current + ", ";
                    }
                    if (currentLine.length() > 2)
                    {
                        System.out.println(currentLine.substring(0, currentLine.length() - 2) + "\n");
                    }
                }

            }
        }
    }
}
