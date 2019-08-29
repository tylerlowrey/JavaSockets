/**
 * EchoClient.java
 * @author Tyler Lowrey
 * Date Created: 08/27/19
 * Description: This is an example usage of the Java sockets API that sends a message that is echoed back by the server.
 */

import java.net.*;
import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class EchoServer
{
    private static final int SERVER_PORT = 2112;
    private static final int MAX_THREADS = 10;

    public static void main(String[] args)
    {
        boolean running = true;

        Executor threadExecutor = Executors.newFixedThreadPool(MAX_THREADS);

        System.out.println("EchoServer started on port " + SERVER_PORT);

        while(running)
        {

            System.out.println("Waiting for connection...");

            try
            (
                    ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
                    Socket clientSocket = serverSocket.accept();
            )
            {

                System.out.println("Connection accepted. IP Address: " + clientSocket.getInetAddress());

                threadExecutor.execute(new ConnectionHandler(clientSocket));

            }
            catch (IOException e)
            {
                System.out.println("Exception caught when trying to listen on port "
                        + SERVER_PORT + " or listening for a connection");
                System.out.println(e.getMessage());
            }
        }

    }
}
