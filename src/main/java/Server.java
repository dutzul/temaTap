import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by dutzul on 16.05.2017.
 */


public class Server {
    public static void acceptPlayer(ServerSocket serverSocket,Socket socket,PrintWriter printWriter,BufferedReader bufferedReader){
             try {
                 socket = serverSocket.accept();
                 printWriter = new PrintWriter(socket.getOutputStream(), true);
                 bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             } catch(Exception e){

             }
    }
    public static void main(String[] Args) {
        int portNumber = 1500;

        try {

            Singleton.getInstance();

            ServerSocket serverSocket = new ServerSocket(portNumber);

            Socket[] playerSockets=new Socket[2];
            PrintWriter[] printWriters=new PrintWriter[2];
            BufferedReader[] bufferedReaders=new BufferedReader[2];
            //acceptPlayer(serverSocket,playerSockets[0],printWriters[0],bufferedReaders[0]);
           // acceptPlayer(serverSocket,playerSockets[1],printWriters[1],bufferedReaders[1]);

            playerSockets[0] = serverSocket.accept();
            printWriters[0] = new PrintWriter(playerSockets[0].getOutputStream(), true);
            bufferedReaders[0] = new BufferedReader(new InputStreamReader(playerSockets[0].getInputStream()));

            playerSockets[1] = serverSocket.accept();
            printWriters[1] = new PrintWriter(playerSockets[1].getOutputStream(), true);
            bufferedReaders[1] = new BufferedReader(new InputStreamReader(playerSockets[1].getInputStream()));

            int turn=0;

            while (true) {
                printWriters[turn].println("Your Turn");
                String moveValue = bufferedReaders[turn].readLine();
                if (Singleton.getInstance().getMoves().get(turn ^ 1).contains(Integer.parseInt(moveValue))) {
                    try {
                        printWriters[turn].println("You Won");
                        printWriters[turn ^ 1].println("You Lost");
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    break;
                }

                Singleton.getInstance().getMoves().get(turn).add(Integer.parseInt(moveValue));

                System.out.println("Player " + turn + "generated" + moveValue);
                Thread.sleep(1000);
                turn ^= 1;
            }
        } catch (Exception e) {
           System.out.println(e.toString());
        }

    }
}

