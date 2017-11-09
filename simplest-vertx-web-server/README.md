# The Simplest Vert.x-web Server

"This project is licensed under the terms of the open source license."
This is a sample project to show the minimal deployment of a `vertx-web` based routing HTTP server.

java libraries:

 io.vertx.core.*;
 io.vertx.core.http.*;
 io.vertx.core.json.JsonObject;
 io.vertx.ext.web.*;
 io.vertx.ext.web.handler.BodyHandler;
 java.util.ArrayList;
 java.util.Collection;
 java.util.Collections;
 java.util.HashMap;
 java.util.Iterator;
 java.util.List;
 java.util.Map.Entry;

Maven build (via eclipse):
project --> 'Run As' --> Run Configurations --> 'new Lanuch Configuration' button --> Goal: clean install --> run

To run (via eclipse):
run button --> choose 'Server'

To test:

```
curl http://localhost:8080/
```
