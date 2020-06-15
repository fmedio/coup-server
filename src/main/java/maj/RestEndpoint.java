package maj;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("/")
public class RestEndpoint {
    @Inject
    private Board board;


    public RestEndpoint() {
    }

    @GET
    @Path("/players/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayer(@PathParam("id") Integer id) {
        return Response.status(Status.OK).entity(board.getPlayer(id)).build();
    }

    @GET
    @Path("/players")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayers() {
        return Response.status(Status.OK).entity(board.players()).build();
    }

    @POST
    @Path("/players")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPlayer(String name) {
        Player player = board.createPlayer(name);
        return Response.status(Status.CREATED).entity(player).build();
    }


//        get("/createGame", (request, response) -> {
//            response.type("application/json");
//            return gson.toJson(board.createGame());
//        });
//
//        get("/createPlayer/:displayName", (request, response) -> {
//            response.type("application/json");
//            String displayName = request.params(":displayName");
//            Player player = board.createPlayer(displayName);
//            return gson.toJson(player);
//        });
//
//
//        get("/games", (request, response) -> {
//            response.type("application/json");
//            return gson.toJson(board.gameIds());
//        });
//
//
//        get("/players", (request, response) -> {
//            response.type("application/json");
//            return gson.toJson(board.playerIds());
//        });
//
//        get("/players/:id", (req, res) -> {
//            res.type("application/json");
//            Player player = board.getPlayer(Long.parseLong(req.params(":id")));
//            return gson.toJson(player);
//        });
//
//        get("/games/:id", (request, response) -> {
//            response.type("application/json");
//            Game game = board.getGame(Long.parseLong(request.params(":id")));
//            if (game == null) {
//                return "No such game";
//            }
//            return gson.toJson(game);
//        });
//
//        get("/games/:id/start", (request, response) -> {
//            response.type("application/json");
//            Game game = board.getGame(Long.parseLong(request.params(":id")));
//            if (game == null) {
//                return "No such game";
//            }
//            game.start();
//            return gson.toJson(game);
//        });
//
//        get("/play/:gameId/:playerId/:actionName", (request, response) -> {
//            response.type("application/json");
//            Game game = board.getGame(Long.parseLong(request.params(":gameId")));
//            if (game == null) {
//                return "No such game";
//            }
//
//            Player player = board.getPlayer(Long.parseLong(request.params(":playerId")));
//            String actionName = request.params("actionName");
//            game.play(player, actionName);
//            return gson.toJson(game);
//        });
//
//        get("/addPlayer/:gameId/:playerId", (request, response) -> {
//            response.type("application/json");
//            Game game = board.getGame(Long.parseLong(request.params(":gameId")));
//            Player player = board.getPlayer(Long.parseLong(request.params(":playerId")));
//            game.addPlayer(player);
//            return gson.toJson(game);
//        });
}
