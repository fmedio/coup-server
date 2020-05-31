package maj;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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