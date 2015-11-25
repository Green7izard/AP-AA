/**
 * Created by Remco on 25-11-2015.
 */
public class SudokuBord {
    int size;
    NumberBox[][] bord;

    SudokuBord()
    {
        size = 9;
        bord = new NumberBox[size][size];
        createBord();
    }

    void createBord(){
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++)
            {
                bord[row][column] = new NumberBox();
            }
        }
    }

    void setValue(int row, int column, int value)
    {
        bord[row - 1][column - 1].setValue(value);
    }

    void printValues(){
        for(int row = 0; row < size; row++){

            System.out.print("+ - + - + - + - + - + - + - + - + - +\n");
            System.out.print("| ");

            for(int column = 0; column < size; column++){
                System.out.print("" + bord[row][column].getValue());
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
        System.out.print("+ - + - + - + - + - + - + - + - + - +");
    }
}