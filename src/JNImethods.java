public class JNImethods {
    static {
        System.loadLibrary("libTicTacToeC");
    }

    public native void setBoard(int dimension);
    public native void destroyBoard();
    public native char getValue(int row, int col);
    public native boolean checkWin(char player);
    public native boolean checkDraw();
}