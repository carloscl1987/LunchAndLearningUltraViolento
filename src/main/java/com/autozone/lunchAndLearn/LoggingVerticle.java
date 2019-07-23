package com.autozone.lunchAndLearn;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

public class LoggingVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    EventBus bus = vertx.eventBus();  
   
    bus.<JsonObject>consumer("log", this::logMessage);
  }
  
  private void logMessage(Message<JsonObject> message) {
	  if(null != message.body().getString("message")) {
		  System.out.println( this.getClass().getName() + ": " + message.body().getString("message"));
	  }
  }
  
}
