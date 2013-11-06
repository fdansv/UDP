package com.dansd.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPTest {
    static int port = 9876;
    public static void main(String[] args) throws Exception{
        //new Messenger("localhost").send("hello, I'm fran");
        new MyServer(port).listen();
        System.out.println("The server is listening in a different thread");
        new Messenger().send("Hey dude");
    }
    static class MyServer extends Server{

        public MyServer(int port) {
            super(port);
        }

        @Override
        public String onRequest(String reqString) {
            System.out.println("RECEIVED: "+reqString);
            return "Shut up, I hate you";
        }
    }


}
