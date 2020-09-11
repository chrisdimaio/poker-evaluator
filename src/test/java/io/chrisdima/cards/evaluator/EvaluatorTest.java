package io.chrisdima.cards.evaluator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EvaluatorTest {
    @Test
    public void cardCount(){
        assertThrows(IllegalStateException.class, () -> Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.THREE, Suit.HEARTS),
                new Card(Rank.FOUR, Suit.DIAMONDS),
                new Card(Rank.FIVE, Suit.CLUBS)
        ))));

        assertThrows(IllegalStateException.class, () -> Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.THREE, Suit.HEARTS),
                new Card(Rank.FOUR, Suit.DIAMONDS),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES),
                new Card(Rank.EIGHT, Suit.HEARTS)
        ))));
    }

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

    @Test
    public void StraightAgainstLowStraight(){
        Hand straight = Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TEN, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.QUEEN, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.CLUBS),
                new Card(Rank.ACE, Suit.SPADES)
        )));

        Hand lowStraight = Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.THREE, Suit.HEARTS),
                new Card(Rank.FOUR, Suit.DIAMONDS),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES)
        )));

        ArrayList<Hand> againstLowStraight = new ArrayList<>(List.of(straight, lowStraight));
        assertEquals(straight, Evaluator.winner(againstLowStraight));
    }

    @Test
    public void StraightAgainstHighCard(){
        Hand straight = Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TEN, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.QUEEN, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.CLUBS),
                new Card(Rank.ACE, Suit.SPADES)
        )));

        Hand highCard = Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.FOUR, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.DIAMONDS),
                new Card(Rank.SIX, Suit.CLUBS),
                new Card(Rank.SEVEN, Suit.SPADES)
        )));

        ArrayList<Hand> againstHighCard = new ArrayList<>(List.of(straight, highCard));
        assertEquals(straight, Evaluator.winner(againstHighCard));
    }

    @Test
    public void StraightAgainstFlush(){
        Hand straight = Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TEN, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.QUEEN, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.CLUBS),
                new Card(Rank.ACE, Suit.SPADES)
        )));

        Hand flush = Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.FOUR, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.HEARTS),
                new Card(Rank.SIX, Suit.HEARTS),
                new Card(Rank.SEVEN, Suit.HEARTS)
        )));

        ArrayList<Hand> againstFlush = new ArrayList<>(List.of(straight, flush));
        assertNotEquals(straight, Evaluator.winner(againstFlush));
    }

    @Test
    public void StraightAgainstBoat(){
        Hand straight = Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TEN, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.QUEEN, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.CLUBS),
                new Card(Rank.ACE, Suit.SPADES)
        )));

        Hand boat = Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.TWO, Suit.SPADES),
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.SEVEN, Suit.CLUBS),
                new Card(Rank.SEVEN, Suit.HEARTS)
        )));

        ArrayList<Hand> againstBoat = new ArrayList<>(List.of(straight, boat));
        assertNotEquals(straight, Evaluator.winner(againstBoat));
    }

    @Test
    public void StraightAgainstQuads(){
        Hand straight = Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TEN, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.QUEEN, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.CLUBS),
                new Card(Rank.ACE, Suit.SPADES)
        )));

        Hand quads = Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.TWO, Suit.SPADES),
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.SEVEN, Suit.HEARTS)
        )));

        ArrayList<Hand> againstQuads = new ArrayList<>(List.of(straight, quads));
        assertNotEquals(straight, Evaluator.winner(againstQuads));
    }

    @Test
    public void StraightAgainstStraightFlush(){
        Hand straight = Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TEN, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.QUEEN, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.CLUBS),
                new Card(Rank.ACE, Suit.SPADES)
        )));

        Hand straightFlush = Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TWO, Suit.SPADES),
                new Card(Rank.THREE, Suit.SPADES),
                new Card(Rank.FOUR, Suit.SPADES),
                new Card(Rank.FIVE, Suit.SPADES),
                new Card(Rank.SIX, Suit.SPADES)
        )));

        ArrayList<Hand> againstStraightFlush = new ArrayList<>(List.of(straight, straightFlush));
        assertNotEquals(straight, Evaluator.winner(againstStraightFlush));
    }
}
