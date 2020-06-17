package maj;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BoardTest {
    @Test
    public void testCreatePlayer() {
        Board board = new Board();
        assertEquals(0, board.players().size());
        assertFalse(board.getPlayer(0).isPresent());
        board.createPlayer("panda");
        assertEquals(1, board.players().size());
    }
}