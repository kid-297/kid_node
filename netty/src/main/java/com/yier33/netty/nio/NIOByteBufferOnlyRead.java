package com.yier33.netty.nio;

import java.nio.ByteBuffer;

/**
 * 只读
 */
public class NIOByteBufferOnlyRead {

    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(5);

        for (int i = 0; i < 5; i++) {
            byteBuffer.put((byte)i);
        }

        byteBuffer.flip();

        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();

        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }
    }
}
