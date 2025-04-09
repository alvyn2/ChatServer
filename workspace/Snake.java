
//code to diplay the snake game
    //code for snake copied from https://zetcode.com/javagames/snake/
//Graphics by Bas de Reuver

    import java.awt.EventQueue;
    import javax.swing.JFrame;
    import javax.swing.JPanel;
    import java.awt.event.KeyEvent;
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
        public void importkeyEvent(KeyEvent e, int player) {
            board.importkeyEvent(e,player);
        }
        public void importGameState(int d,int d2,int ax,int ay,int ax2,int ay2,boolean ld,boolean rd,boolean ud,boolean dd,boolean ld2, boolean rd2 ,boolean ud2 ,boolean dd2, boolean ig) {
            board.importGameState(d, d2, ax, ay, ax2, ay2, ld, rd, ud, dd, ld2, rd2 , ud2 , dd2, ig);
        }
        public Object[] exportGameState(){
            return board.exportGameState();
        }
        public static void main(String[] args) {
            
            EventQueue.invokeLater(() -> {
                JFrame ex = new Snake();
                ex.setVisible(true);
            });
        }
    }
    