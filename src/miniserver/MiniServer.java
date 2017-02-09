/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Tobias
 */
public class MiniServer
{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
    {
        Executor exe = new ThreadPoolExecutor(2, 5, 10, TimeUnit.DAYS, new LinkedBlockingQueue<>());
        ServerSocket serverSocket = new ServerSocket(1717);
        System.out.println("MiniServer running on: 1717");
        while(true)
        {
            Socket client = serverSocket.accept();
            System.out.println("Client connected from: " + client.getInetAddress());
            ClientThread clientThread = new ClientThread(client);
            exe.execute(clientThread);
        }
    }
    
}
