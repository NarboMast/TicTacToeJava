import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel {
    private JNImethods jniMethods;
    private Icons icons;
    private JButton[][] buttons;

    public Board() {
        setLayout(new GridLayout(Settings.dimension, Settings.dimension));
        //setPreferredSize(new Dimension(600, 600));

        jniMethods = new JNImethods();
        icons = new Icons();
        buttons = new JButton[Settings.dimension][Settings.dimension];

        for (int i = 0; i < Settings.dimension; i++) {
            for (int j = 0; j < Settings.dimension; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(120, 120));
                button.setBackground(Color.white);

                final int row = i;
                final int col = j;

                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleClick(row, col);
                    }
                });

                buttons[i][j] = button;
                add(button);
            }
        }

        jniMethods.setBoard(Settings.dimension);
    }

    void handleClick(int row, int col) {
        char player = jniMethods.getValue(row, col);
        buttons[row][col].setIcon(icons.getIcon(player));
        boolean win = jniMethods.checkWin(player);
        if (win) {
            System.out.println(player + " wins");
            jniMethods.destroyBoard();
            System.exit(0);
        }
    }
}
