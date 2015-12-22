package nl.han.bas;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Anagrammen zijn woorden die uit dezelfde letters zijn samengesteld.
 * Voorbeeld: ‘een’, ‘nee’, en ‘ene’ zijn anagrammen van elkaar.
 * Schrijf een algoritme dat alle anagrammen vindt die voorkomen in een stuk tekst, die je invoert.
 * Het verschil tussen hoofd- en kleine letters mag je negeren.
 * Elk woord komt maar een keer voor in zijn lijst van anagrammen.
 * Created by Bas on 22-12-2015.
 */
public class Main
{

    private static final int LINELENGTH = 100;

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        AnagramFinder finder = new AnagramFinder();
        System.out.println("Anagram Finder: Type \"quit\" to shut down. Type anything else to get all possible permutations!");
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
                    if (currentLine.length() + current.length() > LINELENGTH)
                    {
                        System.out.println(currentLine);
                        currentLine = "";
                    }
                    currentLine += current + ", ";
                }
                System.out.println(currentLine + "\n");
            }
        }
        System.out.println("End of the program!");
    }
}
