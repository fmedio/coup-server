package maj;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void testAddPlayer() {
        Game game = new Game(42);
        Player maj = new Player(1, "maj");
        game.addPlayer(maj);
        game.start();
        Hand hand = game.getHand(maj);
        assertEquals(1, hand.getCoins());
        assertEquals(2, hand.getCards().size());
    }

    @Test
    public void testNextTurn() {
        Game game = new Game(42);
        Player panda = game.addPlayer(new Player(0, "panda"));
        Player lemur = game.addPlayer(new Player(1, "lemur"));
        game.start();
        assertEquals(panda, game.currentPlayer());
        game.nextTurn();
        assertEquals(lemur, game.currentPlayer());
        game.nextTurn();
        assertEquals(panda, game.currentPlayer());
    }
}