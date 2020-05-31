package com.yier33.netty.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接在内存中修改，不需要操作系统再进行拷贝
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws Exception{

        RandomAccessFile randomAccessFile = new RandomAccessFile("d:\\1.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();
        /*
            第二三个参数：可以修改的范围是 0-channel.size()
         */
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());

        map.put(0, (byte) 'H');
        map.put(2, (byte) 'l');

        channel.close();
        randomAccessFile.close();
    }

}
