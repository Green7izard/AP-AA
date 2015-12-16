public class Main
{

    public static void main(String[] args)
    {
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
        climber.solveBoard(40);

        System.out.println("\n---------------------------------\n");

        Board board = new Board(8, 8, 10);
        board.printBoard();
        HillClimber hillClimber = new HillClimber(board);
        hillClimber.solveBoard(10);
    }


}
