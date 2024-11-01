import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel implements Settings{
    private final JNImethods jniMethods;
    private final Icons icons;
    private boolean keyListenerEnabled = false;
    private int highlightedRow = 0;
    private int highlightedCol = 0;

    public Board() {
        setLayout(new GridLayout(dimension, dimension));
        jniMethods = new JNImethods();
        icons = new Icons();
        initializeBoard();
        jniMethods.setBoard(dimension);
    }

    private void initializeBoard() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                JButton button = createButton(i, j);
                add(button);
            }
        }
    }

    private JButton createButton(int row, int col) {
        JButton button = new JButton();
        button.setBackground(Color.white);

        button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                handleClick(row, col);
            }
        });

        button.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    keyListenerEnabled = !keyListenerEnabled;
                }
                handleKeyPress(e);
            }
        });

        return button;
    }

    private void handleClick(int row, int col) {
        jniMethods.makeMove(row, col);
        keyListenerEnabled = false;
        highlightedRow = row;
        highlightedCol = col;
        refreshBoard();
        checkGameState(row, col);
    }

    private void handleKeyPress(KeyEvent e) {
        if (keyListenerEnabled) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (highlightedRow == 0) {
                        highlightButton(dimension, highlightedCol);
                    } else {
                        highlightButton(highlightedRow - 1, highlightedCol);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (highlightedRow == dimension - 1) {
                        highlightButton(0, highlightedCol);
                    } else {
                        highlightButton(highlightedRow + 1, highlightedCol);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (highlightedCol == 0) {
                        highlightButton(highlightedRow, dimension - 1);
                    } else {
                        highlightButton(highlightedRow, highlightedCol - 1);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (highlightedCol == dimension - 1) {
                        highlightButton(highlightedRow, 0);
                    } else {
                        highlightButton(highlightedRow, highlightedCol + 1);
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
        Component[] components = getComponents();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                JButton button = (JButton)components[i * dimension + j];
                button.setIcon(icons.getIcon(jniMethods.getValue(i, j)));
                button.setBackground(Color.white);
            }
        }
    }

//    private void highlightButton(JButton button) {
//        button.setBackground(Color.GRAY);
//        button.requestFocus();
//    }

    private void highlightButton(int row, int col) {
        Component[] components = getComponents();
        JButton fromButton = (JButton)components[highlightedRow * dimension + highlightedCol];
        fromButton.setBackground(Color.white);
        highlightedRow = row;
        highlightedCol = col;
        JButton toButton = (JButton)components[highlightedRow * dimension + highlightedCol];
        toButton.setBackground(Color.GRAY);
        toButton.requestFocus();
    }
}
