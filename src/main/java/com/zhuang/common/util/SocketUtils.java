package com.zhuang.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by zhuang on 1/21/2018.
 */
public class SocketUtils {

    public static byte[] write(String ip, int port, byte[] bytes) {
        return write(ip, port, bytes, 1024 * 100);
    }

    public static byte[] write(String ip, int port, byte[] bytes, int readBufferSize) {
        try {
            Socket socket = new Socket(ip, port);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            InputStream inputStream = socket.getInputStream();
            byte[] readBuffer = new byte[readBufferSize];
            int readCount = inputStream.read(readBuffer);
            if (readCount == -1) return new byte[0];
            byte[] result = Arrays.copyOf(readBuffer, readCount);
            outputStream.close();
            socket.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static byte[] write(String ip, int port, String content) {
        return write(ip, port, content.getBytes());
    }

}
