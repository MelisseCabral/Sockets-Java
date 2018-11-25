package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable{

    private Thread t;
    private int port;
    private String nick;

    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Client(int port){
        this.port = port;
    }

    public void run() {
        System.out.println("Running Client");
        try {
            initClient(port);

        } catch (Exception e) {
            System.out.println("Thread Client interrupted.");
        }
    }

    public void start () {
        System.out.println("Starting Client" );
        if (t == null) {
            t = new Thread (this, "Client");
            t.start ();
        }
    }

    public boolean createSocketClient(int port){
        try{
            socket = new Socket("localhost", port);
            return true;
        }
        catch (IOException e){

        }
        return false;
    }

    public void createIOStream(){
        // Create Streams in and out
        try{
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            System.out.println("Client connect");
        }
        catch (IOException e){

        }
    }
    public void sendMsg(String msg){
        try {
            output.writeUTF(msg);
            output.close();
        }
        catch (IOException e){

        }
    }
    public void inputMsg(String msg){
        try{
            msg = input.readUTF();
            input.close();
        }
        catch (IOException e){

        }
    }

    public void initClient(int port){
        createSocketClient(port);
        createIOStream();
    }
}