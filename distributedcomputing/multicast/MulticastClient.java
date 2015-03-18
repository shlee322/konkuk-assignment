import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * 멀티캐스트 클라이언트
 *
 * 분산컴퓨팅및시스템 멀티캐스트 구현 과제
 *
 * @author Lee Sanghyuck (shlee322@elab.kr)
 * @since 2015.03.18
 * @link https://github.com/shlee322/konkuk-assignment/tree/master/distributedcomputing/multicast/
 *
 */
public class MulticastClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        InetAddress multicastAddress = Common.getMulticastAddress();

        MulticastSocket socket = new MulticastSocket(Common.getPort());
        socket.joinGroup(multicastAddress);

        byte[] buffer = new byte[2048];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        while (true) {
            socket.receive(packet);
            System.out.println(new String(packet.getData(), 0, packet.getLength()));
        }

        //socket.leaveGroup(multicastAddress); // 원래는 그룹 나가야 하지만 어차피 무한루프라 주석
    }
}
