package maj;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static spark.Spark.get;

public class Main {

    public static void main(String[] args) {
        final GameController gameController = new GameController();
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();

        get("/createGame", (request, response) -> {
            response.type("application/json");
            return gson.toJson(gameController.createGame());
        });

        get("/createPlayer/:displayName", (request, response) -> {
            response.type("application/json");
            String displayName = request.params(":displayName");
            Player player = gameController.createPlayer(displayName);
            return gson.toJson(player);
        });


        get("/games", (request, response) -> {
            response.type("application/json");
            return gson.toJson(gameController.gameIds());
        });


        get("/players", (request, response) -> {
            response.type("application/json");
            return gson.toJson(gameController.playerIds());
        });

        get("/players/:id", (req, res) -> {
            res.type("application/json");
            Player player = gameController.getPlayer(Long.parseLong(req.params(":id")));
            return gson.toJson(player);
        });

        get("/games/:id", (request, response) -> {
            response.type("application/json");
            Game game = gameController.getGame(Long.parseLong(request.params(":id")));
            if (game == null) {
                return "No such game";
            }
            return gson.toJson(game);
        });

        get("/addPlayer/:gameId/:playerId", (request, response) -> {
            response.type("application/json");
            Game game = gameController.getGame(Long.parseLong(request.params(":gameId")));
            Player player = gameController.getPlayer(Long.parseLong(request.params(":playerId")));
            game.addPlayer(player);
            return gson.toJson(game);
        });
    }
}