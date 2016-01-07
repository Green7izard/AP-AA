import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        AVLTree<Integer, String> tree = new AVLTree<Integer, String>();
        print("Starting the AVL Tree");
        print("Type quit to quit");
        print("Type print to print");
        print("Type reset to clear the tree");
        print("Type clear to print loads of new lines");
        print("Type a number to add a number");
        while (true)
        {
            String value = s.nextLine().toLowerCase();
            if (value.contains("quit"))
            {
                break;
            } else if (value.contains("size"))
            {
                print("Current size: " + tree.size());
            } else if (value.contains("print"))
            {
                print("Current tree:");
                print(tree.toString());
            } else if (value.contains("reset"))
            {
                print("Resetting tree");
                tree.clear();
            } else if (value.contains("clear"))
            {
                print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
            } else
            {
                try
                {
                    int number = Integer.parseInt(value);
                    insertValue(tree, number);
                } catch (Exception e)
                {
                    print("unknown command!");
                }
            }

        }
        print("Shutting down");
    }


    private static void insertValue(AVLTree<Integer, String> target, int number)
    {
        String insert = ""+number;
        String replaced = target.put(number, insert);
        print("Inserted: \"" + insert + "\" at location: " + number);
        if (replaced != null)
        {
            print("Replaced: " + replaced);
        }
    }

    private static void print(String s)
    {
        System.out.println(s);
    }
}
