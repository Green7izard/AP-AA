package nl.han.bas.change;

import nl.han.bas.change.changer.Change;
import nl.han.bas.change.changer.ChangeMachine;
import nl.han.bas.change.currency.Currency;
import nl.han.bas.change.currency.Euro;
import nl.han.bas.change.currency.EuroBills;
import nl.han.bas.change.currency.Gulden;
import nl.han.bas.change.utility.MoneyToString;

import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Bas on 6-1-2016.
 *
 * Slotprobleem 2a:
 *
 * Bij een parkeerautomaat of treinkaartjesautomaat kun je betalen met contant geld (munten). Bij sommige automaten hoef je niet met gepast geld te betalen. Deze automaten kunnen wisselgeld teruggeven.
 * Geef een algoritme voor de geldteruggave. Het algoritme heeft als invoer het bedrag van het wisselgeld en bepaalt hoeveel van welke soort munten wordt teruggegeven; liefst worden zo weinig mogelijk munten teruggeven.
 * Ga in eerste instantie uit van de muntwaarden van de bekende euromunten. Je kunt een hoger cijfer verdienen met een algoritme dat ook werkt met de munten van de oude gulden (1 cent, 5 cent, 10 cent, 25 cent, 1 gulden, 2½ gulden).
 *
 */
public class Main
{

    ChangeMachine machine;
    Scanner scan;


    public Main()
    {
        machine = new ChangeMachine(new Euro(true));
        scan = new Scanner(System.in);
    }

    public static void main(String[] args)
    {
        System.out.println("Change machine by Bas van Summeren");
        new Main().start();
    }

    private void start()
    {
        String command = "help";
        while (true)
        {

            if (command.contains("exit"))
            {
                break;
            } else if (command.contains("change") || command.contains("switch"))
            {
                switchCurrency();
            } else if (command.contains("help"))
            {
                print("Type \"exit\" to exit");
                print("Type \"change\" to change currency");
                print("Type any number (in cents) to get your change");
            } else
            {
                try
                {
                    getChange(Integer.parseInt(command));
                } catch (ClassCastException e)
                {
                    print("Not a valid number");
                }
            }

            command = scan.nextLine().trim().toLowerCase();
        }
    }

    private void getChange(int i)
    {
        Change change = machine.getChange(i);
        print("Getting change for: " + MoneyToString.toText(i) + " " + change.getCurrency().getName());
        SortedSet<Integer> keys = new TreeSet<Integer>(Currency.CURRENCY_COMPARATOR);
        keys.addAll(change.getChange().keySet());
        for (Integer entry : keys)
        {
            if (entry == - 1)
            {
                printSame("Not changeable: ");
            } else
            {
                printSame(MoneyToString.toText(entry, change.getCurrency()) + ": ");
            }
            print(change.getChange().get(entry) + "x");
        }
    }

    private void switchCurrency()
    {
        print("Switching Currency!");
        print("Type\"Euro\" for the Euro");
        print("For Euro you can add bills by adding \"Bills\" in the line");
        print("For Euro you can add 1 and 2 cent coins by adding \"cent\" in the line");
        print("Type\"Gulden\" for the Gulden");
        print("For special coins add: \"special\"");
        String command = scan.nextLine().trim().toLowerCase();
        if (command.contains("euro"))
        {
            boolean legacy = command.contains("cent");
            if (command.contains("bills"))
            {
                machine.setCurrency(new EuroBills(legacy));
            } else
            {
                machine.setCurrency(new Euro(legacy));
            }
            print("Switched to Euro");
        } else if (command.contains("gulden"))
        {
            machine.setCurrency(new Gulden(command.contains("special")));
            print("Switched to gulden");
        } else
        {
            print("Did not switch currency!");
        }

    }

    private void print(String s)
    {
        System.out.println(s);
    }

    private void printSame(String s)
    {
        System.out.print(s);
    }
}
