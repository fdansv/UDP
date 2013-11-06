package com.dansd.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created with IntelliJ IDEA.
 * User: franciscodans
 * Date: 06/11/2013
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
public class Server {
    public static void main(String args[]) throws Exception{
        int thisPort = 9876;
        DatagramSocket serverSocket = new DatagramSocket(thisPort);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[24];
        System.out.println("Listening to port "+ thisPort);
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
