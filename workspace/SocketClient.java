

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


// creates a socket to send and recieve mesages to and from a server
public class SocketClient {

    static GUI display=new GUI();
	static ObjectOutputStream outs =null;
	static ObjectInputStream ins =null;
	//private static boolean sending=false;
	private static String message=null;
	/*
	 *  We will build on this project in the future to make a full fledged server based game,
	 *  so make sure you can read your code later! Use good programming practices.
	 *  
	 *  ****HINT**** you may wish to have a thread be in charge of sending information 
	 *  and another thread in charge of receiving information.
	*/

	//creates a thread to send messages

// no need for a thread to send messages because messages are sent when the user does actions such as clikicng the mouse or pressing a key
	/* 
	private static class SendHandler extends Thread {
        Socket server;
        SendHandler(Socket socket) {
            server = socket;
        }

        public void run() {
        	try {
				final ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());

				message="hello server";
					while(true){
						
					
						System.out.println("reached sending=true"+sending);

						System.out.println("sending message"+message);
						out.writeObject(message);
		
						}
					}			
				
			} catch (IOException e) {
				// prints to console
				System.out.println("error in thread handling sending");
			}
        }

		*/

	
// thread to handle recieveing messages
	private static class RecieveHandler extends Thread {
        Socket server;
		JFrame gameboardFrame=null;
		//constructor
        RecieveHandler(Socket socket) {
            server = socket;
        }
		// runnig the thread checks for a message until the connection is closed
        public void run() {
			
        	try {
				final ObjectInputStream in =ins;
				while(server.isConnected()){
				
				//int recieve= in.readObject();
				JFrame serverMessage=null;
				try {
					serverMessage = (JFrame) in.readObject();
                    if(serverMessage!=null) {
                    	display.read(serverMessage);
                    }
				} catch (ClassNotFoundException e) {
					// handles the exception
					e.printStackTrace();
					System.out.println("error reading server message");
				}
	            //System.out.println("Message: " + message);
			}
			} catch (IOException e) {
				// prints to console
				System.out.println("error in thread handling recieving:");
				System.out.println(serverMessage.toString());
				e.printStackTrace();
			}
        }
	}
	
    //GUi class to create a window to send and recieve messages
	//uses a dialog to send messages
	// and a label to display messages
    private static class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener,KeyListener{



	
	//private static final long serialVersionUID = 1L;
    //JFrame f=new JFrame("simple client gui");  
	JFrame gameboard=null;
    //String input="";
    //String output=null;
	//JLabel input = new JLabel("message from server:");

   public GUI(){
	// define varous elements
	
	//JLabel messageLabel = new JLabel("label(message?");
	
	//JButton b=new JButton("Click Here to send a message");  
	/* 
	// add elements to the frame
	//f.add(output);
	//f.add(messageLabel);
	//f.add(b);  
	//f.add(input);
	f.setSize(1000,500);  
	f.setLayout(new GridLayout());  
	f.setVisible(true);
    f.setDefaultCloseOperation(EXIT_ON_CLOSE);
	*/
	   //button to send messages
		//b.addMouseListener(this);
		
        //System.out.print("Gui displayed");
		//updates the gui
		revalidate();
	   repaint();
	   
	 
	}
   
	// reads a message from the server
   public void read(JFrame g){

    //input.setText("message from server:"+ s);
	gameboard=g;
	g.setVisible(true);
	//g.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


   public void update(){
    //output(getName());
    revalidate();
    repaint();
   }
//key event functions
@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
		System.out.println("up key pressed");
		
		try {
			outs.writeObject(e);	
			System.out.println("sending keyevent");
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			System.out.println("error sending keyevent");
		}
		
	}

	if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
		System.out.println("left pressed");
		
		try {
			outs.writeObject(e);	
			System.out.println("sending keyevent");
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			System.out.println("error sending keyevent");
		}
		
	}


	if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
		System.out.println(" right pressed");
		
		try {
			outs.writeObject(e);	
			System.out.println("sending keyevent");
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			System.out.println("error sending keyevent");
		}
		
	}

	if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
		System.out.println("up key pressed");
		try {
			outs.writeObject(e);	
			System.out.println("sending keyevent");
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			System.out.println("error sending keyevent");
		}
	}
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyReleased(KeyEvent e ){
	// TODO Auto-generated method stub
	
}

  //mouse event functions 
@Override
public void mouseDragged(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseMoved(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
//creates a dialog to send a message when the button is clicked
public void mouseClicked(MouseEvent e) {
	
	// gets a message from the user by craeting a dialog
	
	String input = JOptionPane.showInputDialog(null, "type your message and press ok to send");
	message=input;
	// then tells the thread handling sending to send the message
	//System.out.println("sending message"+message);
	try {
	outs.writeObject(message);
	} catch (Exception ex) {
	// exeption handled here
	System.out.println("error sending message");
	ex.printStackTrace();
}
	//System.out.println(input);
	//rh.send(input);
	
}
@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	update();
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



}//end gui
	
	
	
	

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        //SocketClient t = new SocketClient();
    	InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        //ObjectOutputStream oos = null;
        //ObjectInputStream ois = null;
        //establish socket connection to server
        socket = new Socket(host.getHostName(), 9876);
        ins= new ObjectInputStream(socket.getInputStream());
        outs = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("sucessfully connected to server");
        //outs.writeObject("hello from the client side");
        
        //System.out.println((String) ins.readObject());
        Thread rh=new RecieveHandler(socket);
            rh.start();

        //for(int i=0; i<5;i++){
            //establish socket connection to server
            //socket = new Socket(host.getHostName(), 9876);
            //Thread wh=new SendHandler(socket);
            
            //wh.start();
            //write to socket using ObjectOutputStream
            //commented out code to test that connection is working
            /* 
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            //System.out.println("Sending request to Socket Server");
            // System.out.println("Sending request to Socket Server");
            //if(i==4)oos.writeObject("exit");
            //else oos.writeObject(""+i);
            //read the server response message
            
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            
            //close resources
            ois.close();
            oos.close();
            */
            while(true){
                display.update();
			}
            //Thread.sleep(100);
       // } end of for loop for testing
        
        
    }



	
}