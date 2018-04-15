package com.example.dasha.diploma2;

/**
 * Created by dasha on 15.04.2018.
 */

import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpClientThread extends Thread{

    String dstAddress;
    int dstPort;
    public String cur_msg = "NUUUULL";
    UdpClientHandler handler;

    DatagramSocket socket;
    InetAddress address;

    public UdpClientThread(String addr, int port, UdpClientHandler handler) {
        super();
        dstAddress = addr;
        dstPort = port;
        this.handler = handler;
    }

    public void setMsgToServer(String cur_msg){
        this.cur_msg = cur_msg;
    }

    @Override
    public void run() {
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName(dstAddress);

            // send request
            byte[] buf = new byte[256];
            Log.v("tag", "from client: " + cur_msg);
            buf = cur_msg.getBytes();

            DatagramPacket packet =
                    new DatagramPacket(buf, buf.length, address, dstPort);
            socket.send(packet);

            // get response
            buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);

            socket.receive(packet);
            String line = new String(packet.getData(), 0, packet.getLength());

            Log.v("tag", "from sever: " + line);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket != null){
                socket.close();
                handler.sendEmptyMessage(UdpClientHandler.UPDATE_END);
            }
        }

    }
}