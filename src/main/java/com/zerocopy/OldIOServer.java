package com.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class OldIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket();
        socket.bind(new InetSocketAddress(8899));


        while (true) {
            Socket accept = socket.accept();
            InputStream dataInputStream = new DataInputStream(accept.getInputStream());
            byte[] bytes = new byte[4096 * 4096];

            while (true) {
                int readcount = dataInputStream.read(bytes, 0, bytes.length);
                if (readcount == -1) {
                    break;
                }
            }

        }
    }

}
