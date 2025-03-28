

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
	 * Modify this example so that it opens a dialogue window using java swing, 
	 * takes in a user message and sends it
	 * to the server. 
	 * The server should output the message back to all connected clients
	 * (you should see your own message pop up in your client as well when you send it!).
	 * 
	 *  We will build on this project in the future to make a full fledged server based game,
	 *  so make sure you can read your code later! Use good programming practices.
	 *  
	 *  ****HINT**** you may wish to have a thread be in charge of sending information 
	 *  and another thread in charge of receiving information.
	*/

	//creates a thread to send messages
	// this thread is unnecessary so commented out

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
		//constructor
        RecieveHandler(Socket socket) {
            server = socket;
        }
		// runnig the thread checks for a message until the connection is closed
        public void run() {
			
        	try {
				while(true){
				final ObjectInputStream in =ins;
				//int recieve= in.readObject();
				String message=null;
				try {
					message = (String) in.readObject();
                    if(message!=null) {
                    	display.read(message);
                    }
				} catch (ClassNotFoundException e) {
					// there could just not 
					e.printStackTrace();
					System.out.println("error reading server message");
				}
	            //System.out.println("Message: " + message);
			}
			} catch (IOException e) {
				// prints to console
				System.out.println("error in thread handling sending:");
				e.printStackTrace();
			}
        }
	}
	
    //GUi class to create a window to send and recieve messages
	//uses a dialog to send messages
	// and a label to display messages
    private static class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener{



	
	//private static final long serialVersionUID = 1L;
    JFrame f=new JFrame("simple gui");  
    //String input="";
    //String output=null;
	JLabel input = new JLabel("message from server:");

   public GUI(){
	// define varous elements
	
	//JLabel messageLabel = new JLabel("label(message?");
	
	JButton b=new JButton("Click Here to send a message");  

	// add elements to the frame
	//f.add(output);
	//f.add(messageLabel);
	f.add(b);  
	f.add(input);
	f.setSize(1500,500);  
	f.setLayout(new GridLayout(4, 1));  
	f.setVisible(true);
    f.setDefaultCloseOperation(EXIT_ON_CLOSE);
	   //button to send messages
		b.addMouseListener(this);

		/* 
		//alternate code for using enter to send messages
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
		
        //System.out.print("Gui displayed");
		//updates the gui
		revalidate();
	   repaint();
	   
	 
	}
   
	// reads a message from the server
   public String read(String s){

    input.setText("message from server:"+ s);
    return s;

   }


   public void update(){
    //output(getName());
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
	// TODO: handle exception
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
       
        //display= new GUI();
        socket = new Socket(host.getHostName(), 9876);
        ins= new ObjectInputStream(socket.getInputStream());
        outs = new ObjectOutputStream(socket.getOutputStream());
        //outs.writeObject("hello from the client side");
        
        //System.out.println((String) ins.readObject());
        
        //for(int i=0; i<5;i++){
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);
            //Thread wh=new SendHandler(socket);
            Thread rh=new RecieveHandler(socket);
            rh.start();
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