package maj;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.ResettableIterator;

import com.google.common.collect.Lists;

public class Game {
    public enum State {
        enrollingPlayers,
        started,
        finished
    }

    private long id;
    private final LinkedHashMap<Player, Hand> playerMap;
    private ResettableIterator<Player> circularIterator;
    private final AtomicReference<Player> currentPlayer;
    private State state;

    public Game(long id) {
        this.id = id;
        playerMap = new LinkedHashMap<Player, Hand>();
        currentPlayer = new AtomicReference<>();
        state = State.enrollingPlayers;
    }

    public Player addPlayer(Player player) {
        if (! state.equals(State.enrollingPlayers)) {
            throw new IllegalStateException("Game has started, cannot add players!");
        }

        List<Card> cards = Lists.newArrayList(Card.deal(), Card.deal());
        Hand hand = new Hand(cards, 1);
        playerMap.put(player, hand);
        return player;
    }

    public long getId() {
        return id;
    }

    public LinkedHashMap<Player, Hand> getPlayerMap() {
        return playerMap;
    }

    public AtomicReference<Player> getCurrentPlayer() {
        return currentPlayer;
    }

    public State getState() {
        return state;
    }

    public void start() {
        state = State.started;
        circularIterator = IteratorUtils.loopingIterator(playerMap.keySet());
        currentPlayer.set(circularIterator.next());
    }

    public Hand getHand(Player player) {
        if (!state.equals(State.started)) {
            throw new IllegalStateException("Game hasn't started yet!");
        }
        return playerMap.get(player);
    }

    public Player currentPlayer() {
        if (! state.equals(State.started)) {
            throw new IllegalStateException("Game hasn't started yet!");
        }

        return currentPlayer.get();
    }

    public Player nextTurn() {
        currentPlayer.set(circularIterator.next());
        return currentPlayer.get();
    }

    public void play(Player player, String action) {
        if (!state.equals(State.started)) {
            throw new IllegalStateException("Game hasn't started yet!");
        }

        if (!currentPlayer.get().equals(player)) {
            throw new IllegalStateException("It isn't " + player.getDisplayName() + "'s turn yet");
        }

        nextTurn();
    }
}

