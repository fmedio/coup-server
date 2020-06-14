package maj;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BoardTest {
    @Test
    public void testCreatePlayer() {
        Board board = new Board();
        assertEquals(0, board.players().size());
        board.createPlayer("panda");
        assertEquals(1, board.players().size());
    }
}