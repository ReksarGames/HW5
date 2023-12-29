package com.danit.springrest.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j

public class HandlerSocket extends TextWebSocketHandler {
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws InterruptedException, IOException {
        String payload = message.getPayload();
        Map<String, String> valuesMap = new ObjectMapper().readValue(payload, Map.class);
        String messageType = valuesMap.get("type");
        if (messageType == null) {
            String name = valuesMap.get("name");
            session.sendMessage(new TextMessage("Hello " + name));
            return;
        }

        log.info(payload);
        String textJson = "{\"data\": \"Hello " + "user " + session.getPrincipal().getName() + " !\"}";
        session.sendMessage(new TextMessage(textJson));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Connection closed by {}:{}", session.getRemoteAddress().getHostString(), session.getRemoteAddress().getPort());
        super.afterConnectionClosed(session, status);
    }
}
