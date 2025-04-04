//code for snake copied from https://zetcode.com/javagames/snake/
//basically a game of snake
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    
    private final int B_WIDTH = 900;//changed to 700 from smaller 300
    private final int B_HEIGHT = 720;//changed to 700 from smaller 300
    private final int DOT_SIZE = 50;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 14;
    private final int DELAY = 200;//changed to slow down from 140

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    //duplicated variables for player 2 snake
    private final int x2[] = new int[ALL_DOTS];
    private final int y2[] = new int[ALL_DOTS];

    private int dots;
    private int dots2;//duplicated variable for player 2 snake
    private int apple_x;
    private int apple_y;

    private int apple_x2;
    private int apple_y2;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    //duplicated variables for player 2 snake
    private boolean leftDirection2 = false;
    private boolean rightDirection2 = true;
    private boolean upDirection2 = false;
    private boolean downDirection2 = false;

    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image apple2;
    private Image head;
    //duplicated variables for player 2 snake
    private Image head2;
    private Image ball2;

    public Board() {
        
        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("workspace/snake_green_blob_64.png");
        ball = iid.getImage();
        
        ImageIcon iia = new ImageIcon("workspace/apple_red_64.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("workspace/snake_green_head_64.png");
        head = iih.getImage();
        //duplicate code for player 2 snake
        ImageIcon iid2 = new ImageIcon("workspace/snake_yellow_blob_64.png");
        ball2 = iid2.getImage();

        //code for extra apple
        ImageIcon iia2 = new ImageIcon("workspace/apple_green_64.png");
        apple2 = iia2.getImage();

        ImageIcon iih2 = new ImageIcon("workspace/snake_yellow_head_64.png");
        head2 = iih2.getImage();


    }

    private void initGame() {

        dots = 3;
        dots2=3;
        //reset to default values
        //duplicated code for player 2 snake
        for (int z = 0; z < dots2; z++) {
            x2[z] = 50 - z * 10;
            y2[z] = 50;
        }

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        
        locateApple();
        locateExtraApple();//second apple so 2 players can both get apples

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);
            g.drawImage(apple2, apple_x2, apple_y2, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }
            //duplicate code for player 2 snake
            for (int z = 0; z < dots2; z++) {
                if (z == 0) {
                    g.drawImage(head2, x2[z], y2[z], this);
                } else {
                    g.drawImage(ball2, x2[z], y2[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 24);//increased font size
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
        String win="tie?";
        if(dots>dots2){
            win="Player 1 wins!";
        }else if(dots2>dots){
            win="Player 2 wins!";
        }
        g.drawString(win, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 4);
    }

    private void checkApple() {

        if (((x[0] == apple_x) && (y[0] == apple_y)) || ((x[0] == apple_x2) && (y[0] == apple_y2))) {

            dots++;
            locateApple();
        }
        //duplicated variables for player 2 snake
        if (((x2[0] == apple_x) && (y2[0] == apple_y)) || ((x2[0] == apple_x2) && (y2[0] == apple_y2))) {
            dots2++;
            locateApple();
        }
    }

    private void move() {
        
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
        //duplicated code for player 2 snake
            for (int z = dots2; z > 0; z--) {
                x2[z] = x2[(z - 1)];
                y2[z] = y2[(z - 1)];
            }
    
            if (leftDirection2) {
                x2[0] -= DOT_SIZE;
            }
    
            if (rightDirection2) {
                x2[0] += DOT_SIZE;
            }
    
            if (upDirection2) {
                y2[0] -= DOT_SIZE;
            }
    
            if (downDirection2) {
                y2[0] += DOT_SIZE;
            }
            
    }

    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
        //duplicated code for player 2 snake
        for (int z = dots2; z > 0; z--) {

            if ((z > 4) && (x2[0] == x2[z]) && (y2[0] == y2[z])) {
                inGame = false;
            }
        }

        if (y2[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y2[0] < 0) {
            inGame = false;
        }

        if (x2[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x2[0] < 0) {
            inGame = false;
        }        

        if (!inGame) {//ends game if either player collides
            timer.stop();
        }
    }

    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));

    }

    private void locateExtraApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x2 = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y2 = ((r * DOT_SIZE));

    }

    public void importkeyEvent(KeyEvent e, int player) {
    //copied code from keyPressed
    int key = e.getKeyCode();
    //adjusts key event code so player 1 is arrow keys and player 2 is wasd
    if(player==1){
    if(key==KeyEvent.VK_W){
        key=KeyEvent.VK_UP;
    }
    if(key==KeyEvent.VK_A){
        key=KeyEvent.VK_LEFT;
    }
    if(key==KeyEvent.VK_S){
        key=KeyEvent.VK_DOWN;
    }
    if(key==KeyEvent.VK_D){
        key=KeyEvent.VK_RIGHT;
    }
    
    }

    if(player==2){
        if(key==KeyEvent.VK_UP){
            key=KeyEvent.VK_W;
        }
        if(key==KeyEvent.VK_LEFT){
            key=KeyEvent.VK_A;
        }
        if(key==KeyEvent.VK_DOWN){
            key=KeyEvent.VK_S;
        }
        if(key==KeyEvent.VK_RIGHT){
            key=KeyEvent.VK_D;
        }
        
    }



    if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
        leftDirection = true;
        upDirection = false;
        downDirection = false;
    }

    if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
        rightDirection = true;
        upDirection = false;
        downDirection = false;
    }

    if ((key == KeyEvent.VK_UP) && (!downDirection)) {
        upDirection = true;
        rightDirection = false;
        leftDirection = false;
    }

    if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
        downDirection = true;
        rightDirection = false;
        leftDirection = false;
    }

    //wasd is for player 2
    if ((key == KeyEvent.VK_A) && (!rightDirection)) {
                leftDirection2 = true;
                upDirection2 = false;
                downDirection2 = false;
            }

            if ((key == KeyEvent.VK_D) && (!leftDirection)) {
                rightDirection2 = true;
                upDirection2 = false;
                downDirection2 = false;
            }

            if ((key == KeyEvent.VK_W) && (!downDirection)) {
                upDirection2 = true;
                rightDirection2 = false;
                leftDirection2 = false;
            }

            if ((key == KeyEvent.VK_S) && (!upDirection)) {
                downDirection2 = true;
                rightDirection2 = false;
                leftDirection2 = false;
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();

        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            //added wasd
            if ((key == KeyEvent.VK_A) && (!rightDirection)) {
                leftDirection2 = true;
                upDirection2 = false;
                downDirection2 = false;
            }

            if ((key == KeyEvent.VK_D) && (!leftDirection)) {
                rightDirection2 = true;
                upDirection2 = false;
                downDirection2 = false;
            }

            if ((key == KeyEvent.VK_W) && (!downDirection)) {
                upDirection2 = true;
                rightDirection2 = false;
                leftDirection2 = false;
            }

            if ((key == KeyEvent.VK_S) && (!upDirection)) {
                downDirection2 = true;
                rightDirection2 = false;
                leftDirection2 = false;
            }



            //press space to restart
            if (key == KeyEvent.VK_SPACE) {
                inGame = true;
                 leftDirection = false;
                 rightDirection = true;
                upDirection = false;
                downDirection = false;
    //duplicated variables for player 2 snake
                 leftDirection2 = false;
                rightDirection2 = true;
                upDirection2 = false;
               downDirection2 = false;

                initGame();
            }


        }
    }
}


