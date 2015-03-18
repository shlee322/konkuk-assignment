import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 멀티캐스트 공통 클래스
 *
 * 분산컴퓨팅및시스템 멀티캐스트 구현 과제
 *
 * @author Lee Sanghyuck (shlee322@elab.kr)
 * @since 2015.03.18
 * @link https://github.com/shlee322/konkuk-assignment/tree/master/distributedcomputing/multicast/
 *
 */
public class Common {
    /**
     * 멀티캐스트 Group Address 리턴
     *
     * Group Address : 1110 xxxx . x. x . x -> 224.0.0.0 ~ 239.255.255.255
     *
     * @return Group Address
     * @throws UnknownHostException
     */
    public static InetAddress getMulticastAddress() throws UnknownHostException {
        return InetAddress.getByName("239.239.239.239");
    }

    public static int getPort() {
        return 7777;
    }
}
