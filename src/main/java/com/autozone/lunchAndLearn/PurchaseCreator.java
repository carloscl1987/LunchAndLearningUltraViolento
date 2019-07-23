package com.autozone.lunchAndLearn;


import java.util.Date;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

public class PurchaseCreator extends AbstractVerticle {

	private int numberOfPurchases = 0;
	
  @Override
  public void start() throws Exception {

	  EventBus bus = vertx.eventBus();
	  
	  bus.consumer("get.purchases", hndlr -> {
		  hndlr.reply(new JsonObject().put("purchases",numberOfPurchases));
	  });
	  
    vertx.setPeriodic(2000, id -> {
       Long pid =   new Date().getTime();
	   bus.publish("log", new JsonObject().put("message", "Purchase created with id: " + pid.toString() ));
	   
	   bus.<JsonObject>request("purchase.process", new JsonObject().put("id", pid.toString() ), result -> {
		  
		   System.out.println("Order #"+ result.result().body().getString("id") + " processed");
		   
	   });
	   
	   numberOfPurchases++;
    });
  }
}
