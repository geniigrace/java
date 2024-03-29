package ManySocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

public class ChatSeverThread implements Runnable {
	Socket child;
	BufferedReader ois;
	PrintWriter oos;
	
	String user_id;
	HashMap<String, PrintWriter> hm;
	InetAddress ip;
	String msg;
	
	public ChatSeverThread(Socket s, HashMap<String, PrintWriter> h) {
		child = s;
		hm = h;
		try {
			ois = new BufferedReader(new InputStreamReader(child.getInputStream()));
			oos = new PrintWriter(child.getOutputStream());
			
			user_id = ois.readLine();
			
			ip=child.getInetAddress();
			
			
			System.out.println(ip+" 로 부터 "+user_id+" 님이 접속했습니다.");
			broadcast(user_id+" 님이 접속했습니다. ");
			
			synchronized(hm) {
				hm.put(user_id, oos); //다중접속이 일어날 수 있으므로 관리해야할 자료는 임계영역으로 지정하여 관리 
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		String receiveData;
		
		try {
			while( (receiveData = ois.readLine())!=null) {
				if(receiveData.equals("/quit")) {
					synchronized(hm) {
						hm.remove(user_id);
					}
					break;
				}
				
				else if(receiveData.indexOf("/to")>=0) {
					sendMsg(receiveData);
				}
				else {
					System.out.println(user_id + " >> " + receiveData);
					broadcast(user_id + " >> " + receiveData);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			synchronized(hm) {
				hm.remove(user_id);
				}
			broadcast(user_id+" 님이 퇴장했습니다.");
			System.out.println(user_id+" 님이 퇴장했습니다.");
			
			try {
				if(child != null) {
					ois.close();
					oos.close();
				}
			}
			catch(Exception e) {}
		}
	}

	public void broadcast(String message) {
		synchronized(hm) {
			try {
				
				for(PrintWriter oos : hm.values()) {
					oos.println(message);
					oos.flush();
				}
				
			}
			catch (Exception e) {}
		}
	}
	
	public void sendMsg(String message) {
		int begin = message.indexOf(" ")+1;
		int end = message.indexOf(" ", begin);
		
		if(end != -1) { //귓속말 보낼 아이디를 추출 
			String id = message.substring(begin, end);
			String msg = message.substring(end+1);
			PrintWriter oos = hm.get(id);
			
			try {
				if(oos != null) {
					oos.println(user_id+ " 님이 다음과 같은 귓속말을 보내셨습니다. : "+msg);
					
					oos.flush();
				}
			}
			catch(Exception e) {}
		}
		
	}
	
}
