import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel implements Settings{
    private final JNImethods jniMethods;
    private final Icons icons;
    private final JButton[][] buttons;
    private int highlightedRow = 0;
    private int highlightedCol = 0;
    private boolean keyListenerEnabled = false;

    public Board() {
        setLayout(new GridLayout(dimension,dimension));

        jniMethods = new JNImethods();
        icons = new Icons();
        buttons = new JButton[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                JButton button = getjButton(i, j);
                buttons[i][j] = button;
                add(button);
            }
        }

        jniMethods.setBoard(dimension);
    }

    private JButton getjButton(int i, int j) {
        JButton button = new JButton();
        button.setBackground(Color.white);

        final int row = i;
        final int col = j;

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(row, col);
            }
        });

        button.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_A) {
                    keyListenerEnabled = !keyListenerEnabled;
                    moveHighlightButton(highlightedRow, highlightedCol);
                }
                handleKeyPress(e);
            }
        });

        return button;
    }

    private void handleClick(int row, int col) {
        jniMethods.makeMove(row, col);

        moveHighlightButton(row, col);
        highlightedRow = row;
        highlightedCol = col;
        keyListenerEnabled = false;
        refreshBoard();
        checkGameState(row, col);
    }


    private void handleKeyPress(KeyEvent e) {
        if(keyListenerEnabled) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (highlightedRow == 0) {
                        moveHighlightButton(dimension - 1, highlightedCol);
                    } else {
                        moveHighlightButton(highlightedRow - 1, highlightedCol);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (highlightedRow == dimension - 1) {
                        moveHighlightButton(0, highlightedCol);
                    } else {
                        moveHighlightButton(highlightedRow + 1, highlightedCol);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (highlightedCol == 0) {
                        moveHighlightButton(highlightedRow, dimension - 1);
                    } else {
                        moveHighlightButton(highlightedRow, highlightedCol - 1);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (highlightedCol == dimension - 1) {
                        moveHighlightButton(highlightedRow, 0);
                    } else {
                        moveHighlightButton(highlightedRow, highlightedCol + 1);
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    jniMethods.makeMove(highlightedRow, highlightedCol);
                    refreshBoard();
                    checkGameState(highlightedRow, highlightedCol);
                    break;
            }
        }
    }

    private void checkGameState(int row, int col) {
        if (jniMethods.checkWin(jniMethods.getValue(row, col))) {
            System.out.println(jniMethods.getValue(row, col) + " wins");
            jniMethods.destroyBoard();
            System.exit(0);
        }

        if (jniMethods.checkDraw()) {
            System.out.println("Draw");
            jniMethods.destroyBoard();
            System.exit(0);
        }
    }

    private void refreshBoard() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                buttons[i][j].setIcon(icons.getIcon(jniMethods.getValue(i, j)));
            }
        }
    }

    private void highlightButton(int row, int col) {
        buttons[row][col].setBackground(Color.GRAY);
        buttons[row][col].requestFocus();
    }

    private void moveHighlightButton(int nRow, int nCol) {
        buttons[highlightedRow][highlightedCol].setBackground(Color.white);
        highlightedRow = nRow;
        highlightedCol = nCol;
        highlightButton(highlightedRow, highlightedCol);
    }
}
