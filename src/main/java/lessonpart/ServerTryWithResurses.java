package lessonpart;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTryWithResurses {
    private static final int PORT = 8190;

    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server is started");
            try (Socket socket = server.accept()) {
                System.out.println("Client connected");
                new Thread(()->{
                    try (Scanner sc = new Scanner(socket.getInputStream()))
                          {
                        while (true) {
                            String str = sc.nextLine();
                            if (str.equals("/end")) {
                                System.out.println("Client disconnected");
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
                new Thread(()->{
                    try (
                         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {
                        while (true) {
                            out.println("Client: " + out);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }catch( IOException e){
            e.printStackTrace();
        }
    }
}
