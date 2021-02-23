package homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
private static Socket socket;
private static final int PORT = 8197;
private static final String IP_ADDRESS = "localhost";


    public static void main(String[] args) {
        try {
            socket = new Socket(IP_ADDRESS,PORT);


            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scfs = new Scanner(socket.getInputStream());
            Scanner scfk = new Scanner(System.in);


            new Thread(()-> {
                while(true){
                       String str = scfk.nextLine();
                    if(str.equals("/end")){
                        System.out.println("Client disconnected");
                        break;
                    }
                    System.out.println("Client: "+str);
                    out.println("Client: "+ str);
                }
            }).start();
            new Thread(()-> {
                while(true){

                    String str = scfs.nextLine();
                    if(str.equals("/end")){
                            System.out.println("Client disconnected");
                            break;
                        }

                        System.out.println("Server: "+str);

                }
            }).start();
    } catch (IOException e) {
        e.printStackTrace();
    }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}}
