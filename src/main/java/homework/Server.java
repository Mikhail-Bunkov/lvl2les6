package homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server {
    private static final int PORT = 8197;
    private static ServerSocket server;
    private static Socket socket;

    public static void main(String[] args) {
        try{
            server = new ServerSocket(PORT);
            System.out.println("Server is started");
            socket = server.accept();
            System.out.println("Client connected");


            Scanner scfs = new Scanner(socket.getInputStream());
            Scanner scfk = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            new Thread(()-> {
                while(true){
                    String str = scfk.nextLine();
                    if(str.equals("/end")){
                        System.out.println("Client disconnected");
                        break;
                    }
                    System.out.println("Server: "+str);
                    out.println("Server: "+ str);
                }
            }).start();
            new Thread(()-> {
                while(true){

                    String str = scfs.nextLine();
                    if(str.equals("/end")){
                        System.out.println("Client disconnected");
                        break;
                    }

                    System.out.println("Client: "+str);

                }
            }).start();
        }catch( IOException e){
            e.printStackTrace();
        }finally{
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
