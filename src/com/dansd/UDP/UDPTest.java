package com.dansd.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPTest {
    static int port = 9876;
    public static void main(String[] args) throws Exception{
        //new Messenger("localhost").send("hello, I'm fran");
//        MyServer s = new MyServer(port);
//        s.listen();
//        System.out.println("The server is listening in a different thread");
//        s.close();

        new Messenger("192.168.11.11",9876).send("https://cocacolahappiness.s3.amazonaws.com/1377183809867.gif&1377183809867".getBytes());
        System.out.println("Sent");
    }
    static class MyServer extends Server{

        public MyServer(int port) {
            super(port);
        }

        @Override
        public byte[] onRequest(byte[] reqString) {
            System.out.println("RECEIVED: "+new String(reqString));
            return "Shut up, I hate you".getBytes();
        }
    }


}
