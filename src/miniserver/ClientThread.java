/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias
 */
public class ClientThread implements Runnable
{

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientThread(Socket socket) throws IOException
    {
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run()
    {
        try
        {
            boolean run = true;
            while (run)
            {
                String cmd = in.readUTF();
                System.out.println("Command: " + cmd);
                switch (cmd.toLowerCase())
                {
                    case "exit":
                        run = false;
                        out.writeUTF("Goodbye!");
                        socket.close();
                        break;
                    case "hello":
                        out.writeUTF("Hello from Server.");
                        break;
                    default:
                        out.writeUTF("Unknown command: " + cmd);
                        break;
                }
            }
        } catch (IOException ex)
        {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
