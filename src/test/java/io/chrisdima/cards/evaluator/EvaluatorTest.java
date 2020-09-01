package io.chrisdima.cards.evaluator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;

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
        assertTrue(Evaluator.compareHand(highHand, lowHand));
        assertFalse(Evaluator.compareHand(lowHand, highHand));
    }
}
