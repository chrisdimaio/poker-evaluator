package io.chrisdima.cards.evaluator;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EvaluatorTest {
    @Test
    public void highLowStraight(){
        ArrayList<Card> high = new ArrayList<>();
        high.add(new Card(Rank.TEN, Suit.CLUBS));
        high.add(new Card(Rank.JACK, Suit.DIAMONDS));
        high.add(new Card(Rank.QUEEN, Suit.SPADES));
        high.add(new Card(Rank.KING, Suit.CLUBS));
        high.add(new Card(Rank.ACE, Suit.SPADES));

        ArrayList<Card> low = new ArrayList<>();
        low.add(new Card(Rank.ACE, Suit.CLUBS));
        low.add(new Card(Rank.TWO, Suit.DIAMONDS));
        low.add(new Card(Rank.THREE, Suit.SPADES));
        low.add(new Card(Rank.FOUR, Suit.CLUBS));
        low.add(new Card(Rank.FIVE, Suit.HEARTS));

        Hand highHand = Evaluator.createHand(high);
        Hand lowHand = Evaluator.createHand(low);

        ArrayList<Hand> hands = new ArrayList<>();
        hands.add(highHand);
        hands.add(lowHand);

        Hand winner = Evaluator.winner(hands);

        assertEquals(highHand, winner);
        assertNotEquals(lowHand, winner);
    }

    @Test
    public void pairVSAceHigh(){
        ArrayList<Card> pair = new ArrayList<>();
        pair.add(new Card(Rank.KING, Suit.HEARTS));
        pair.add(new Card(Rank.QUEEN, Suit.SPADES));
        pair.add(new Card(Rank.QUEEN, Suit.HEARTS));
        pair.add(new Card(Rank.SEVEN, Suit.SPADES));
        pair.add(new Card(Rank.TWO, Suit.SPADES));

        ArrayList<Card> pairAces = new ArrayList<>();
        pairAces.add(new Card(Rank.ACE, Suit.DIAMONDS));
        pairAces.add(new Card(Rank.KING, Suit.CLUBS));
        pairAces.add(new Card(Rank.NINE, Suit.DIAMONDS));
        pairAces.add(new Card(Rank.THREE, Suit.DIAMONDS));
        pairAces.add(new Card(Rank.ACE, Suit.HEARTS));

        Hand handPair = Evaluator.createHand(pair);
        Hand handPairAces = Evaluator.createHand(pairAces);

        ArrayList<Hand> hands = new ArrayList<>();
        hands.add(handPair);
        hands.add(handPairAces);

        assertEquals(handPairAces, Evaluator.winner(hands));
    }
}
