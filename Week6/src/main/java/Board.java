import java.util.List;
import java.util.Random;

/**
 * Created by Bas on 16-12-2015.
 */
public class Board
{
    public static final int DEFAULT_MAX = 10;
    private final int board[/*X*/][/*Y*/];

    public Board(int newBoard[][])
    {
        board = newBoard;
    }

    public Board(int width, int height, int maxValue)
    {
        this(generateBoard(width, height, maxValue));
    }

    public Board(int size)
    {
        this(size, size, DEFAULT_MAX);
    }

    private static int[][] generateBoard(int width, int height, int maxValue)
    {
        int newBoard[][] = new int[width][height];
        Random random = new Random();
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                newBoard[x][y] = random.nextInt(maxValue) + 1;
            }
        }
        newBoard[0][0] = 0;
        newBoard[width - 1][height - 1] = 0;

        return newBoard;
    }

    public int getLengthForSequence(List<RightDown> list)
    {
        try
        {
            int length = 0;
            int x = 0;
            int y = 0;
            for (RightDown step : list)
            {
                if (step != null)
                {
                    if (step == RightDown.RIGHT)
                    {
                        x++;
                    } else if (step == RightDown.DOWN)
                    {
                        y++;
                    }
                    length += getSquare(x, y);
                }
            }
            return length;
        } catch (Exception e)
        {
            return Integer.MAX_VALUE;
        }
    }

    public int getSquare(int x, int y)
    {
        try
        {
            return board[x][y];
        } catch (Exception e)
        {
            return Integer.MAX_VALUE/5;
        }
    }

    public int getHeight()
    {
        if (board.length > 0)
        {
            return board[0].length;
        } else
        {
            return - 1;
        }
    }

    public int getWidth()
    {
        return board.length;
    }

    public int[][] getBoard()
    {
        return board;
    }

    public void printBoard()
    {
        int rowCounter = 0;
        int columnCounter = 0;

        for (int row = 0; row < getHeight(); row++)
        {
            for (int i = 0; i < getWidth(); i++)
            {
                System.out.print("+ - ");
            }
            System.out.println("+");
            System.out.print("|");

            for (int column = 0; column < getWidth(); column++)
            {
                String value = String.valueOf(getSquare(column, row));
                if (value.equals("0"))
                {
                    value = " # ";
                } else if (value.length() == 1)
                {
                    value = " " + value + " ";
                } else if (value.length() == 2)
                {
                    value = " " + value;
                }
                System.out.print(value);
                System.out.print("|");
            }
            System.out.print("\n");
        }
        for (int i = 0; i < getWidth(); i++)
        {
            System.out.print("+ - ");
        }
        System.out.println("+\n");
    }


}
