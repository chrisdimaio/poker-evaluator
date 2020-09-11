package io.chrisdima.poker.evaluator;

import java.util.HashMap;
import java.util.Map;

//https://en.wikipedia.org/wiki/Playing_cards_in_Unicode#Block
public class Unicode {
    private static final Map<Integer, Integer> suitMap;
    static {
        suitMap = new HashMap<>();
        suitMap.put(Suit.SPADES, 0x1F0A0);
        suitMap.put(Suit.HEARTS, 0x1F0B0);
        suitMap.put(Suit.DIAMONDS, 0x1F0C0);
        suitMap.put(Suit.CLUBS, 0x1F0D0);
    }

    private static final Map<Integer, Integer> rankMap;
    static {
        rankMap = new HashMap<>();
        rankMap.put(Rank.ACE, 0x1);
        rankMap.put(Rank.TWO, 0x2);
        rankMap.put(Rank.THREE, 0x3);
        rankMap.put(Rank.FOUR, 0x4);
        rankMap.put(Rank.FIVE, 0x5);
        rankMap.put(Rank.SIX, 0x6);
        rankMap.put(Rank.SEVEN, 0x7);
        rankMap.put(Rank.EIGHT, 0x8);
        rankMap.put(Rank.NINE, 0x9);
        rankMap.put(Rank.TEN, 0xA);
        rankMap.put(Rank.JACK, 0xB);
        rankMap.put(Rank.QUEEN, 0xD);
        rankMap.put(Rank.KING, 0xE);
    }

    public static String map(int rank, int suit) {
        return String.valueOf(Character.toChars(suitMap.get(suit) + rankMap.get(rank)));
    }
}
