import javax.swing.*;

public class Frame extends JFrame implements Settings{
    Frame(){
        setTitle("TicTacToe");
        setSize(windowWidth,windowHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Board board = new Board();
        add(board);

        setVisible(true);
    }
}
