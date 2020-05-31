package maj;

import java.util.Random;

public enum Card {
    ten,
    king,
    ace,
    queen,
    jack;

    private static Random RNG = new Random();

    public static Card deal() {
        return Card.values()[RNG.nextInt(Card.values().length)];
    }
}
