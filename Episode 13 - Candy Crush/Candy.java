class Candy {
    private int _row;
    private int _col;
    private int _type;

    public Candy(int type, int row, int col) {
        _row = row;
        _col = col;
        _type = type;
    }

    public int getRow() {
        return _row;
    }

    public int getCol() {
        return _col;
    }

    public int getType() {
        return _type;
    }
}
