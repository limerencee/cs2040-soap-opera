class Square {

    private int[][] _tiles;
    private int _N;

    /**
    * Constructor to initialize N x N 2D array.
    * @param N - Must be an odd integer. The length of row/col.
    */
    public Square(int N) {
        _tiles = new int[N][N];
        _N = N;
    }

    /**
    * Generate the magic square starting from row 1 and column N/2.
    * Follows the 2 rules:
    * 1) If the tile[row-1][col+1] currently has no element (== 0)
    * 2) If 1) is false, insert the new element at tile[row+1][col] instead.
    */
    public void generate() {
        int row = 0, col = _N/2;
        int counter = 1;
        while (counter <= Math.pow(_N, 2)) {
            _tiles[row][col] = counter++;
            int nextRow = Math.floorMod((row - 1), _N); //wrap-around
            int nextCol =  Math.floorMod((col + 1), _N); //wrap-around
            if (_tiles[nextRow][nextCol] != 0) { //if top-right is filled
                nextRow = Math.floorMod((row + 1), _N); //fill the bottom tile instead
                nextCol = col;
            }
            row = nextRow;
            col = nextCol;
        }
    }


    /**
    * Prints out the Magic Square in a read-able format.
    * @return Read-able magic square tiles.
    */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < _N; i++) {
            for (int j = 0; j < _N; j++) {
                sb.append(String.format("%5d", _tiles[i][j]));
            }
            sb.append("\n");
        }
        return sb.substring(0, sb.lastIndexOf("\n")).toString();
    }

}
