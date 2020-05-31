package maj;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testAddPlayer() {
        Game game = new Game(42);
        Player maj = new Player(1, "maj");
        game.addPlayer(maj);
        Hand hand = game.getHand(maj);
        assertEquals(1, hand.getCoins());
        assertEquals(2, hand.getCards().size());
    }
}