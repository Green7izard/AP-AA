public class Main {

    public static void main(String[] args) {
        int size = 9;
        SudokuBord sb = new SudokuBord(size);
        setStartValues(sb);
        sb.printValues();
        BruteForce bf = new BruteForce();
        bf.findSolution(size, sb);
    }

    static void setStartValues(SudokuBord bord)
    {
        bord.setValue(1,1,7, true);
        bord.setValue(1,3,6, true);
        bord.setValue(1,5,5, true);
        bord.setValue(1,7,3, true);
        bord.setValue(1,9,8, true);
        bord.setValue(2,3,3, true);
        bord.setValue(2,7,2, true);
        bord.setValue(3,2,1, true);
        bord.setValue(3,5,9, true);
        bord.setValue(3,8,4, true);
        bord.setValue(4,1,6, true);
        bord.setValue(4,4,3, true);
        bord.setValue(4,6,1, true);
        bord.setValue(4,9,4, true);
        bord.setValue(5,3,1, true);
        bord.setValue(5,7,5, true);
        bord.setValue(6,1,3, true);
        bord.setValue(6,4,4, true);
        bord.setValue(6,6,5, true);
        bord.setValue(6,9,9, true);
        bord.setValue(7,2,6, true);
        bord.setValue(7,5,8, true);
        bord.setValue(7,8,1, true);
        bord.setValue(8,3,7, true);
        bord.setValue(8,7,9, true);
        bord.setValue(9,1,2, true);
        bord.setValue(9,3,4, true);
        bord.setValue(9,5,1, true);
        bord.setValue(9,7,8, true);
        bord.setValue(9,9,6, true);
    }
}