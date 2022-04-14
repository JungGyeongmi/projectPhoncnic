package ds.com.phoncnic.handler;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SwoomiWebSocketHandler extends TextWebSocketHandler{
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
    String payload = message.getPayload();

    TextMessage initialGreeting = new TextMessage("Welcome to 굴러굴러 왕구슬 server~");
    session.sendMessage(initialGreeting);
    }
}
