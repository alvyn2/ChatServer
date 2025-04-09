

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;
/**
 * This program is a server that takes connection requests on
 * the port specified by the constant LISTENING_PORT.  When a
 * connection is opened, the program should allow the client to send it messages. The messages should then 
 * become visible to all other clients.  The program will continue to receive
 * and process connections until it is killed (by a CONTROL-C,
 * for example). 
 * 
 * This version of the program creates a new thread for
 * every connection request.
 */
public class SnakeServer {
//9876 default
    public static final int LISTENING_PORT = 9876;
    private static ArrayList<ConnectionHandler> connections = new ArrayList<ConnectionHandler>(); 
   // JPanel board = new Board();
    static Board board = new Board();
    static Snake ex =null;
	public static void main(String[] args) {
    	ServerSocket listener ; // Listens for incoming connections.
        //Socket connection;    // For communication with the connecting program.
        int idIterator=1;
        /* Accept and process connections forever, or until some error occurs. */
        
        try {

            // Accept next connection request and handle it.
            listener = new ServerSocket(LISTENING_PORT);
            System.out.println("Listening on port " + LISTENING_PORT);
            board = new Board();
            //code copied from snake to run the game

            while (true) {
            	//create a socket to form connection and create a thread to handle the socket
            	Socket s = listener.accept();
            	ConnectionHandler cH=new ConnectionHandler(s,idIterator);
                idIterator++;
            	cH.start();
            	connections.add(cH);
            }

        }
        catch (Exception e) {
            System.out.println("Sorry, the server has shut down.");
            System.out.println("Error:  " + e);
            return;
        }

        
            
            
        

    }  // end main()


    /**
     *  Defines a thread that handles the connection with one
     *  client.
     */
    private static class ConnectionHandler extends Thread {
        private Socket client;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private int id;
        
        //ObjectInputStream in;
        ConnectionHandler(Socket socket,int i) {
            client = socket;
            id=i;
            try {
            	//code to create input and output stream
            	out = new ObjectOutputStream(client.getOutputStream());
            	in = new ObjectInputStream(client.getInputStream());
            }
            catch (Exception e){
            e.printStackTrace();	
            System.out.println("Error creating input/output stream");
            }
            }
        //sends an object to the client
        public synchronized void send(Object output) {
        	try {
        		out.writeObject(output);
        	}catch(IOException e) {
        		System.out.println("error sending message");
        		e.printStackTrace();
        	}
        }

		public void run() {
            EventQueue.invokeLater(() -> {
                ex = new Snake();
                ex.setVisible(true);
            });
            send(ex.exportGameState());
            String clientAddress = client.getInetAddress().toString();// only for debugging purposes
            while(client.isConnected()) {
	            try {
                  
	            	//code to send messages
	            	//String message = (String) in.readObject();
	            	Object input=null;
	            	input = in.readObject();
                    
	            	if(input!=null) {
	            		//System.out.println("server recieved"+input);
	            		if(input instanceof KeyEvent) {
                            ex=(Snake) ex;
                            System.out.println("id"+id);
                            System.out.println("keyevent"+input);
                            ex.importkeyEvent((KeyEvent)input,id);
                        
                        
                        for(ConnectionHandler c :connections) {
	            			synchronized(c){
                                //System.out.println("sending"+input);
                                //sneds the entire snake game gui
                                Object[] gameState = ex.exportGameState();
	            				c.send(gameState);
	            			}
	            		}
                        }
	            	}
	            	//out.close();
	            }
	            catch (Exception e){

	                System.out.println("Error on connection with: " 
	                        + clientAddress + ": " + e);
                    if(e instanceof EOFException) {
                        System.out.println("Client disconnected: " + clientAddress);
                    } else {
                        e.printStackTrace();
                    }
	            }
            }
            System.out.println("Connection closed: " );
        }
    }

        
    


}//end serverclass

