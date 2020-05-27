/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversockettest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hossein
 */
public class ServerSocketTest {

    /**
     * @param args the command line arguments
     */
    static ServerSocket server;
    private final static int BUFSIZE=32;
    public static void main(String[] args) throws IOException {
        try {
            server= new ServerSocket(12345);
        } catch (IOException ex) {
            Logger.getLogger(ServerSocketTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Server Started!");
        System.out.println("Waiting for Client...");
        int RecvMSGSize;
        byte[] RecvBuf=new byte[BUFSIZE];
        while(true){
            Socket socket=server.accept();
            System.out.println("Client Connected!");
            SocketAddress clientAddr=socket.getRemoteSocketAddress();
            System.out.println("Client addr:"+ clientAddr);
            InputStream in=socket.getInputStream();
            OutputStream out=socket.getOutputStream();
            while((RecvMSGSize=in.read(RecvBuf))!=-1){
                out.write(RecvBuf,0,RecvMSGSize);
                String s=new String(RecvBuf);
                System.out.println(s);
            }
            socket.close();
        }
        
    }
    
}
