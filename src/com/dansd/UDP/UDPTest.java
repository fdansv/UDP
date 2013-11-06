package com.dansd.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPTest {
    static int port = 9876;
    public static void main(String[] args) throws Exception{
        new Messenger("localhost").send("hello, I'm fran");
    }


}
