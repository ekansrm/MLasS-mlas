package com.ekansrm.mlas.controller.root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BroadcastHandler {

  private static final Logger logger = LoggerFactory.getLogger(BroadcastHandler.class);

  private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

  private final Object MONITOR = new Object();

  public void add(WebSocketSession session) {
    synchronized (MONITOR) {
      if (!sessions.contains(session)) sessions.add(session);
    }
  }

  public void broadcast(String message) {
    System.out.println("Start Broadcasting");
    for (WebSocketSession session : sessions) {
      try {
        if (session.isOpen()) session.sendMessage(new TextMessage(message));
      } catch (IOException e) {
        System.out.println("Error in sending message for session " + session.getId());
      }
    }
    System.out.println("Finished Broadcasting");
    System.out.println("Total active sessions " + sessions.size());
  }

  public void remove(WebSocketSession session) {
    synchronized (MONITOR) {
      System.out.println("REMOVING channel: " + session.toString());
      sessions.remove(session);
    }
  }
}
