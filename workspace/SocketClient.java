

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.WriteAbortedException;
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
	static Object[] gameState=null;
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
		//JFrame gameboardFrame=null;
		//constructor
        RecieveHandler(Socket socket) {
            server = socket;
        }
		// runnig the thread checks for a message until the connection is closed
        public void run() {
			
        	try {
				final ObjectInputStream in =ins;
				while(server.isConnected()){
					//unpauses teh game when the server connects
					//display.read(gameState);
				//int recieve= in.readObject();
				Object serverMessage=null;
				try {
					serverMessage = in.readObject();
                    if(serverMessage!=null) {
                    	display.read((Object[])serverMessage);
						System.out.println("importing game state");								
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
				
				if(e instanceof EOFException || e instanceof WriteAbortedException) {
                        System.out.println("Server disconnected: " );
                    } else {
                        e.printStackTrace();
                    }
			}
        }
	}
	
    //GUi class to create a window to send and recieve messages
	//uses a dialog to send messages
	// and a label to display messages
    private static class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener,KeyListener{



	
	//private static final long serialVersionUID = 1L;
    Snake f=new Snake(1);  
	//JPanel game=new JPanel();
	//Snake s= null;

   public GUI(){
	//show the frame
	f.setVisible(true);
    f.setDefaultCloseOperation(EXIT_ON_CLOSE);
	//pauses the game
	gameState=f.exportGameState();
	boolean[] pausedBooleans=(boolean[])gameState[1];
	gameState[1]=pausedBooleans;
	int[] ints=(int[])gameState[0];
	boolean[] bools=(boolean[])gameState[1];
	//pauses the game by setting all the direction values to false
	f.importGameState(ints[0],ints[1],ints[2],ints[3],ints[4],ints[5],false,false,false,false,false,false,false,false,bools[8]);
	revalidate();
	   repaint();

        //System.out.print("Gui displayed");
		//updates the gui
		JLabel label = new JLabel("Client GUI");
		f.add(label);
		label.addKeyListener(this);
		f.board.addKeyListener(this);
		revalidate();
	   repaint();
	   
	 
	}
   
	// reads a message from the server
   public void read(Object[] gameState){
	System.out.println("importing game state from server");
	int[] ints=(int[])gameState[0];
	boolean[] bools=(boolean[])gameState[1];
	f.importGameState(ints[0],ints[1],ints[2],ints[3],ints[4],ints[5],bools[0],bools[1],bools[2],bools[3],bools[4],bools[5],bools[6],bools[7],bools[8]);
	revalidate();
	   repaint();
    //input.setText("message from server:"+ s);
	System.out.println("game displayed");
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
		//System.out.println("up key pressed");
		
		try {
			outs.writeObject(e);	
			//System.out.println("sending keyevent");
		} catch (Exception ex) {
			// handles exception
			ex.printStackTrace();
			System.out.println("error sending keyevent");
		}
		
	}

	if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
		//System.out.println("left pressed");
		
		try {
			outs.writeObject(e);	
			//System.out.println("sending keyevent");
		} catch (Exception ex) {
			// handles exception
			ex.printStackTrace();
			System.out.println("error sending keyevent");
		}
		
	}


	if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
		//System.out.println(" right pressed");
		
		try {
			outs.writeObject(e);	
			//System.out.println("sending keyevent");
		} catch (Exception ex) {
			// handles exception
			ex.printStackTrace();
			System.out.println("error sending keyevent");
		}
		
	}

	if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
		//System.out.println("up key pressed");
		try {
			outs.writeObject(e);	
			//System.out.println("sending keyevent");
		} catch (Exception ex) {
			// handles exception
			ex.printStackTrace();
			System.out.println("error sending keyevent");
		}
	}

	if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		//System.out.println("up key pressed");
		
		try {
			outs.writeObject(e);	
			System.out.println("sending space");
		} catch (Exception ex) {
			// handles exception
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
//old code from chatserver
//does nothing becasue theres no button or mouselistener
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
    	InetAddress host = InetAddress.getLocalHost();
		//host=InetAdress.get(?)
        Socket socket = null;
        //establish socket connection to server
        socket = new Socket(host.getHostName(), 9876);
        ins= new ObjectInputStream(socket.getInputStream());
        outs = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("sucessfully connected to server");

        Thread rh=new RecieveHandler(socket);
            rh.start();
            while(true){
                display.update();
			}
            //Thread.sleep(100)
        
        
    }



	
}