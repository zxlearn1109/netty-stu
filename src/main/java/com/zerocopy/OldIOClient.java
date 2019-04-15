package com.zerocopy;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class OldIOClient {

    private static final String CENTOS_ISO = "C:\\Users\\ll\\Downloads\\jdk-8u192-linux-x64.tar.gz";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 8899));

        File file = new File(CENTOS_ISO);
        FileInputStream inputStream = new FileInputStream(file);

        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096*4096];
        long readcount = 0, total = 0;

        long startTime = System.currentTimeMillis();
        while ((readcount = inputStream.read(buffer)) >= 0) {
            total += readcount;
            outputStream.write(buffer);
        }
        System.out.println("发送字节总数:" + total + ",耗时：" + (System.currentTimeMillis() - startTime));
        outputStream.close();
        socket.close();
        inputStream.close();
    }

}
