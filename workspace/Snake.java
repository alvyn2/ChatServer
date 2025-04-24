
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
        public Snake(int type) {
            
            initUI(type);
        }
        
        private void initUI(int t) {
            board = new Board();
            add(board);
            
            setResizable(false);
            pack();
             if(t==0){
                setTitle("Server Snake");
                
             }else{
                setTitle("Client Snake");
                setLocationRelativeTo(null);

             }
            //setLocationRelativeTo(null);//sets the JFrame to the center of the screen
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        
        public void importkeyEvent(String e, int player) {
            board.importkeyEvent(e,player);
        }
        public void importGameState(int d,int d2,int ax,int ay,int ax2,int ay2,boolean ld,boolean rd,boolean ud,boolean dd,boolean ld2, boolean rd2 ,boolean ud2 ,boolean dd2, boolean ig,int[] xi,int[] yi,int[] xi2,int[] yi2) {
            board.importGameState(d, d2, ax, ay, ax2, ay2, ld, rd, ud, dd, ld2, rd2 , ud2 , dd2, ig,xi,yi,xi2,yi2);
        }
        public Object[] exportGameState(){
            return board.exportGameState();
        }
        public static void main(String[] args) {
            
            EventQueue.invokeLater(() -> {
                JFrame ex = new Snake(0);
                ex.setVisible(true);
            });
        }
    }
    