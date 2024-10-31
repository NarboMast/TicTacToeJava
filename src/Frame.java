import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    Frame(){
        setTitle("TicTacToe");
        setSize(new Dimension(600,650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board board = new Board();
        add(board);

        setVisible(true);
    }
}
