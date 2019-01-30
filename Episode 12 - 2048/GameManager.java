import java.util.Scanner;

class GameManager {

    private final int SIDE_LENGTH = 4;
    private enum Direction {LEFT, UP, RIGHT, DOWN}
    private Scanner sc;
    private int[][] _field;

    /**
    * Constructor to create an instance of the playing field.
    */
    public GameManager() {
        sc = new Scanner(System.in);
        _field = new int[SIDE_LENGTH][SIDE_LENGTH];
    }

    /**
    * Reads in SIDE_LENGTH x SIDE_LENGTH amount of user input, filling row by row.
    */
    public void readFieldState() {
        for (int r = 0; r < SIDE_LENGTH; r++) {
            for (int c = 0; c < SIDE_LENGTH; c++) {
                _field[r][c] = sc.nextInt();
            }
        }
    }

    /**
    * Reads in a user input of 0-3 which indicates the direction the field is shifting.
    * See enum Direction above for the values.
    * Groups horizontal shifts and vertical shifts together.
    */
    public void playerAction() {
        Direction action = Direction.values()[sc.nextInt()];
        if (action == Direction.LEFT || action == Direction.RIGHT) evaluateHorizontal(action);
        else if (action == Direction.UP || action == Direction.DOWN) evaluateVertical(action);
        else return;
    }

    /**
    * Evaluate the board starting from the left or from the right, depending on
    * direction input. Either way, the field will be evaluated row-wise.
    * a) if evaluating left, start from column 0 and loop increasingly to SIDE_LENGTH - 1.
    * b) if evaluating right, start from column SIDE_LENGTH - 1 and loop decreasingly to 0.
    * 1) Flush the current row (i.e moving all the 0s to the other end).
    * 2) Still on the current row, look through each element and the element next to it
    *         column-wise. (either left or right, depending on direction).
    *         Merge the two elements if they match.
    *         Do this for all elements in the current row.
    * 3) flush the row again according to the direction.
    * Repeat steps 1) to 3) for each row in the field.
    */
    private void evaluateHorizontal(Direction direction) {
        for (int row = 0; row < SIDE_LENGTH; row++) {
            if (direction == Direction.LEFT) {
                flush(_field[row], 0); //flush all 0s to the right
                for (int col = 0; col < SIDE_LENGTH - 1; col++) {
                    int[] one = {row, col};
                    int[] two = {row, col + 1};
                    checkSwap(one, two);
                }
                flush(_field[row], 0); //flush all 0s to the right again
            } else {
                flush(_field[row], SIDE_LENGTH - 1); //flush all 0s to the left
                for (int col = SIDE_LENGTH - 1; col > 0; col--) {
                    int[] one = {row, col};
                    int[] two = {row, col - 1};
                    checkSwap(one, two);
                }
                flush(_field[row], SIDE_LENGTH - 1); //flush all 0s to the left again
            }
        }
    }

    /**
    * Evaluate the board starting from the top or from the bottom, depending on
    * direction input. Either way, the field will be evaluated column-wise.
    * a) if evaluating top, start from row 0 and loop increasingly to SIDE_LENGTH - 1.
    * b) if evaluating bottom, start from row SIDE_LENGTH - 1 and loop decreasingly to 0.
    * 1) Flush the current column (i.e moving all the 0s to the other end).
    * 2) Still on the current column, look through each element and the element next to it
    *         row-wise. (either top or bottom, depending on direction).
    *         Merge the two elements if they match.
    *         Do this for all elements in the current column.
    * 3) flush the column again according to the direction.
    * Repeat steps 1) to 3) for each column in the field.
    */
    private void evaluateVertical(Direction direction) {
        for (int col = 0; col < SIDE_LENGTH; col++) {
            int[] temp = getColArr(col); //necessary buffer to obtain the column array.
            if (direction == Direction.UP) {
                flush(temp, 0); //flush all 0s to the bottom
                setColArr(temp, col);
                for (int row = 0; row < SIDE_LENGTH - 1; row++) {
                    int[] one = {row, col};
                    int[] two = {row + 1, col};
                    checkSwap(one, two);
                }
                temp = getColArr(col); //flush all 0s to the bottom again
                flush(temp, 0);
            } else {
                flush(temp, SIDE_LENGTH - 1); //flush all 0s to the top
                setColArr(temp, col);
                for (int row = SIDE_LENGTH - 1; row > 0; row--) {
                    int[] one = {row, col};
                    int[] two = {row - 1, col};
                    checkSwap(one, two);
                }
                temp = getColArr(col); //flush all 0s to the top again
                flush(temp, SIDE_LENGTH - 1);
            }
            setColArr(temp, col); //update field with updated buffer
        }
    }

    /**
    * Auxiliary method to get the specified column as an int[] array.
    * @param col - the column index to get the array of
    * @return int[] of the specified column index
    */
    private int[] getColArr(int col) {
        int[] temp = new int[SIDE_LENGTH];
        for (int i = 0; i < SIDE_LENGTH; i++) {
            temp[i] = _field[i][col];
        }
        return temp;
    }

    /**
    * Auxiliary method to set a specified column with input int[] array.
    * @param input - array used to replace the specified column index
    * @param col - column index to replace
    */
    private void setColArr(int[] input, int col) {
        for (int i = 0; i < SIDE_LENGTH; i++) {
            _field[i][col] = input[i];
        }
    }

    /**
    * Flush all the 0s in the input int[] array from input starting index.
    * Either increment until index reaches SIDE_LENGTH or decrement until index reaches 0.
    * @param arr - Input array to flush
    * @param startIndex - starting index to flush away from. Usually contains 0 or SIDE_LENGTH - 1;
    */
    private void flush(int[] arr, int startIndex) {
        int count = startIndex; //for keeping track of current output index
        if (startIndex > 0) { //if flushing to front
            for (int i = startIndex; i >= 0; i--) { //for each index, if element != 0,
                if (arr[i] != 0) arr[count--] = arr[i]; //write to the current output index
            }
            while (count >= 0) arr[count--] = 0; //pad arr with 0 until whole arr is passed
        } else { //if flushing to back
            for (int i = 0; i < SIDE_LENGTH; i++) {     //for each index, if element != 0,
                if (arr[i] != 0) arr[count++] = arr[i]; //write to the current output index
            }
            while (count < SIDE_LENGTH) arr[count++] = 0; //pad arr with 0 until whole arr is passed
        }
    }

    /**
    * Given two consecutive elements, check if they match. If they do, double the
    * first element and set the second element to 0 to "merge" them.
    * @param one - first element in the form of int[2] which contains int[0] = row, int[1] = col
    * @param two - second element in the form of int[2] which contains int[0] = row, int[1] = col
    */
    private void checkSwap(int[] one, int[] two) {
        if (_field[one[0]][one[1]] == _field[two[0]][two[1]]) {
             //"merge" the two matching tiles and store in spot one
            _field[one[0]][one[1]] *= 2;
            _field[two[0]][two[1]] = 0;
        }
    }

    /**
    * Formats the field into the required String format.
    */
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("\n");
        for (int row = 0; row < SIDE_LENGTH; row++) {
            for (int col = 0; col < SIDE_LENGTH; col++) {
                output.append(_field[row][col] + " ");
            }
            output.append("\n");
        }
        return output.substring(0, output.lastIndexOf("\n")).toString();
    }
}
