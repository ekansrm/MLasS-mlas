package com.ekansrm.mlas.controller.root;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;

/**
 * 配置WebSocket
 */
@Configuration
//注解开启使用STOMP协议来传输基于代理(message broker)的消息,这时控制器支持使用@MessageMapping,就像使用@RequestMapping一样
@EnableAutoConfiguration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(stockWebSocketHandler(), "/stocks").setAllowedOrigins("*").withSockJS();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new MvcConfig();
  }

  @Bean
  public WebSocketHandler stockWebSocketHandler() {
    return new PerConnectionWebSocketHandler(StockWebSocketHandler.class);
  }

  @Bean
  public BroadcastHandler createBroadcastHandler() {
    return new BroadcastHandler();
  }

}
