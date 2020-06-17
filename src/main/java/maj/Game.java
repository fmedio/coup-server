package maj;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;
import org.apache.commons.collections4.iterators.LoopingIterator;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public class Game {
    public enum State {
        enrollingPlayers,
        started,
        finished
    }

    private long id;
    private final LinkedHashMap<Player, Hand> playerMap;
    private PeekingIterator<Player> circularIterator;
    private State state;

    public Game(long id) {
        this.id = id;
        playerMap = new LinkedHashMap<Player, Hand>();
        state = State.enrollingPlayers;
        circularIterator = Iterators.peekingIterator(Collections.emptyIterator());
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


    public State getState() {
        return state;
    }

    public void start() {
        state = State.started;
        circularIterator = Iterators.peekingIterator(new LoopingIterator<>(playerMap.keySet()));
    }

    public Hand getHand(Player player) {
        if (!state.equals(State.started)) {
            throw new IllegalStateException("Game hasn't started yet!");
        }
        return playerMap.get(player);
    }

    public Optional<Player> getCurrentPlayer() {
        try {
            if (circularIterator.hasNext()) {
                return Optional.of(circularIterator.peek());
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Player> nextTurn() {
        circularIterator.next();
        return getCurrentPlayer();
    }

    public Player play(Player player, String action) {
        if (!state.equals(State.started)) {
            throw new IllegalStateException("Game hasn't started yet!");
        }


        Optional<Player> currentPlayer = getCurrentPlayer();
        if (currentPlayer.isPresent()) {
            if (!currentPlayer.get().equals(player)) {
                throw new IllegalStateException("It isn't " + player.getDisplayName() + "'s turn yet");
            }
        }

        nextTurn();
        return player;
    }
}

