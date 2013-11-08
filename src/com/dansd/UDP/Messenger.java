package com.dansd.UDP;

import java.io.IOException;
import java.net.*;


public class Messenger extends Thread{
    private int sessionPort;
    private String sessionHost;
    private byte[] currentMessage;

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
    public void send(byte[] message){
        this.currentMessage = message;
        this.run();
    }

    public void run(){
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        InetAddress IP = null;
        try {
            IP = InetAddress.getByName(sessionHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        byte[] receiveData = new byte[1024];
        byte[] sendData = currentMessage;
        DatagramPacket sendPacket  = new DatagramPacket(sendData, sendData.length, IP, sessionPort);
        try {
            clientSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            clientSocket.receive(receivePacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        receiveData = receivePacket.getData();
        onResponse(receiveData);
        clientSocket.close();
    }

    //User should override this
    public void onResponse(byte[] response){
        System.out.println("RESPONSE: "+new String(response));
    }

}
