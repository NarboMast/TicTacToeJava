import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel implements Settings{
    private final JNImethods jniMethods;
    private final Icons icons;
    private final JButton[][] buttons;

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
        return button;
    }

    void handleClick(int row, int col) {
        char player = jniMethods.getValue(row, col);
        buttons[row][col].setIcon(icons.getIcon(player));

        if (jniMethods.checkWin(player)) {
            System.out.println(player + " wins");
            jniMethods.destroyBoard();
            System.exit(0);
        }

        if (jniMethods.checkDraw()){
            System.out.println("Draw");
            jniMethods.destroyBoard();
            System.exit(0);
        }
    }
}
