package maj;

import java.util.Collection;

public class Hand {
    private Collection<Card> cards;
    private int coins;

    public Hand(Collection<Card> cards, int coins) {
        this.cards = cards;
        this.coins = coins;
    }

    public Collection<Card> getCards() {
        return cards;
    }

    public int getCoins() {
        return coins;
    }
}
