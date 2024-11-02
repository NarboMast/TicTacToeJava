import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JNImethodsTest {
    private JNImethods jniMethods;

    @BeforeAll
    public static void loadLibrary() {
        System.loadLibrary("libTicTacToeC");
    }

    @BeforeEach
    public void setUp() {
        jniMethods = new JNImethods();
        jniMethods.setBoard(5);
    }

    @Test
    public void setValidBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(' ', jniMethods.getValue(i, j), "New board must be empty");
            }
        }
    }

//    @Test
//    public void setInValidBoard() {
//        jniMethods.makeMove(3,3);
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                assertEquals(' ', jniMethods.getValue(i, j), "New board must be empty");
//            }
//        }
//    }

    @Test
    public void testMakeValidMove() {
        jniMethods.makeMove(1, 1);
        assertEquals('X', jniMethods.getValue(1, 1), "Cell must be marked after the move");
    }

    @Test
    public void testMakeInvalidMove() {
        jniMethods.makeMove(1, 1);
        jniMethods.makeMove(1, 1);
        assertNotEquals('O', jniMethods.getValue(1, 1), "Non-empty cell cannot be changed");
    }

    @Test
    public void testCheckWin() {
        jniMethods.makeMove(0, 0);
        jniMethods.makeMove(1, 0);
        jniMethods.makeMove(0, 1);
        jniMethods.makeMove(1, 1);
        jniMethods.makeMove(0, 2);
        jniMethods.makeMove(2, 2);
        jniMethods.makeMove(0, 3);
        jniMethods.makeMove(3, 3);
        jniMethods.makeMove(0, 4);

        assertTrue(jniMethods.checkWin('X'), "X is supposed to win the game");
    }

    @Test
    public void testCheckDraw() {
        jniMethods.makeMove(0, 0);
        jniMethods.makeMove(0, 1);
        jniMethods.makeMove(0, 2);
        jniMethods.makeMove(0, 3);
        jniMethods.makeMove(0, 4);

        jniMethods.makeMove(1, 0);
        jniMethods.makeMove(1, 1);
        jniMethods.makeMove(1, 2);
        jniMethods.makeMove(1, 3);
        jniMethods.makeMove(1, 4);

        jniMethods.makeMove(2, 0);
        jniMethods.makeMove(2, 1);
        jniMethods.makeMove(2, 2);
        jniMethods.makeMove(2, 3);
        jniMethods.makeMove(2, 4);

        jniMethods.makeMove(3, 0);
        jniMethods.makeMove(3, 1);
        jniMethods.makeMove(3, 2);
        jniMethods.makeMove(3, 3);
        jniMethods.makeMove(3, 4);

        jniMethods.makeMove(4, 0);
        jniMethods.makeMove(4, 1);
        jniMethods.makeMove(4, 2);
        jniMethods.makeMove(4, 3);
        jniMethods.makeMove(4, 4);

        assertTrue(jniMethods.checkDraw(), "The game should be a draw");
    }

    @Test
    public void testDestroyBoard() {
        jniMethods.destroyBoard();
        assertDoesNotThrow(() -> jniMethods.setBoard(3), "Should allow reinitializing after destroying the board");
    }

    @Test
    public void capture(){
        jniMethods.makeMove(0, 0);
        jniMethods.capture(0, 0);
        assertEquals('O', jniMethods.getValue(0, 0), "Cell must be captured");
    }
}
