import java.util.ArrayList;
import java.util.List;

public class NumberPartitions
{

    private static final int NUMBER = 7;

    public static void main(String[] args)
    {
        int number;
        try
        {
            number = Integer.parseInt(args[0]);
        } catch (Exception e)
        {
            number = NUMBER;
        }
        System.out.println("Running with number: " + number);

        List<ArrayList<Integer>> possibilities = new ArrayList<ArrayList<Integer>>(number);

        //Get the partitions
        partition(possibilities, number, number, null);

        //PRINT
        for (List<Integer> results : possibilities)
        {
            boolean first = true;
            for (int result : results)
            {
                if (! first)
                {
                    System.out.print("+");
                }
                first = false;
                System.out.print(result);
            }
            System.out.println();
        }
    }

    public static void partition(List<ArrayList<Integer>> output, int n, int max, ArrayList<Integer> current)
    {
        if (current == null)
        {
            partition(output, n, max, new ArrayList<Integer>());
            return;
        } else if (n <= 0)
        {
            if (! current.isEmpty())
            {
                output.add(current);
            }
            return;
        } else
        {
            for (int i = Math.min(max, n); i >= 1; i--)
            {
                ArrayList<Integer> temp = (ArrayList<Integer>) current.clone();
                temp.add(i);
                partition(output, n - i, i, temp);
            }
        }
    }
}