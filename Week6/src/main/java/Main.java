import java.util.List;

public class Main
{

    public static void main(String[] args)
    {
        List<RightDown> solution;
        int testBoard[][] =
                {
                        {0, 3, 5, 7, 8, 7},
                        {5, 5, 9, 2, 7, 3},
                        {3, 4, 2, 7, 3, 2},
                        {6, 3, 4, 3, 6, 7},
                        {3, 8, 7, 2, 4, 4},
                        {2, 9, 8, 7, 8, 6},
                        {1, 4, 3, 8, 5, 0}
                };
        Board tester = new Board(testBoard);
        HillClimber climber = new HillClimber(tester);
        tester.printBoard();
        solution = climber.solveBoard(5);
        System.out.println("Best is: " + solution);
        System.out.println("It has a length of: " + tester.getLengthForSequence(solution));

        System.out.println("\n------------------------------------------------------------------------\n");

        Board board = new Board(8, 8, 10);
        board.printBoard();
        HillClimber hillClimber = new HillClimber(board);
        solution = hillClimber.solveBoard(5);
        System.out.println("Best is: " + solution);
        System.out.println("It has a length of: " + board.getLengthForSequence(solution));
    }


}
