package io.chrisdima.poker.evaluator;

import org.apache.commons.math3.stat.Frequency;
import org.apache.commons.math3.util.Combinations;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Evaluator {
    private static final List<Long> QUADS = Arrays.asList(1L, 4L);
    private static final List<Long> BOAT = Arrays.asList(2L, 3L);
    private static final List<Long> THREE_OF_KIND = Arrays.asList(1L, 1L, 3L);
    private static final List<Long> TWO_PAIR = Arrays.asList(1L, 2L, 2L);

    public static void main( String[] args ){
        Hand straight = Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TEN, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.QUEEN, Suit.DIAMONDS),
                new Card(Rank.KING, Suit.CLUBS),
                new Card(Rank.ACE, Suit.SPADES)
        )));

        Hand straightFlush = Evaluator.createHand(new ArrayList<>(List.of(
                new Card(Rank.TWO, Suit.SPADES),
                new Card(Rank.THREE, Suit.CLUBS),
                new Card(Rank.FOUR, Suit.SPADES),
                new Card(Rank.FIVE, Suit.SPADES),
                new Card(Rank.SIX, Suit.SPADES)
        )));

        ArrayList<Hand> againstStraightFlush = new ArrayList<>(List.of(straight, straightFlush));
        System.out.println(Evaluator.winner(againstStraightFlush));
    }

    public static Hand createHand(ArrayList<Card> cards){
        Hand hand = new Hand(cards);

        // If we don't have 5 cards then we have an incomplete hand, skip all evaluations.
        if(cards.size() == 5) {
            hand.setHistogram(getHistogram(cards));
            ArrayList<Long> counts = new ArrayList<>(hand.getHistogram().values());
            Collections.sort(counts);
            hand.setCounts(counts);
            if (hand.getCounts().size() == 2) {
                if (hand.getCounts().equals(new ArrayList<>(QUADS))) {
                    hand.setHandType(HandType.QUADS);
                } else if (hand.getCounts().equals(new ArrayList<>(BOAT))) {
                    hand.setHandType(HandType.BOAT);
                }
            } else if (hand.getCounts().size() == 3) {
                if (hand.getCounts().equals(new ArrayList<>(THREE_OF_KIND))) {
                    hand.setHandType(HandType.THREE_OF_A_KIND);
                } else if (hand.getCounts().equals(new ArrayList<>(TWO_PAIR))) {
                    hand.setHandType(HandType.TWO_PAIR);
                }
            } else if (hand.getCounts().size() == 4) {
                hand.setHandType(HandType.ONE_PAIR);
            }

            boolean isStraight = testForStraight(hand);
            boolean isFlush = cards.stream().map(Card::getSuit).distinct().limit(2).count() <= 1;
            if(isStraight) {
                hand.setHandType(HandType.STRAIGHT);
            }
            if(isFlush) {
                hand.setHandType(HandType.FLUSH);
            }
            if(isStraight && isFlush) {
                hand.setHandType(HandType.STRAIGHT_FLUSH);
            }
            if(hand.getHandType() == null) {
                hand.setHandType(HandType.HIGH_CARD);
            }
        }
        return hand;
    }

    public static ArrayList<Hand> winner(ArrayList<Hand> hands, Hand communityHand){

        // Map the best of each hand's composite hands to the original hand
        HashMap<Hand, Hand> bestsMap = new HashMap<>();
        hands.forEach(hand -> bestsMap.put(winner(buildCompositeHands(hand, communityHand)), hand));

        // Find the best of the best composite hands.
        Hand best =  winner(new ArrayList<>(bestsMap.keySet()));

        return new ArrayList<>(List.of(bestsMap.get(best), best));
    }

    /**
     * Determines the winner of a list of hands.
     * @param hands ArrayList of hands to find winner of.
     * @return The winning hand.
     */
    public static Hand winner(ArrayList<Hand> hands){
        return rankHands(hands).get(0);
    }

    private static ArrayList<Hand> buildCompositeHands(Hand hand, Hand communityHand){
        ArrayList<Card> allCards = new ArrayList<>();
        allCards.addAll(hand.getCards());
        allCards.addAll(communityHand.getCards());

        ArrayList<Hand> compositeHands = new ArrayList<>();
        for (int[] ints : new Combinations(allCards.size(), 5)){
            compositeHands.add(
                    Evaluator.createHand(
                            Arrays.stream(ints).mapToObj(allCards::get).collect(
                                    Collectors.toCollection(ArrayList::new))));
        }
        return compositeHands;
    }

    // Can probably modify Grouped to handle this.
    private static HashMap<Integer, Long> getHistogram(ArrayList<Card> cards){
        Frequency frequency = new Frequency();
        cards.forEach(c -> frequency.addValue(c.getRank()));
        HashMap<Integer, Long> histogram = new HashMap<>();
        cards.forEach(c-> histogram.put(c.getRank(), frequency.getCount(c.getRank())));
        return histogram;
    }

    /**
     * Ranks hands from highest (winner) to lowest.
     * @param hands An ArrayList of hands to be ranked
     * @return Ranked ArrayList of hands.
     */
    private static ArrayList<Hand> rankHands(ArrayList<Hand> hands){
        hands.sort(Collections.reverseOrder());
        return hands;
    }

    private static boolean testForStraight(Hand hand){
        ArrayList<Card> cards = hand.getCards();
        boolean highStraight =
                cards.get(0).getRank() - cards.get(4).getRank()
                == 4;
        // If cards contain an Ace and it's not a high straight test for Ace high and low straight.
        if(!highStraight && hand.containsByRank(Rank.ACE)){
            cards.get(0).setRank(1);
            cards.sort(Collections.reverseOrder());
            boolean lowStraight =
                    cards.get(0).getRank() - cards.get(cards.size()-1).getRank()
                    == 4;

            // If we didn't find a low straight put rank back.
            if(lowStraight){
                return true;
            } else
                cards.get(4).setRank(Rank.ACE);
            cards.sort(Collections.reverseOrder());

        }
        return highStraight;
    }
}