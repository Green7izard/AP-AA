public class Main
{
    public enum Level
    {
        OneStar, ElevenStars
    }

    public static void main(String[] args)
    {
        int size = 7;
        ChessBoard chessBoard = new ChessBoard(size);

        BackTracking bf = new BackTracking();
        bf.findSolution(size, chessBoard);
    }
}