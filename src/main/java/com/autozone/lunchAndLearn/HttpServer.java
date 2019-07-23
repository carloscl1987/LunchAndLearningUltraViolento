package com.autozone.lunchAndLearn;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;

public class HttpServer extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		vertx.createHttpServer()
		.requestHandler(this::getPurchases)
		.listen(8080);
	}
	
	private void getPurchases(HttpServerRequest request) {
		vertx.eventBus().publish("log", new JsonObject().put("message", "Request invoked by customer"));
		vertx.eventBus().<JsonObject>request("get.purchases",null, reply -> {
			if (reply.succeeded()) {
				request.response().end("Number of purchases so far: " + reply.result().body().getInteger("purchases"));
			}
		});
	}
}
