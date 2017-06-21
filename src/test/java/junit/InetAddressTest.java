package junit;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws IOException {
		InetAddress ip=InetAddress.getByName("www.oneedu.cn");
		System.out.println(ip.isReachable(2000));
		System.out.println(ip.getHostAddress());
		InetAddress local=InetAddress.getByAddress(new byte[]{127,0,0,1});
		System.out.println("local:"+local.isReachable(5000));
		System.out.println(local.getCanonicalHostName());
	}

}
