package maj;

import java.util.List;

public class Hand {
    private List<Card> cards;
    private int coins;

    public Hand(List<Card> cards, int coins) {
        this.cards = cards;
        this.coins = coins;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getCoins() {
        return coins;
    }
}
