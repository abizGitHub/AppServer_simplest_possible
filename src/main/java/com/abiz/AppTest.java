package com.abiz;

import net.NetWorker;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppTest {


    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket server = new ServerSocket();
        SocketAddress address = new InetSocketAddress(8080);
        server.bind(address);
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        while (true) {
            Socket socket = server.accept();
            NetWorker netWorker = new NetWorker(socket);
            executorService.submit(netWorker);
        }

    }


}
