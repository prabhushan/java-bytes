package com.prabhu.websockets.helloworld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{clientname}")
public class ChatServer {

	public static List<Session> listofUsers = new CopyOnWriteArrayList<Session>();

	@OnOpen
	public void onOpen(@PathParam("clientname") String name, Session session) throws IOException {
		// add new user
		listofUsers.add(session);

		broadCastMessage("User " + name + " joined the chat room");

	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		broadCastMessage(message);
	}

	@OnError
	public void onError(Throwable t, Session session) throws IOException {
		if (session.isOpen())
			session.getBasicRemote().sendText("Error Occured");
	}

	@OnClose
	public void onClose(@PathParam("clientname") String name,Session session) throws IOException {


		for (Session userSession : listofUsers) {
			if (userSession.getId().equals(session.getId())) {
				listofUsers.remove(userSession);
			}
		}
		broadCastMessage("User " + name + " Left the chat room");
	}

	private void broadCastMessage(String message) throws IOException {

		for (Session userSession : listofUsers) {
			if (userSession.isOpen()) {
				userSession.getBasicRemote().sendText(message);
			}
		}
	}

}
