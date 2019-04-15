package com.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {

    private static final String CENTOS_ISO = "C:\\Users\\ll\\Downloads\\jdk-8u192-linux-x64.tar.gz";

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        socketChannel.configureBlocking(true);

        FileChannel channel = new FileInputStream(CENTOS_ISO).getChannel();

        long startTime = System.currentTimeMillis();
        long transferCount = channel.transferTo(0, channel.size(), socketChannel);

        System.out.println("发送字节总数:" + transferCount + ",耗时:" + (System.currentTimeMillis() - startTime));
        channel.close();
        socketChannel.close();
        //发送字节总数:8388608,耗时:409
    }

}
