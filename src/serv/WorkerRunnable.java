package serv;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Hashtable;

/**

 */
public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;
    
    String OpponentAdress, PlayerAdress;
    int value;
    
    Hashtable<String,Integer> hashPos = new Hashtable<String,Integer>();
    
    

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try {
         
          
        	DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            
          	value = in.readInt();
          	PlayerAdress = in.readUTF();
          	OpponentAdress = in.readUTF();
          	
          	hashPos.put(PlayerAdress, value);
          	
          
          	
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            
            out.writeInt(hashPos.get(PlayerAdress));
         //   out.writeInt(value); 
            out.close();
        	in.close();
           
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}