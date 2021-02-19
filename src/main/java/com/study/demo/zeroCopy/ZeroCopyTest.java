package com.study.demo.zeroCopy;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class ZeroCopyTest {
    public static void main(String[] args) throws IOException {
        testTransferTo();
    }

    public static void  testTransferTo() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("H:\\注册码.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("H:\\注册码1.txt", "rw");
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        fromChannel.transferTo(position, count, toChannel);
    }
}
