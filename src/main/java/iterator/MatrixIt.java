package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
//        while (row < data.length) {
//            if (data[row].length != 0 && column + 1 < data[row].length) {
//                column++;
//                return true;
//            } else {
//                column = 0;
//                row++;
//            }
//        }

        while (row < data.length) {
            if (column < data[row].length) {
                return true;
            } else {
                column = 0;
                row++;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}