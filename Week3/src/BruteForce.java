/**
 * Created by Remco on 25-11-2015.
 */
public class BruteForce {

    void findSolution(int size, SudokuBord bord) {
        int value;

        for (int rowI = 0; rowI < size; rowI++) {
            for (int columnI = 0; columnI < size; columnI++) {
                value = bord.getValue(rowI, columnI);
                if (value != 9) {
                    if (bord.isValidValue(rowI, columnI, value)) {
                        bord.increaseValue(rowI, columnI);
                    } else {
                        if (value < 9) {
                            value++;
                            columnI--;
                        } else if (value == 9 && columnI > 1) {
                            columnI--;
                        } else {
                            rowI--;
                        }
                    }
                }
            }
        }
    }
}
