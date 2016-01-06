/**
 * Created by Remco on 25-11-2015.
 */
public class BruteForce
{

    void findSolution(int size, SudokuBord bord)
    {
        int counterSteps = 0;
        int oldValue;
        int newValue;
        boolean hasNewValue;
        boolean goBack = false;

        bord.printValues();

        for (int rowI = 0; rowI < size; rowI++)
        {
            for (int columnI = 0; columnI < size; columnI++)
            {
                if (! bord.isDefaultNumber(rowI, columnI))
                {
                    hasNewValue = false;
                    goBack = false;
                    if (bord.getValue(rowI, columnI) != null)
                    {
                        oldValue = bord.getValue(rowI, columnI);
                    } else
                    {
                        oldValue = 0;
                    }
                    while (hasNewValue == false)
                    {
                        counterSteps ++;
                        if (oldValue < 9)
                        {
                            newValue = oldValue + 1;
                            if (bord.isValidValue(rowI, columnI, newValue))
                            {
                                bord.setValue(rowI, columnI, newValue, false);
                                hasNewValue = true;

                            } else
                            {
                                oldValue = newValue;
                            }
                        }else if (columnI >= 1)
                        {
                            bord.setValue(rowI, columnI, 0, false);
                            columnI = columnI - 2;
                            goBack = true;
                            break;
                        } else if (rowI >= 1)
                        {
                            bord.setValue(rowI, columnI, 0, false);
                            rowI = rowI - 1;
                            columnI = size - 2;
                            goBack = true;
                            break;
                        }
                    }
                } else if (goBack == true)
                {
                    if (columnI >= 1)
                    {
                        columnI = columnI - 2;
                        goBack = true;
                    } else if (rowI >= 1)
                    {
                        rowI = rowI - 1;
                        columnI = size - 2;
                        goBack = true;
                    } else{
                        rowI = size;
                        columnI = size;
                        System.out.println("This puzzle cannot be solved!!");
                        break;
                    }
                }
            }
        }
        bord.printValues();
        System.out.println("The program calculated it in " + counterSteps + " steps.");
    }
}