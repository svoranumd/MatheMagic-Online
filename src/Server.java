import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;

// Server class
public class Server {
    public static final int SERVER_PORT = 2526; // This creates the server port to connect to
    static String name = "";


    public static void main(String[] args) {
        boolean login = false; // bollean to set login to false at first
        String logins[] = new String[4]; // string to take in all log in options
        int j = 0;
        FileWriter myWriter;
        try  
        {  
            File file=new File("logins.txt"); // loads logins from file
            myWriter = new FileWriter("john_solutions.txt"); // sets file to blank
            myWriter = new FileWriter("sally_solutions.txt");// sets file to blank
            myWriter = new FileWriter("root_solutions.txt");// sets file to blank
            myWriter = new FileWriter("qiang_solutions.txt");// sets file to blank
            Scanner sc = new Scanner(file);     //file to be scanned  
        while (sc.hasNextLine())   {     //returns true if scanner has another line 
            logins[j] = sc.nextLine(); // load logins into array
            j++; // increment
        }
            
        }  
        catch(Exception e)  
        {  
            e.printStackTrace();  
        }   
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
                Socket socket = serverSocket.accept();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    String message;
                    String name = "test";
                    while ((message = reader.readLine()) != null) {
                        System.out.println("c:\t"+message);
                        // checks if command is LOGIN
                        if (message.startsWith("LOGIN")) {
                            String[] data = message.split(" ");
                            String test = data[1]+ " " +data[2];
                            boolean contains = Arrays.stream(logins).anyMatch(test::equals);
                            // checks if login is correct
                            if (data.length == 3 && contains == true) {
                                writer.println("SUCCESS ");
                                System.out.println("s:\tSUCCESS");
                                name = data[1];
                                myWriter = new FileWriter(name+"_solutions.txt", true);
                                login = true;
                            } else {
                                writer.println("FAILURE: Please provide correct username and password. Try again.");
                                System.out.println("s:\tFAILURE: Please provide correct username and password. Try again.");
                            }
                            // checks if command is solve
                        } else if (message.startsWith("SOLVE ") && login == true) {
                            String[] data = message.split(" ");
                            myWriter = new FileWriter(name+"_solutions.txt", true);
                            // checks if calling square
                            if (data.length == 3 && data[1].equals("-r")) {
                                float area = Integer.parseInt(data[2])*Integer.parseInt(data[2]);
                                float perimeter = Integer.parseInt(data[2])*4;
                                
                                writer.println("Rectangle’s perimeter is " + perimeter + " and area is " + area);
                                System.out.println("s:\tRectangle’s perimeter is " + perimeter + " and area is " + area);
                                myWriter.write("Sides " + data[2] + " " + data[2] +  ": Rectangle’s perimeter is " + perimeter + " and area is " + area + "\n");
                            }
                            // check if calling rectangle
                            else if (data.length == 4 && data[1].equals("-r") && login == true) {
                                float area = Integer.parseInt(data[2])*Integer.parseInt(data[3]);
                                float perimeter = Integer.parseInt(data[2])*2 + Integer.parseInt(data[3])*2 ;
                                writer.println("Rectangle’s perimeter is " + perimeter + " and area is " + area);
                                System.out.println("s:\tRectangle’s perimeter is " + perimeter + " and area is " + area);
                                myWriter.write("Sides " + data[2] + " " + data[3] +  ": Rectangle’s perimeter is " + perimeter + " and area is " + area + "\n");
                            }
                            // check if calling circle
                            else if (data.length == 3 && data[1].equals("-c")&& login == true) {
                                double Circum = Integer.parseInt(data[2])*2*Math.PI;
                                Circum = Math.round(Circum * 100.0) / 100.0;
                                double area = Integer.parseInt(data[2])*Integer.parseInt(data[2])*Math.PI;
                                area = Math.round(area * 100.0) / 100.0;
                                writer.println("Circle’s circumference is " + Circum + " and area is " + area);
                                System.out.println("s:\tCircle’s circumference is " + Circum + " and area is " + area);
                                myWriter.write("Radius " + data[2] + ": Circle’s circumference is " + Circum + " and area is " + area  + "\n");
                            }
                            // check if cirlce info is wrong
                            else if (data.length == 1 && data[1].equals("-c")&& login == true) {
                                writer.println("Error: No radius found");
                                System.out.println("s:\tError: No radius found");
                                myWriter.write("Error: No radius found");
                            }
                            // check if radius info is wrong
                            else if (data.length == 1 && data[1].equals("-r")&& login == true) {
                                writer.println("Error: No sides found");
                                System.out.println("s:\tError: No sides found");
                                myWriter.write("Error: No sides found\n");
                            }
                            // checks if formatting is off
                            else {
                                    writer.println("301 message format error");
                                    System.out.println("s:\t301 message format error");
                                    myWriter.write("301 message format error\n");
                            }
                            myWriter.flush();
                        // checks if command is list
                        } else if ((message.startsWith("LIST") ||message.equals("LIST")) && login == true) {
                            if (!name.equals("")) {
                                String[] data = message.split(" ");
                                myWriter = new FileWriter(name+"_solutions.txt", true);
                                // if non-root calls correctly
                                if (message.equals("LIST")) {
                                    writer.println(name);
                                    System.out.println("s:\t" + name);
                                    File input=new File(name+"_solutions.txt");
                                    Scanner s = new Scanner(input);
                                    if (!s.hasNextLine()) {
                                        writer.println("\t\tNo interactions yet");
                                        System.out.println("\t\tNo interactions yet");
                                    }
                                    else {
                                        while (s.hasNextLine())   { 
                                            String line = s.nextLine();
                                            writer.println("\t\t" + line);
                                            System.out.println("\t\t"+ line);
                                        }
                                    }
                                }
                                // if root calls correctly
                                else if (name.equals("root") && data[1].equals("-all")) {
                                    writer.println("root");
                                    System.out.println("s:\troot");
                                    File input=new File("root_solutions.txt");
                                    Scanner s = new Scanner(input);
                                    if (!s.hasNextLine()) {
                                        writer.println("\t\tNo interactions yet");
                                        System.out.println("\t\tNo interactions yet");
                                    }
                                    else {
                                        while (s.hasNextLine())   {       
                                            String line = s.nextLine();
                                            writer.println("\t\t" + line);
                                            System.out.println("\t\t"+ line);
                                        }
                                    }
                                    writer.println("\tjohn");
                                    System.out.println("\tjohn");
                                    input=new File("john_solutions.txt");
                                    s = new Scanner(input);
                                    if (!s.hasNextLine()) {
                                        writer.println("\t\tNo interactions yet");
                                        System.out.println("\t\tNo interactions yet");
                                    }
                                    else {
                                        while (s.hasNextLine())   {       
                                            String line = s.nextLine();
                                            writer.println("\t\t" + line);
                                            System.out.println("\t\t"+ line);
                                        }
                                    }
                                    writer.println("\tsally");
                                    System.out.println("\tsally");
                                    input=new File("sally_solutions.txt");
                                    s = new Scanner(input);
                                    if (!s.hasNextLine()) {
                                        writer.println("\t\tNo interactions yet");
                                        System.out.println("\t\tNo interactions yet");
                                    }
                                    else {
                                        while (s.hasNextLine())   {       
                                            String line = s.nextLine();
                                            writer.println("\t\t" + line);
                                            System.out.println("\t\t"+ line);
                                        }
                                    }
                                    writer.println("\tqiang");
                                    System.out.println("\tqiang");
                                    input=new File("qiang_solutions.txt");
                                    s = new Scanner(input);
                                    if (!s.hasNextLine()) {
                                        writer.println("\t\tNo interactions yet");
                                        System.out.println("\t\tNo interactions yet");
                                    }
                                    else {
                                        while (s.hasNextLine())   {       
                                            String line = s.nextLine();
                                            writer.println("\t\t" + line);
                                            System.out.println("\t\t"+ line);
                                        }
                                    }
                                } 
                                else if (!name.equals("root") && data[1].equals("-all")) {
                                    writer.println("Error: You are not the root user.");
                                    System.out.println("s:\tError: You are not the root user.");
                                } 
                                else {
                                    writer.println("301 message format error");
                                    System.out.println("s:\t301 message format error");
                                }
                            } else {
                                writer.println("LIST");
                                System.out.println("s:\tLIST");
                            }
                        // if command was shutdown
                        } else if (message.equals("SHUTDOWN") && login == true) {
                            writer.println("200 OK");
                            System.out.println("s:\t200 OK");
                            writer.flush();
                            return;
                        // if command was logout
                        } else if (message.equals("LOGOUT") && login == true) {
                            writer.println("200 OK");
                            System.out.println("s:\t200 OK");
                        }
                        // checks to make sure the user has logged in first
                        else if ((message.equals("LOGOUT") ||message.equals("SHUTDOWN") ||
                                message.startsWith("LIST") ||message.equals("LIST")||
                                message.startsWith("SOLVE ")) && login == false) {
                            writer.println("Error: You must login first");
                            System.out.println("s:\tError: You must login first");
                        }
                        // pritns if commands are invalid
                        else {
                            writer.println("300 invalid command");
                            System.out.println("s:\t300 invalid command");
                        }

                        writer.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
