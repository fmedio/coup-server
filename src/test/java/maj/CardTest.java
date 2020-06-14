package maj;


import static org.junit.Assert.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class CardTest {
    @Test
    public void testRandomness() {
        Set<Card> cards = new HashSet<Card>();
        for (int i = 0; i < 100; i++) {
            cards.add(Card.deal());
        }

        assertNotEquals(1, cards.size());
    }
}