public class Main
{
    public enum Level
    {
        OneStar, ElevenStars
    }

    public static void main(String[] args)
    {
        int size = 9;
        SudokuBord bordOneStar = new SudokuBord(size);
        setStartValues(bordOneStar, Level.OneStar);


        SudokuBord bordElevenStars = new SudokuBord(size);
        setStartValues(bordElevenStars, Level.ElevenStars);

        BruteForce bf = new BruteForce();
        System.out.println("1 Star:");
        bf.findSolution(size, bordOneStar);

        System.out.println("11 Stars:");
        bf.findSolution(size, bordElevenStars);
    }

    static void setStartValues(SudokuBord bord, Level level)
    {
        switch (level)
        {
            //1 STAR
            case OneStar:
                bord.setValue(0, 0, 6, true);
                bord.setValue(0, 1, 4, true);
                bord.setValue(2, 0, 2, true);

                bord.setValue(3, 2, 4, true);
                bord.setValue(4, 0, 5, true);
                bord.setValue(4, 1, 9, true);

                bord.setValue(6, 0, 3, true);
                bord.setValue(7, 1, 8, true);
                bord.setValue(6, 2, 1, true);

                bord.setValue(2, 3, 6, true);
                bord.setValue(1, 4, 8, true);
                bord.setValue(2, 5, 5, true);

                bord.setValue(5, 5, 2, true);

                bord.setValue(6, 4, 9, true);
                bord.setValue(7, 4, 5, true);
                bord.setValue(8, 5, 8, true);

                bord.setValue(1, 8, 7, true);
                bord.setValue(2, 8, 3, true);

                bord.setValue(3, 6, 7, true);
                bord.setValue(3, 8, 6, true);
                bord.setValue(4, 7, 2, true);
                bord.setValue(4, 8, 4, true);
                bord.setValue(5, 6, 9, true);

                bord.setValue(6, 7, 4, true);
                bord.setValue(6, 8, 5, true);
                bord.setValue(7, 7, 9, true);
                bord.setValue(7, 8, 2, true);
                bord.setValue(8, 6, 3, true);
                break;

                // 11 STARS - http://www.nieuwsblad.be/cnt/dmf20120629_074
            case ElevenStars:

                bord.setValue(0, 0, 8, true);
                bord.setValue(1, 2, 3, true);
                bord.setValue(2, 1, 7, true);

                bord.setValue(3, 1, 5, true);

                bord.setValue(6, 2, 1, true);
                bord.setValue(7, 2, 8, true);
                bord.setValue(8, 1, 9, true);

                bord.setValue(1, 3, 6, true);
                bord.setValue(2, 4, 9, true);


                bord.setValue(3, 5, 7, true);
                bord.setValue(4, 4, 4, true);
                bord.setValue(4, 5, 5, true);
                bord.setValue(5, 3, 1, true);

                bord.setValue(7, 3, 5, true);

                bord.setValue(2, 6, 2, true);

                bord.setValue(4, 6, 7, true);
                bord.setValue(5, 7, 3, true);

                bord.setValue(6, 7, 6, true);
                bord.setValue(6, 8, 8, true);
                bord.setValue(7, 7, 1, true);
                bord.setValue(8, 6, 4, true);
                break;
        }
    }
}