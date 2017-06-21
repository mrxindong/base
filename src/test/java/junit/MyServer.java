package junit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

	public static List<Socket> socketList = new ArrayList<Socket>();

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(30000);
		while (true) {
			Socket s = ss.accept();
			socketList.add(s);
			 new Thread(new ServerThread(s)).start();
		}
	}

}

class ServerThread implements Runnable {
	Socket s = null;
	BufferedReader br = null;

	public ServerThread(Socket s) throws IOException {
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
	}

	@Override
	public void run() {
		try {

			String content = null;
			while ((content = readFromClient()) != null) {
				for (Socket s : MyServer.socketList) {
					PrintStream ps=new PrintStream(s.getOutputStream());
					ps.println(content);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String readFromClient() {
		try {
			return br.readLine();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
