package com.dansd.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPTest {
    static int port = 9876;
    public static void main(String[] args) throws Exception{
        DatagramSocket serverSocket = new DatagramSocket(port);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[24];
        System.out.println("Listening to port "+ port);
        while(true){
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String receivedString = new String(receivePacket.getData());
            System.out.println("RECEIVED: "+receivedString);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String responseString = "Shut up you imbecile";
            sendData = responseString.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(responsePacket);
        }
    }
}
