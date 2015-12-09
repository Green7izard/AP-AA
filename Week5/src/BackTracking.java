/**
 * Created by Remco on 25-11-2015.
 */
public class BackTracking
{
    /*
    Terug tot een queen en set to false
    Set new Queen in row
    [Kan niet] Naar vorige row

    zoek laatste queen en set to false
    Set new Queen in row
    [Kan niet] Naar vorige row
     */
    void findSolution(int size, ChessBoard bord)
    {
        boolean goBack = false;

        ChessSquare[] queens = new ChessSquare[size];
        for (int rowI = 0; rowI < size; rowI++)
        {
            for (int columnI = 0; columnI < size; columnI++)
            {
                if (!goBack && bord.isValidValue(rowI, columnI, true))
                {
                    bord.setQueenValue(rowI, columnI, true);
                    goBack = false;
                    break;
                }
                else if(goBack && bord.isQueen(rowI, columnI))
                {
                    bord.setQueenValue(rowI, columnI, false);
                    goBack = false;
                }

                if(columnI >= size - 1)
                {
                    if(!bord.rowContainsQueen(rowI))
                    {
                        goBack = true;
                    }
                }
                if(goBack)
                {
                    columnI -= 2;
                    if(columnI < 0)
                    {
                        columnI = size -2;
                        rowI -= 1;
                    }
                }
            }
        }
        bord.printValues();
    }
}