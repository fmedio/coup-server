package maj;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Game {
    private long id;
    private LinkedHashMap<Player, Hand> players;
    private AtomicReference<Player> currentPlayer;

    public Game(long id) {
        this.id = id;
        players = new LinkedHashMap<Player, Hand>();
        currentPlayer = new AtomicReference<Player>();
    }


    public void addPlayer(Player player) {
        List<Card> cards = Lists.newArrayList(Card.deal(), Card.deal());
        Hand hand = new Hand(cards, 1);
        players.put(player, hand);
    }

    public Hand getHand(Player player) {
        return players.get(player);
    }
}

