import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * 멀티캐스트 서버
 *
 * 분산컴퓨팅및시스템 멀티캐스트 구현 과제
 *
 * @author Lee Sanghyuck (shlee322@elab.kr)
 * @since 2015.03.18
 * @link https://github.com/shlee322/konkuk-assignment/tree/master/distributedcomputing/multicast/
 *
 */
public class MulticastServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        byte[] sendMessage = "test message!!".getBytes();

        InetAddress multicastAddress = Common.getMulticastAddress();

        DatagramPacket packet = new DatagramPacket(sendMessage, sendMessage.length, multicastAddress, Common.getPort());

        MulticastSocket socket = new MulticastSocket();
        while (true) {
            socket.send(packet);
            Thread.sleep(1000);
        }
    }
}
