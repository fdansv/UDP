package com.dansd.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created with IntelliJ IDEA.
 * User: franciscodans
 * Date: 06/11/2013
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
public class Server extends Thread{
    private int serverPort = 9876;

    public Server(int port){
        serverPort = port;
    }

    public void listen(){
        this.start();
    }

    //User should override this
    public byte[] onRequest(byte[] reqString){
        byte[] response = "".getBytes();
        System.out.println("RECEIVED: " + reqString);
        return response;
    }

    @Override
    public void run() {
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(serverPort);
        } catch (SocketException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        while(true){
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[24];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                serverSocket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            receiveData = receivePacket.getData();
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            sendData = onRequest(receiveData);
            DatagramPacket responsePacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            try {
                serverSocket.send(responsePacket);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

    }
}
