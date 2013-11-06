package com.dansd.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPTest {
    static int port = 9876;
    public static void main(String[] args) throws Exception{
        send("Hey dude!");
    }
    private static void send(String stringToSend) throws Exception{
        System.out.println("Sending");
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IP = InetAddress.getByName("192.168.11.13");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        sendData = stringToSend.getBytes();
        DatagramPacket sendPacket  = new DatagramPacket(sendData, sendData.length, IP, port);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String responseString = new String(receivePacket.getData());
        System.out.println("RESPONSE: "+responseString);
        clientSocket.close();

    }
    private static void listen() throws Exception{
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
