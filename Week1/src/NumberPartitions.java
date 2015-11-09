import java.util.ArrayList;
import java.util.List;

public class NumberPartitions {

    private static final int NUMBER =5;

    public static void main(String[] args) {
        int number;
        try{
            number = Integer.parseInt(args[0]);
        }catch(Exception e){
            number=NUMBER;
        }
        System.out.println("Running with number: "+number);

        ArrayList< ArrayList<Integer> > possiblilities = new ArrayList< ArrayList<Integer> >(number);

        ArrayList<Integer> base = new ArrayList<Integer>(number);
        for(int counter=0; counter<number; counter++)
        {
            base.add(1);
        }
        possiblilities.add(base);



        //PRINT
        for(ArrayList<Integer> results: possiblilities)
        {
            boolean first = true;
            for(Integer result: results)
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



}
