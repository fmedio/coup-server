package maj;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayExecutorTest {

    @Test
    public void testPlay() {
        Board board = new Board();
        Game game = board.createGame();
        Player panda = game.addPlayer(new Player(0, "panda"));
        Player giraffe = game.addPlayer(new Player(1, "giraffe"));
        PlayExecutor executor = new PlayExecutor(board);


        //executor.play(game, panda, "pass");
    }
}