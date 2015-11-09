import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NumberPartitions {

    private static final int NUMBER =10;

    public static void main(String[] args) {
        int number;
        try{
            number = Integer.parseInt(args[0]);
        }catch(Exception e){
            number=NUMBER;
        }
        System.out.println("Running with number: " + number);

        ArrayList< int[] > possibilities = new ArrayList< int[] >(number);

        partition(number, number, possibilities, null);

        //PRINT
        for(int[] results: possibilities)
        {
            boolean first = true;
            for(int result: results)
            {
                if(!first) {
                    System.out.print("+");
                }
                first=false;
                System.out.print(result);
            }
            System.out.println();
        }
    }

    public static void partition(int n, int max, List<int[]> output, ArrayList<Integer> current) {
        if(current==null)
        {
            partition(n, max, output, new ArrayList<Integer>());
            return;
        }
        if (n <= 0) {
            if(!current.isEmpty()){
                output.add(toArray(current));
            }
            return;
        }

        for (int i = Math.min(max, n); i >= 1; i--) {
            ArrayList<Integer> temp = (ArrayList<Integer>)current.clone();
            temp.add(i);
            partition(n - i, i, output, temp);
        }
    }

    public static int[] toArray(List<Integer> numbers)
    {
        int list[] = new int[numbers.size()];
        for(int i = 0; i<numbers.size();i++)
        {
            list[i]=numbers.get(i);
        }
        return list;
    }


}
