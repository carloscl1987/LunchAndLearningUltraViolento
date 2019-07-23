package com.autozone.lunchAndLearn;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

public class PurchaseProcessor extends AbstractVerticle {
  @Override
  public void start(){
    vertx.eventBus().<JsonObject>consumer("purchase.process", this::processPurchase);
  }
  
  private void processPurchase(Message<JsonObject> message) {
    vertx.setTimer(5000, processHandler ->{
      vertx.eventBus().publish("log", new JsonObject().put("message", " Processing Order #"+ message.body().getString("id") ));
      message.reply(message.body());
    });
  }
}
