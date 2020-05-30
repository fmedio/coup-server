package maj;
import static spark.Spark.get;

import spark.Request;
import spark.Response;
import spark.Route;

public class HelloWorld {
    public static void main(String[] args) {
        Route route = new Route() {
            public Object handle(Request request, Response response) {
                return "The url parameter for name is " + request.params(":name");
            }
        };


        get("/user/:name", route);
    }
}