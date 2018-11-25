package Model;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private Thread t;
    private int port;
    private ServerSocket server;

    public Server(int port){
        this.port = port;
    }

    public void run() {
        System.out.println("Running Server");
        try {

            this.createServerSocket();
            Socket socket = this.waitConnection();
            this.treatConnection(socket);

        } catch (Exception e) {
            System.out.println("Thread Server interrupted.");
        }
    }

    public void start () {
        System.out.println("Starting Server" );
        if (t == null) {
            t = new Thread (this, "Server");
            t.start ();
        }
    }

    // Create Server
    public void createServerSocket() throws IOException {
        server = new ServerSocket(port);

    }

    //Wait connection request
    public Socket waitConnection() throws IOException {

        Socket socket = server.accept();

        return socket;
    }

    //
    public void treatConnection(Socket socket){

        try{
        // Create streams in and out
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            //Protocolo
                /* Client --> HELLO
                    Server <----HELLO WORLD
                 */
                String msg = input.readUTF();
                System.out.println("Mensagem recebida");
                output.writeUTF("HELLO WORLD");
                output.flush();

                input.close();
                output.close();
        }
        catch (IOException e){

        }
        finally {
            closeSocket(socket);
        }

    }

    public void closeSocket(Socket s){
        try{
            s.close();
        }
        catch (IOException e){

        }

    }
}
