/**
 * Created by dutzul on 16.05.2017.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Client {
    public static void main(String[] Args) {
        int portNumber = Integer.parseInt(Args[0]);

        try {
             Socket socket = new Socket("localhost", 1500);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Random rand = new Random();
             while (true){
                 String message=in.readLine();
                 if (message.equals("Your Turn")){
                     out.println(rand.nextInt()%50);
                 }else {
                     if (message.equals("You Lost")){
                         System.out.println("I lost i am unlucky");
                     }else if (message.equals("You Won")) {
                         System.out.println("I won i am lucky");
                     }else{
                         System.out.println("Unknown message found");
                         System.out.println(message);
                     }
                 }
                 Thread.sleep(1000);
             }
        } catch (Exception e) {

        }

    }
}
