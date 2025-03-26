

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
import javax.swing.JPanel;



public class SocketClient {
	
	//Thread wh=new SendHandler(socket);
    //Thread rh=new RecieveHandler(socket);
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
	private static class SendHandler extends Thread {
        Socket server;
        SendHandler(Socket socket) {
            server = socket;
        }
        public void run() {
        	try {
				final ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());;
				out.writeObject("hello server");
			} catch (IOException e) {
				// prints to console
				System.out.println("error in thread handling sending");
			}
        }
	}
	
	
//creates a thread to handle recieveing messages
	private static class RecieveHandler extends Thread {
        Socket server;
        RecieveHandler(Socket socket) {
            server = socket;
        }
        public void run() {
        	try {
				final ObjectInputStream in =new ObjectInputStream(server.getInputStream());
				//int recieve= in.readObject();
				String message="no message";
				//try {
					//message = (String) in.readObject();
				//} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("cant read server message");
				//}
	            System.out.println("Message: " + message);
			} catch (IOException e) {
				// prints to console
				System.out.println("error in thread handling sending:");
				e.printStackTrace();
			}
        }
	}
	
	
	
	/*
	
	*/
	

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        SocketClient t = new SocketClient();
    	InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        GUI display= new GUI();
       
        
        socket = new Socket(host.getHostName(), 9876);
        ObjectInputStream ins= new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream outs = new ObjectOutputStream(socket.getOutputStream());
        outs.writeObject("hello from the other side");
        
        System.out.println((String) ins.readObject());
        for(int i=0; i<5;i++){
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);
            Thread wh=new SendHandler(socket);
            Thread rh=new RecieveHandler(socket);
            rh.start();
            wh.start();
            //write to socket using ObjectOutputStream
            
            //ois = new ObjectInputStream(socket.getInputStream());
            //oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            //oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            if(i==4)oos.writeObject("exit");
            else oos.writeObject(""+i);
            //read the server response message
            
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
        }
        
        
    }



	
}