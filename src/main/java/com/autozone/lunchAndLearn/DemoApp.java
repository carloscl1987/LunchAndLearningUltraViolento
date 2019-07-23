package com.autozone.lunchAndLearn;

import io.vertx.core.Vertx;

public class DemoApp {

	public static void main(String[] args) {
		  Vertx vertx = Vertx.vertx();
		  vertx.deployVerticle(new PurchaseCreator());
		  vertx.deployVerticle(new HttpServer());
		  vertx.deployVerticle(new LoggingVerticle());
		  vertx.deployVerticle(new PurchaseProcessor());
	  }
}
