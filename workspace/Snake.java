
//code to diplay the snake game
    //code for snake copied from https://zetcode.com/javagames/snake/
//Graphics by Bas de Reuver

    import java.awt.EventQueue;
    import javax.swing.JFrame;
    import javax.swing.JPanel;
    //
    public class Snake extends JFrame {
        public Board board;
        public Snake() {
            
            initUI();
        }
        
        private void initUI() {
            board = new Board();
            add(board);
            
            setResizable(false);
            pack();
            
            setTitle("Snake");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public static void main(String[] args) {
            
            EventQueue.invokeLater(() -> {
                JFrame ex = new Snake();
                ex.setVisible(true);
            });
        }
    }
    