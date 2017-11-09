package test.project1;

import io.vertx.core.*;
import io.vertx.core.http.*;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.*;
import io.vertx.ext.web.handler.BodyHandler;

public class Server extends AbstractVerticle {

	private Router router;
	private WordsDB myDB = new WordsDB (Integer.MAX_VALUE);;
	private String currWord;
	private HttpServer httpServer;

	@Override
	public void start(Future<Void> fut) throws Exception {

		router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		httpServer = vertx.createHttpServer();

		// handle only http POST requests from "analyze" URL with content-type header set to `json'
		router.route(HttpMethod.POST ,"/analyze/:text").consumes("json/*").handler(this::handlWord);

		httpServer.requestHandler(router::accept)
		.listen(
				config().getInteger("http.port", 8080),	result -> {
					if (result.succeeded()) {
						fut.complete();
					} else {
						fut.fail(result.cause());
					}
				});

	}

	private void handlWord(RoutingContext routingContext){
		// get the value of "text" from the request
		currWord = routingContext.request().getParam("text");

		// prepare the response
		JsonObject words = new JsonObject();
		words.put("value", myDB.findValue(currWord));
		words.put("lexical", myDB.findLexical(currWord));
		// save the word in the database
		myDB.add(currWord);

		HttpServerResponse response = routingContext.response();
		response.putHeader("content-type", "application/json").end(words.encodePrettily()); 
	}
	
	public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new Server());
    }

}
