/**
 * Created by Remco on 25-11-2015.
 */
public class SudokuBord {
    int size;
    NumberBox[][] bord;
    Block[] blocks;

    SudokuBord(int newSize)
    {
        size = newSize;
        bord = new NumberBox[size][size];
        blocks = new Block[size];
        createBlocks();
        createBord();
    }

    void createBlocks()
    {
        for(int i = 0; i < size; i++)
        {
            blocks[i] = new Block();
        }
    }

    void createBord(){
        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++)
            {
                int blockNumber = 0;
                bord[row][column] = new NumberBox();
                if(row < 3)
                {
                    if(column < 3)
                    {
                        blockNumber = 0;
                    }
                    if(column > 5)
                    {
                        blockNumber = 2;
                    }
                    else
                    {
                        blockNumber = 1;
                    }
                }
                if(row > 5)
                {
                    if(column < 3)
                    {
                        blockNumber = 6;
                    }
                    if(column > 5)
                    {
                        blockNumber = 7;
                    }
                    else
                    {
                        blockNumber = 8;
                    }
                }
                else
                {
                    if(column < 3)
                    {
                        blockNumber = 3;
                    }
                    if(column > 5)
                    {
                        blockNumber = 4;
                    }
                    else
                    {
                        blockNumber = 5;
                    }
                }
                blocks[blockNumber].addNumber(bord[row][column]);
            }
        }
    }

    void setValue(int row, int column, int value, boolean isStartValue)
    {
        bord[row - 1][column - 1].setValue(value, isStartValue);
    }

    int getValue(int row, int column)
    {
        return bord[row][column].getValue();
    }

    void increaseValue(int row, int column)
    {
        bord[row - 1][column - 1].increaseValue();
    }

    boolean isValidValue(int row, int column, int value)
    {
        boolean isValid = true;
        if(bord[row][column].isStartValue)
        {
            isValid = false;
        }
        for(int i = 0; i < size; i++)
        {
            if (bord[row][i].isEqual(value))
            {
                isValid = false;
                break;
            }
        }
        for(int i = 0; i < size; i++)
            if(bord[i][column].isEqual(value))
            {
                isValid = false;
                break;
            }
        for(int i = 0; i < size; i++)
            if(blocks[i].doesContain(value))
            {
                isValid = false;
                break;
            }
        return isValid;
    }

    void printValues(){
        for(int row = 0; row < size; row++){

            System.out.print("+ - + - + - + - + - + - + - + - + - +\n");
            System.out.print("|");

            for(int column = 0; column < size; column++){
                if(bord[row][column].getValue() == null)
                {
                    System.out.print("   ");
                }
                else
                {
                    System.out.print(" " + bord[row][column].getValue() + " ");
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
        System.out.print("+ - + - + - + - + - + - + - + - + - +");
    }
}