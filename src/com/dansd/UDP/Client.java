package com.dansd.UDP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created with IntelliJ IDEA.
 * User: franciscodans
 * Date: 06/11/2013
 * Time: 13:03
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String args[]) throws Exception{
        String data = "";

        while(!data.equals("bye")){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            data = br.readLine();
            int thisPort = 9876;
            System.out.println("Sending");
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IP = InetAddress.getByName("169.254.235.72");
            byte[] receiveData = new byte[1024];
            byte[] sendData = new String(data).getBytes();
            DatagramPacket sendPacket  = new DatagramPacket(sendData, sendData.length, IP, thisPort);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String responseString = new String(receivePacket.getData());
            System.out.println("RESPONSE: "+responseString);
            clientSocket.close();
            data = "";
        }

    }
}
