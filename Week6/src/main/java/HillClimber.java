import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Bas on 16-12-2015.
 */
public class HillClimber
{
    private final Board board;

    public HillClimber(Board board)
    {
        this.board = board;
    }

    private List<RightDown> generateRandomSolution(Board board)
    {
        List<RightDown> solution = new ArrayList<>(board.getHeight() + board.getWidth());
        int remainingR = board.getWidth() - 1;
        int remainingD = board.getHeight() - 1;
        Random random = new Random();
        while (remainingD > 0 || remainingR > 0)
        {
            if (remainingR <= 0)
            {
                solution.add(RightDown.DOWN);
                remainingD--;
            } else if (remainingD <= 0)
            {
                solution.add(RightDown.RIGHT);
                remainingR--;
            } else
            {
                if (random.nextBoolean())
                {
                    solution.add(RightDown.RIGHT);
                    remainingR--;
                } else
                {
                    solution.add(RightDown.DOWN);
                    remainingD--;
                }
            }
        }
        return solution;
    }

    public List<RightDown> solveBoard(int iteration)
    {
        List<RightDown> currentSolution = generateRandomSolution(board);
        System.out.println("Starting with: " + currentSolution + " with a length of: " + board.getLengthForSequence(currentSolution));
        for (int it = 0; it < iteration; it++)
        {
            for (int j = 0; j < currentSolution.size() - 1; j++)
            {
                if (currentSolution.get(j) != currentSolution.get(j + 1))
                {
                    List<RightDown> switchSolution = new ArrayList<RightDown>(currentSolution);
                    Collections.swap(switchSolution, j, j + 1);
                    if (board.getLengthForSequence(switchSolution) < board.getLengthForSequence(currentSolution))
                    {
                        currentSolution = switchSolution;
                        System.out.println("New Solution:  " + currentSolution + " with a length of: " + board.getLengthForSequence(currentSolution));
                    }
                }
            }
        }
        return currentSolution;
    }


}
