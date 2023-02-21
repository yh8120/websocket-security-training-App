package com.example.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;


@Configuration
public class WebSocketSecurityConfig
		extends AbstractSecurityWebSocketMessageBrokerConfigurer {

	protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
		messages
				.simpTypeMatchers(SimpMessageType.CONNECT,
						SimpMessageType.HEARTBEAT,
						SimpMessageType.UNSUBSCRIBE,
						SimpMessageType.DISCONNECT,
						SimpMessageType.OTHER)
				.permitAll()
				.simpDestMatchers("/user/*", "/topic/notice/*").hasRole("USER")
				.simpSubscribeDestMatchers("/user/*", "/topic/notice/*").hasRole("USER")
				.anyMessage().authenticated(); 
				;
	}
}