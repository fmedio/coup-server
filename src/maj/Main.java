package maj;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static spark.Spark.get;

public class Main {

    public static void main(String[] args) {
        final Board board = new Board();
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();

        get("/createGame", (request, response) -> {
            response.type("application/json");
            return gson.toJson(board.createGame());
        });

        get("/createPlayer/:displayName", (request, response) -> {
            response.type("application/json");
            String displayName = request.params(":displayName");
            Player player = board.createPlayer(displayName);
            return gson.toJson(player);
        });


        get("/games", (request, response) -> {
            response.type("application/json");
            return gson.toJson(board.gameIds());
        });


        get("/players", (request, response) -> {
            response.type("application/json");
            return gson.toJson(board.playerIds());
        });

        get("/players/:id", (req, res) -> {
            res.type("application/json");
            Player player = board.getPlayer(Long.parseLong(req.params(":id")));
            return gson.toJson(player);
        });

        get("/games/:id", (request, response) -> {
            response.type("application/json");
            Game game = board.getGame(Long.parseLong(request.params(":id")));
            if (game == null) {
                return "No such game";
            }
            return gson.toJson(game);
        });

        get("/games/:id/start", (request, response) -> {
            response.type("application/json");
            Game game = board.getGame(Long.parseLong(request.params(":id")));
            if (game == null) {
                return "No such game";
            }
            game.start();
            return gson.toJson(game);
        });

        get("/play/:gameId/:playerId/:actionName", (request, response) -> {
            response.type("application/json");
            Game game = board.getGame(Long.parseLong(request.params(":gameId")));
            if (game == null) {
                return "No such game";
            }

            Player player = board.getPlayer(Long.parseLong(request.params(":playerId")));
            String actionName = request.params("actionName");
            game.play(player, actionName);
            return gson.toJson(game);
        });

        get("/addPlayer/:gameId/:playerId", (request, response) -> {
            response.type("application/json");
            Game game = board.getGame(Long.parseLong(request.params(":gameId")));
            Player player = board.getPlayer(Long.parseLong(request.params(":playerId")));
            game.addPlayer(player);
            return gson.toJson(game);
        });
    }
}