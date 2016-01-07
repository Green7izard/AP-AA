/**
 * Created by Remco on 25-11-2015.
 */
public class ChessSquare
{
    boolean hasQueen = false;

    void setQueen(boolean hasQueen)
    {
        this.hasQueen = hasQueen;
    }

    boolean doesHaveQueen()
    {
        return hasQueen;
    }
}