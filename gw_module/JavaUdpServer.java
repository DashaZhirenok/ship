import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;


public class JavaUdpServer {


    static UdpServerThread udpServerThread;
    public static void main(String[] args) throws IOException {
        System.out.println("Server start");
        System.out.println("Runtime Java: "
                + System.getProperty("java.runtime.version"));


        new UdpServerThread().start();
    }

    private static class UdpServerThread extends Thread{

        final int serverport = 4445;

        protected DatagramSocket socket = null;

        public UdpServerThread() throws IOException {
            this("UdpServerThread");
        }

        public UdpServerThread(String name) throws IOException {
            super(name);
            socket = new DatagramSocket(serverport);
            System.out.println("JavaUdpServer run on: " + serverport);
        }

        @Override
        public void run() {

            while(true){

                try {
                    byte[] buf = new byte[256];

                    // receive data from Matlab
					int portMatlab = 8845;
					InetAddress addressMatlab = InetAddress.getByName("192.168.0.103");
					
                    DatagramPacket packetMatlab = new DatagramPacket(buf, buf.length, addressMatlab, portMatlab);
                    socket.receive(packetMatlab);
					String lineMatlab = new String(packetMatlab.getData(), 0, packetMatlab.getLength());

                    // send the response to the client at "address" and "port"
                    // InetAddress address = packet.getAddress();
                    // int port = packet.getPort();
					
                    System.out.println("Request from: " + packetMatlab.getAddress() + ":" + packetMatlab.getPort() + " data: " + lineMatlab);
					InetAddress addressMobile = InetAddress.getByName("192.168.0.101");
					
					// send to Mobile
                    int portMobile = 2245;
					String dString = lineMatlab;
                    buf = dString.getBytes();
					
                    packetMatlab = new DatagramPacket(buf, buf.length, addressMobile, portMobile);
                    socket.send(packetMatlab);
					
					// receive data from mobile
					buf = new byte[256];
					DatagramPacket packetMobile = new DatagramPacket(buf, buf.length, addressMobile, portMobile);
					socket.receive(packetMobile);
					String lineMobile = new String(packetMobile.getData(), 0, packetMobile.getLength());
					
					System.out.println("Request from: " + packetMobile.getAddress() + ":" + packetMobile.getPort() + " data: " + lineMobile);
					
					// send to Matlab(angle + speed)
					dString = lineMobile;
                    buf = dString.getBytes();
					
					packetMatlab = new DatagramPacket(buf, buf.length, addressMatlab, portMatlab);
                    socket.send(packetMatlab);

                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }

            }

        }

    }

}
