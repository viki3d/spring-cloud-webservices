package com.viki3d.spring.cloud.webservices.services;

import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Provides the actual port on which this microservice instance is running.
 */
@Service
public class AppPortService {

  private int port;

  public int getPort() {
    return port;
  }

  @EventListener
  public void onApplicationEvent(final ServletWebServerInitializedEvent event) {
    port = event.getWebServer().getPort();
  }
  
}
