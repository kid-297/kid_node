package com.yier33.netty.nio;

import java.nio.ByteBuffer;

/**
 * ByteBuffer支持类型
 */
public class NIOByteBufferTest {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        byteBuffer.putInt(100);
        byteBuffer.putDouble(3);
        byteBuffer.putChar('迩');
        byteBuffer.putShort((short) 2);
        byteBuffer.putLong(3L);

        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getLong());
    }
}
