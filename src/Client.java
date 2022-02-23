import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// Client class
public class Client {
    public static final int SERVER_PORT = 2526; // server port
    static String logout = "";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try (Socket socket = new Socket("", SERVER_PORT)) {
            Thread thread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println(message);
                        if (!logout.equals("") && message.equals("200 OK")) {
                            System.exit(0);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(true);
            thread.start();

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            String msg = "";
            while (true) {
                Scanner myObj = new Scanner(System.in);
                if (!msg.equals(""))
                    System.out.print("S:\t");
                msg = myObj.nextLine(); // gets user input
                System.out.println("C:\t"+msg);
                // if command is login
                if (msg.startsWith("LOGIN ")) {
                    writer.println(msg);
                }
                //if command is solve
                else if (msg.startsWith("SOLVE ")) {
                    writer.println(msg);
                }
                // if command is list
                else if (msg.startsWith("LIST ") || msg.equals("LIST")) {
                    writer.println(msg);
                }
                // if command is logout
                else if (msg.equals("LOGOUT")) {
                    writer.println("LOGOUT");
                    logout = "LOGOUT";
                }
                // if command is shutdown
                else if (msg.equals("SHUTDOWN")) {
                    writer.println("SHUTDOWN");
                    logout = "LOGOUT";
                }
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}