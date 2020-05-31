package maj;
import static spark.Spark.get;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;
import spark.Route;

public class Main {
    public static void main(String[] args) {
        final Game game = new Game(1);


        get("/games/:id", new Route() {
            public Object handle(Request request, Response response) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                return gson.toJson(game);
            }
        });
    }
}