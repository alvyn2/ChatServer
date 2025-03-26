

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author 128409
 *
 */
public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener{



	
	//private static final long serialVersionUID = 1L;
    JFrame f=new JFrame("simple gui");  
    //String input="";
    //String output=null;


   public GUI(){
	// define varous elements
	JLabel output = new JLabel("output:");
	//JLabel messageLabel = new JLabel("label(message?");
	JLabel write = new JLabel("input here : ");
	JButton b=new JButton("Click Here");  

	// add elements to the frame
	f.add(output);
	//f.add(messageLabel);
	f.add(b);  
	f.add(write);
	f.setSize(1500,500);  
	f.setLayout(new GridLayout(4, 1));  
	f.setVisible(true);

	   /*
	   //JPanel title =new JPanel(new GridLayout());
    	//JPanel outputDisplay =new JPanel(new GridLayout());
    	//JPanel inputDisplay=new JPanel(new GridLayout());
    	JPanel panel= new JPanel(new FlowLayout());
    	
    	JLabel titlename=new JLabel("client for chat server");
    	JLabel outputframe=new JLabel("output:");
    	JLabel inputlabel=new JLabel("input:");
    	
    	panel.add(outputframe);
    	panel.add(inputlabel);
    	
    	panel.add(titlename);
    	//this.add(outputDisplay);
    	//this.add(inputDisplay);
    	//this.add(title);
    	this.add(panel);
    	// game.initialize();
    	*/
        
	   
		b.addMouseListener(this);

		/* 
		write.addKeyListener(new KeyListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("button clicked");
			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("enter pressed");
					output(getName());
				}
				
			}


		});
	    
		*/
		System.out.print("Gui displayed");
		revalidate();
	   repaint();
	   
	 
	}
   
   public String read(){
    String s="";
    
    return s;

   }

   public void output(String message){
  
   }

   public void update(){
    output(getName());
    revalidate();
    repaint();
   }
@Override
public void mouseDragged(MouseEvent e) {
	// TODO Auto-generated method stub
	
}



@Override
public void mouseMoved(MouseEvent e) {
	// TODO Auto-generated method stub
	
}



@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
	
}



@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}



@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}



@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}



@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}



@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}



}