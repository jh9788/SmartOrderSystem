package com.SOS.SmartOrderSystem.controller;

import com.SOS.SmartOrderSystem.domain.dto.OrderRequest;
import com.SOS.SmartOrderSystem.domain.dto.ReceiveRequest;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
@Controller
public class WebSocketHandler extends TextWebSocketHandler {

    /**
     * 서버에서 주문넣으면 상품번호, 가게번호, 테이블번호 넘겨줌
     *
     */
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ReceiveRequest greeting(OrderRequest request) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        System.out.println("request = " + request.getMenu());
        return new ReceiveRequest(request.getMenu(), 1, time);
    }
}