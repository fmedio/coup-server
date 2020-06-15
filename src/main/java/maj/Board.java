package maj;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Board {
    private final Map<Long, Game> games;
    private final AtomicLong maxGameId;

    private final Map<Long, Player> players;
    private final AtomicLong maxPlayerId;

    public Board() {
        games = new HashMap<Long, Game>();
        maxGameId = new AtomicLong(0);
        players = new HashMap<Long, Player>();
        maxPlayerId = new AtomicLong(0);
    }

    public Game createGame() {
        Game game = new Game(maxGameId.getAndIncrement());
        games.put(game.getId(), game);
        return game;
    }

    public Game getGame(long id) {
        return games.get(id);
    }

    public Collection<Game> games() {
        return games.values();
    }

    public Collection<Player> players() {
        return players.values();
    }

    public Player createPlayer(String displayName) {
        Player player = new Player(maxPlayerId.getAndIncrement(), displayName);
        players.put(player.getId(), player);
        return player;
    }

    public Player getPlayer(long id) {
        return players.get(id);
    }
}
