package maj;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Path("/")
public class RestEndpoint {
    @Inject
    private Board board;


    public RestEndpoint() {
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

    @GET
    @Path("/players/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayer(@PathParam("id") Long id) {
        return Response.status(Status.OK).entity(board.getPlayer(id)).build();
    }

    @GET
    @Path("/games")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listGames() {
        List<Long> ids = board.games().stream().map(g -> g.getId()).collect(Collectors.toList());
        return Response.status(Status.OK).entity(ids).build();
    }

    @POST
    @Path("/games")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGame() {
        Game game = board.createGame();
        return Response.status(Status.CREATED).entity(game).build();
    }

    @GET
    @Path("/games/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGame(@PathParam("id") Long id) {
        return Optional.ofNullable(board.getGame(id))
                .map(game -> Response.status(Status.OK).entity(game).build())
                .orElse(Response.status(Status.NOT_FOUND).build());
    }

    @PATCH
    @Path("/games/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response startGame(@PathParam("id") Long id) {
        return board.getGame(id)
                .map(game -> {
                    if (game.getPlayerMap().size() == 0) {
                        return Response.status(Status.BAD_REQUEST).entity("\"This game has no players\"").build();
                    } else {
                        game.start();
                        return Response.status(Status.OK).entity(game).build();
                    }
                }).orElse(Response.status(Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/games/{gameId}/{playerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlayerToGame(@PathParam("gameId") Long gameId, @PathParam("playerId") Long playerId) {
        return board.getGame(gameId)
                .flatMap(game -> board.getPlayer(playerId)
                        .map(player -> game.addPlayer(player))
                        .map(player -> Response.status(Status.OK).build()))
                .orElse(Response.status(Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/games/{gameId}/{playerId}/{actionName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response play(@PathParam("gameId") Long gameId,
                         @PathParam("playerId") Long playerId,
                         @PathParam("actionName") String actionName) {

        return board.getGame(gameId)
                .flatMap(game -> board.getPlayer(playerId)
                        .map(player -> game.play(player, actionName))
                        .map(player -> Response.status(Status.OK).entity(game).build()))
                .orElse(Response.status(Status.NOT_FOUND).build());
    }
}
