public class BruteForce {

    public static void main(String[] args) {
        SudokuBord sb = new SudokuBord();
        sb.setValue(1,1,7);
        sb.setValue(1,3,6);
        sb.setValue(1,5,5);
        sb.setValue(1,7,3);
        sb.setValue(1,9,8);
        sb.setValue(2,3,3);
        sb.setValue(2,7,2);
        sb.setValue(3,2,1);
        sb.setValue(3,5,9);
        sb.setValue(3,8,4);
        sb.setValue(4,1,6);
        sb.setValue(4,4,3);
        sb.setValue(4,6,1);
        sb.setValue(4,9,4);
        sb.setValue(5,3,1);
        sb.setValue(5,7,5);
        sb.setValue(6,1,3);
        sb.setValue(6,4,4);
        sb.setValue(6,6,5);
        sb.setValue(6,9,9);
        sb.setValue(7,2,6);
        sb.setValue(7,5,8);
        sb.setValue(7,8,1);
        sb.setValue(8,3,7);
        sb.setValue(8,7,9);
        sb.setValue(9,1,2);
        sb.setValue(9,3,4);
        sb.setValue(9,5,1);
        sb.setValue(9,7,8);
        sb.setValue(9,9,6);
        sb.printValues();
    }
}
