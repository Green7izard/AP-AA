package nl.han.bas.permutation;

import nl.han.bas.permutation.allpossible.AnagramFinder;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Opdracht 10: Anagrammen
 * <p>
 * Anagrammen zijn woorden die uit dezelfde letters zijn samengesteld.
 * Voorbeeld: ‘een’, ‘nee’, en ‘ene’ zijn anagrammen van elkaar.
 * Schrijf een algoritme dat alle anagrammen vindt die voorkomen in een stuk tekst, die je invoert.
 * Het verschil tussen hoofd- en kleine letters mag je negeren.
 * Elk woord komt maar een keer voor in zijn lijst van anagrammen.
 * @Author Bas van Summeren<BasVanSummeren@home.nl> 479334
 */
public class Main
{

    private static final int LINE_LENGTH = 100;

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        AnagramFinder finder = new AnagramFinder();
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
                if(currentLine.length()>2)
                {
                    System.out.println(currentLine.substring(0, currentLine.length() - 2) + "\n");
                }
            }
        }
        System.out.println("End of the program!");
    }
}
