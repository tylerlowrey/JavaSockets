import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler implements Runnable
{
    private final Socket clientSocket;

    ConnectionHandler(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
    }


    @Override
    public void run()
    {
        String inputLine;

        try
        (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        )
        {
            while ((inputLine = in.readLine()) != null)
            {
                out.println(inputLine);
            }
        }
        catch (IOException e)
        {
            System.out.println("IOException occurred during connection with " + clientSocket.getInetAddress());
        }

    }
}
