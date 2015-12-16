public class Main
{

    public static void main(String[] args)
    {
        Board board = new Board(5, 5, 10);
        board.printBoard();
        HillClimber hillClimber = new HillClimber(board);
        hillClimber.solveBoard(10);
    }


}
