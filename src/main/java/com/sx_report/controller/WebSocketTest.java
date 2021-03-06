package com.sx_report.controller;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.MessageHandler.Whole;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.tomcat.websocket.WsSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@ServerEndpoint("/websocketTest")
public class WebSocketTest {
    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        // Print the client message for testing purposes
        System.out.println("Received: " + message);
        
        Map<String, List<String>> requestMap=session.getRequestParameterMap();
        for (Map.Entry<String, List<String>> entry : requestMap.entrySet()) {
			System.out.println(entry.getKey()+":::"+entry.getValue());
		}

        // Send the first message to the client
        session.getBasicRemote().sendText("This is the first server message");

        // Send 3 messages to the client every 5 seconds
        int sentMessages = 0;
        while (sentMessages < 3) {
            Thread.sleep(5000);
            session.getBasicRemote().sendText("This is an intermediate server message. Count: " + sentMessages);
            sentMessages++;
        }

        // Send a final message to the client
        session.getBasicRemote().sendText("This is the last server message");
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
    	Map<String, Object> userM=config.getUserProperties();
    	WsSession wSession=(WsSession) session;
    	wSession.getHttpSessionId();
    	session.addMessageHandler(new handler());
    	for (Map.Entry<String, Object> entry :  userM.entrySet()) {
    		 System.out.println(entry.getKey()+":::"+entry.getValue());
		}
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("Connection closed");
    }
    @OnError
    public void onError(Session session, Throwable thr) {
    	System.out.println(thr.toString());
    }   
    
}

class handler implements Whole<String>{

	@Override
	public void onMessage(String message) {
		 System.out.println("Handler is doing: " + message);
		
	}
	
}