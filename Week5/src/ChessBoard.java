/**
 * Created by Remco on 25-11-2015.
 */
public class ChessBoard
{
    int size;
    ChessSquare[][] board;

    ChessBoard(int newSize)
    {
        size = newSize;
        board = new ChessSquare[size][size];
        createBord();
    }

    void createBord()
    {
        for (int row = 0; row < size; row++)
        {
            for (int column = 0; column < size; column++)
            {
                board[row][column] = new ChessSquare();
            }
        }
    }

    void setQueenValue(int row, int column, boolean hasQueen)
    {
        board[row][column].setQueen(hasQueen);
    }

    boolean isQueen(int row, int column)
    {
        return board[row][column].doesHaveQueen();
    }

    boolean isValidValue(int row, int column, boolean value)
    {
        boolean isValid = true;
        // Rows
        for (int i = 0; i < size; i++)
        {
            if (isQueen(row, i))
            {
                return false;
            }
        }
        //Columns
        for (int i = 0; i < size; i++)
        {
            if (isQueen(i, column))
            {
                return false;
            }
        }
        //Diagonals
        return isDiagonalValid(row, column);
    }

    boolean isDiagonalValid(int row, int column)
    {
        int rowI = row;
        int columnI = column;
        while(rowI >= 0 && columnI >= 0)
        {
            if (isQueen(rowI, columnI))
            {
                return false;
            }
            rowI --;
            columnI --;
        }

        rowI = row;
        columnI = column;
        while(rowI < size && columnI < size)
        {
            if (isQueen(rowI, columnI))
            {
                return false;
            }
            rowI ++;
            columnI ++;
        }

        rowI = row;
        columnI = column;
        while(rowI >= 0 && columnI < size)
        {
            if (isQueen(rowI, columnI))
            {
                return false;
            }
            rowI --;
            columnI ++;
        }

        rowI = row;
        columnI = column;
        while(rowI < size && columnI >= 0)
        {
            if (isQueen(rowI, columnI))
            {
                return false;
            }
            rowI ++;
            columnI --;
        }
        return true;
    }

    boolean rowContainsQueen(int row)
    {
        for (int i = 0; i < size; i++)
        {
            if (isQueen(row, i))
            {
                return true;
            }
        }
        return false;
    }

    void printValues()
    {
        int rowCounter = 0;
        int columnCounter = 0;

        for (int row = 0; row < size; row++)
        {
            System.out.print("+ - + - + - + - + - + - + - + - +\n");
            System.out.print("|");

            for (int column = 0; column < size; column++)
            {
                if (!isQueen(row, column))
                {
                    System.out.print("   ");
                } else
                {
                    System.out.print(" Q ");
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
        System.out.print("+ - + - + - + - + - + - + - + - +\n\n");
    }
}