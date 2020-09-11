package io.chrisdima.poker.evaluator;

import java.util.ArrayList;
import java.util.Arrays;

public class Deck {
    private static final Card[] DECK = {
            new Card(Rank.TWO, Suit.CLUBS),
            new Card(Rank.THREE, Suit.CLUBS),
            new Card(Rank.FOUR, Suit.CLUBS),
            new Card(Rank.FIVE, Suit.CLUBS),
            new Card(Rank.SIX, Suit.CLUBS),
            new Card(Rank.SEVEN, Suit.CLUBS),
            new Card(Rank.EIGHT, Suit.CLUBS),
            new Card(Rank.NINE, Suit.CLUBS),
            new Card(Rank.TEN, Suit.CLUBS),
            new Card(Rank.JACK, Suit.CLUBS),
            new Card(Rank.QUEEN, Suit.CLUBS),
            new Card(Rank.KING, Suit.CLUBS),
            new Card(Rank.ACE, Suit.CLUBS),

            new Card(Rank.TWO, Suit.DIAMONDS),
            new Card(Rank.THREE, Suit.DIAMONDS),
            new Card(Rank.FOUR, Suit.DIAMONDS),
            new Card(Rank.FIVE, Suit.DIAMONDS),
            new Card(Rank.SIX, Suit.DIAMONDS),
            new Card(Rank.SEVEN, Suit.DIAMONDS),
            new Card(Rank.EIGHT, Suit.DIAMONDS),
            new Card(Rank.NINE, Suit.DIAMONDS),
            new Card(Rank.TEN, Suit.DIAMONDS),
            new Card(Rank.JACK, Suit.DIAMONDS),
            new Card(Rank.QUEEN, Suit.DIAMONDS),
            new Card(Rank.KING, Suit.DIAMONDS),
            new Card(Rank.ACE, Suit.DIAMONDS),

            new Card(Rank.TWO, Suit.HEARTS),
            new Card(Rank.THREE, Suit.HEARTS),
            new Card(Rank.FOUR, Suit.HEARTS),
            new Card(Rank.FIVE, Suit.HEARTS),
            new Card(Rank.SIX, Suit.HEARTS),
            new Card(Rank.SEVEN, Suit.HEARTS),
            new Card(Rank.EIGHT, Suit.HEARTS),
            new Card(Rank.NINE, Suit.HEARTS),
            new Card(Rank.TEN, Suit.HEARTS),
            new Card(Rank.JACK, Suit.HEARTS),
            new Card(Rank.QUEEN, Suit.HEARTS),
            new Card(Rank.KING, Suit.HEARTS),
            new Card(Rank.ACE, Suit.HEARTS),

            new Card(Rank.TWO, Suit.SPADES),
            new Card(Rank.THREE, Suit.SPADES),
            new Card(Rank.FOUR, Suit.SPADES),
            new Card(Rank.FIVE, Suit.SPADES),
            new Card(Rank.SIX, Suit.SPADES),
            new Card(Rank.SEVEN, Suit.SPADES),
            new Card(Rank.EIGHT, Suit.SPADES),
            new Card(Rank.NINE, Suit.SPADES),
            new Card(Rank.TEN, Suit.SPADES),
            new Card(Rank.JACK, Suit.SPADES),
            new Card(Rank.QUEEN, Suit.SPADES),
            new Card(Rank.KING, Suit.SPADES),
            new Card(Rank.ACE, Suit.SPADES),
    };

    public static final ArrayList<Card> getDeck(){
        return new ArrayList<>(Arrays.asList(DECK));
    }
}
