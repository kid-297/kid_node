package com.yier33.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String[] args) throws IOException {

        //1. 创建 serverSockerChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //2. 得到 Selector 对象
        Selector selector = Selector.open();

        //3. 绑定端口，在服务器端进行监听
        serverSocketChannel.socket().bind(new InetSocketAddress(666));

        //4, 设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //5. 把 serverSockerChannel 注册到 selector
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        //6. 循环等待客户端连接
        while (true){
            //不存在有事件通道
            if(selector.select(1000) == 0){
                System.out.println("当前不存在有事件通道");
                continue;
            }

            //存在有事件通道，获取所有存在的通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                //如果存在连接请求,分配 SocketChannel
                if(selectionKey.isAcceptable()){
                    //给该客户端生成一个 SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // socketChannel 设置非阻塞
                    socketChannel.configureBlocking(false);
                    System.out.println("this is a new socketChannel : " + socketChannel.hashCode());
                    //将 socketChannel 注册到 selector
                    socketChannel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(1024));
                }

                //有读取事件
                if (selectionKey.isReadable()){
                    //通过 key 获取对应 channel
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    //获取和该 channel 管理的 buffer
                    ByteBuffer buffer = (ByteBuffer)selectionKey.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端 ："+new String(buffer.array()));
                }

                //手动从集合中，移除当前的 selectionKey
                iterator.remove();
            }
        }
    }
}
