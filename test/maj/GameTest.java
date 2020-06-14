package maj;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GameTest {

    private Game game;
    private Player panda;
    private Player lemur;

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

    @Test
    public void testPlay() {
        assertEquals(panda, game.currentPlayer());
        game.play(panda, "pass");
        assertEquals(lemur, game.currentPlayer());
        game.play(lemur, "pass");
        assertEquals(panda, game.currentPlayer());

        try {
            game.play(lemur, "pass");
        } catch (IllegalStateException e) {
            return;
        }

        fail("Should have gotten an IllegalStateException - It wasn't lemur's turn!");
    }

    @Before
    public void setUp() throws Exception {
        game = new Game(42);
        panda = game.addPlayer(new Player(0, "panda"));
        lemur = game.addPlayer(new Player(1, "lemur"));
        game.start();
    }
}