/**
 * EchoClient.java
 * @author Tyler Lowrey
 * Date Created: 08/27/19
 * Description: This is an example usage of the Java sockets API that sends a message that is echoed back by the server.
 */

import java.net.*;
import java.io.*;

public class EchoClient
{
    public static final String serverAddr = "localhost";
    public static final int serverPort = 2112;

    public static void main(String[] args)
    {

        System.out.print("Please enter your message: ");

        try
        (
            Socket echoSocket = new Socket(serverAddr, serverPort);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        )
        {
            String userInput;
            while ((userInput = stdIn.readLine()) != null)
            {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        }
        catch (UnknownHostException e)
        {
            System.err.println("Don't know about host " + serverAddr);
            System.exit(1);
        }
        catch (IOException e)
        {
            System.err.println("Couldn't get I/O for the connection to " + serverAddr);
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

}
