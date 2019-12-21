package com.zhuang.common.util;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhuang on 1/21/2018.
 */
public class SocketUtilsTest {

    @Test
    public void write() throws Exception {
        System.out.println("提示：当Socket发送缓存满时OutputStream的write会阻塞，当Socket的接收缓存没有数据时InputStream的read会阻塞。");
        byte[] bytes = SocketUtils.write("127.0.0.1",21,"zwb");
        System.out.println(new String(bytes));
    }


    @Test
    public void server()
    {
        try {
            ServerSocket serverSocket=new ServerSocket(3699);
            Socket socket = serverSocket.accept();
            String remoteIp = socket.getRemoteSocketAddress().toString();
            System.out.println(remoteIp);
            OutputStream outputStream = socket.getOutputStream();
            for (int i=0;i<1000000;i++)
            {
                outputStream.write(new byte[1024]);
                System.out.println(i);
                Thread.sleep(100);
            }
            System.out.println("server write end!");
            Thread.sleep(5*60*1000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void client()
    {
        try {
            Socket socket=new Socket("192.168.0.102",3699);
            InputStream inputStream = socket.getInputStream();
            Thread.sleep(5*60*1000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}