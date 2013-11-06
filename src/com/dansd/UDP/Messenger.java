package com.dansd.UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created with IntelliJ IDEA.
 * User: franciscodans
 * Date: 06/11/2013
 * Time: 13:03
 * To change this template use File | Settings | File Templates.
 */
public class Messenger {
    private int sessionPort;
    private String sessionHost;

    public Messenger(){
        this("localhost");
    }
    public Messenger(String host){
        this(host, 9876);
    }
    public Messenger(String host, int port){
        sessionPort = port;
        sessionHost = host;
    }
    public void send(String message){
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        InetAddress IP = null;
        try {
            IP = InetAddress.getByName(sessionHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        byte[] receiveData = new byte[1024];
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket  = new DatagramPacket(sendData, sendData.length, IP, sessionPort);
        try {
            clientSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            clientSocket.receive(receivePacket);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        String responseString = new String(receivePacket.getData());
        onResponse(responseString);
        clientSocket.close();
        }

    //User should override this
    public void onResponse(String response){
        System.out.println("RESPONSE: "+response);
    }

}
