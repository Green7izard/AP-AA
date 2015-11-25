/**
 * Created by Remco on 25-11-2015.
 */
public class SudokuBord
{
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
        for (int i = 0; i < size; i++)
        {
            blocks[i] = new Block();
        }
    }

    void createBord()
    {
        for (int row = 0; row < size; row++)
        {
            for (int column = 0; column < size; column++)
            {
                bord[row][column] = new NumberBox();
                blocks[getBlock(row, column)].addNumber(bord[row][column]);
            }
        }
    }

    int getBlock(int row, int column)
    {
        int blockNumber;
        if (row < 3)
        {
            if (column < 3)
            {
                blockNumber = 0;
            } else if (column > 5)
            {
                blockNumber = 2;
            } else
            {
                blockNumber = 1;
            }
        } else if (row > 5)
        {
            if (column < 3)
            {
                blockNumber = 6;
            } else if (column > 5)
            {
                blockNumber = 7;
            } else
            {
                blockNumber = 8;
            }
        } else
        {
            if (column < 3)
            {
                blockNumber = 3;
            } else if (column > 5)
            {
                blockNumber = 4;
            } else
            {
                blockNumber = 5;
            }
        }
        return blockNumber;
    }

    void setValue(int row, int column, int value, boolean isStartValue)
    {
        bord[row][column].setValue(value, isStartValue);
    }

    Integer getValue(int row, int column)
    {
        return bord[row][column].getValue();
    }

    boolean isDefaultNumber(int row, int column)
    {
        if (bord[row][column].isStartValue)
        {
            return true;
        }
        return false;
    }

    boolean isValidValue(int row, int column, int value)
    {
        boolean isValid = true;
        for (int i = 0; i < size; i++)
        {
            if (bord[row][i].isEqual(value))
            {
                return false;
            }
        }
        for (int i = 0; i < size; i++)
        {
            if (bord[i][column].isEqual(value))
            {
                return false;
            }
        }
        for (int i = 0; i < size; i++)
        {
            if (blocks[getBlock(row, column)].doesContain(value))
            {
                return false;
            }
        }
        return true;
    }

    void printValues()
    {
        int rowCounter = 0;
        int columnCounter = 0;

        for (int row = 0; row < size; row++)
        {
            if (rowCounter % 3 == 0)
            {
                printNormalText("+ - + - + - + - + - + - + - + - + - +\n");
            } else
            {
                printNormalText("+ ");
                printColoredText("- + - + -");
                printNormalText(" + ");
                printColoredText("- + - + -");
                printNormalText(" + ");
                printColoredText("- + - + -");
                printNormalText(" +\n");
            }
            rowCounter++;
            printNormalText("|");

            for (int column = 0; column < size; column++)
            {
                if (bord[row][column].getValue() == null)
                {
                    printColoredText("   ");
                } else
                {
                    printNormalText(" " + bord[row][column].getValue() + " ");
                }
                if ((columnCounter + 1) % 3 == 0)
                {
                    printNormalText("|");
                } else
                {
                    printColoredText("|");
                }
                columnCounter ++;
            }
            printColoredText("\n");
        }
        printNormalText("+ - + - + - + - + - + - + - + - + - +\n\n");
    }

    void printNormalText(String text)
    {
        System.out.print(text);
    }

    void printColoredText(String text)
    {
        System.out.print("\033[36m" + text + "\033[0m");
    }
}