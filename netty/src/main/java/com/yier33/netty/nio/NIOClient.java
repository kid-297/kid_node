package com.yier33.netty.nio;

        import java.io.IOException;
        import java.net.InetSocketAddress;
        import java.nio.ByteBuffer;
        import java.nio.channels.SocketChannel;

public class NIOClient {

    public static void main(String[] args) throws IOException {

        //1. 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();

        //2. 设置非阻塞
        socketChannel.configureBlocking(false);

        //3. 提供服务器端的ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",666);

        //4. 连接服务器
        if(!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("当前服务器正在连接中，可以做其他的事情。");
            }
        }

        //5.发送数据
        String text = "hello,yier";
        socketChannel.write(ByteBuffer.wrap(text.getBytes()));
        System.out.println("发送成功");
        System.in.read();
    }
}
