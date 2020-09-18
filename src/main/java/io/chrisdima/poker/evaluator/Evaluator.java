package io.chrisdima.poker.evaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.math3.stat.Frequency;
import org.apache.commons.math3.util.Combinations;

public class Evaluator {
  private static final List<Long> QUADS = Arrays.asList(1L, 4L);
  private static final List<Long> BOAT = Arrays.asList(2L, 3L);
  private static final List<Long> THREE_OF_KIND = Arrays.asList(1L, 1L, 3L);
  private static final List<Long> TWO_PAIR = Arrays.asList(1L, 2L, 2L);

  public static void main(String[] args) {
  }

  /**
   * Turns list of cards into a hand. Evaluates and generates all metadata about hand.
   *
   * @param cards List of cards that make up hand.
   * @return Hand object.
   */
  public static Hand createHand(ArrayList<Card> cards) {
    Hand hand = new Hand(cards);

    // If we don't have 5 cards then we have an incomplete hand, skip all evaluations.
    if (cards.size() == 5) {
      hand.setHistogram(getHistogram(cards));
      ArrayList<Long> counts = new ArrayList<>(hand.getHistogram().values());
      Collections.sort(counts);
      hand.setCounts(counts);
      hand.setHandType(determineHandType(hand));
    } else {
      hand.setHandType(HandType.INCOMPLETE);
    }
    return hand;
  }

  /**
   * Determines winner(s) with community cards.
   *
   * @param hands List of incomplete two card hands to evaluate.
   * @param communityHand Community hand to complete hands.
   * @return A list of 1 or more winners and the complete hand that won it.
   */
  public static ArrayList<Hand> winner(ArrayList<Hand> hands, Hand communityHand) {

    // Map the best of each hand's composite hands to the original hand
    HashMap<Hand, ArrayList<Hand>> bestsMap = new HashMap<>();
    hands.forEach(hand -> {
      Hand key = winner(buildCompositeHands(hand, communityHand));
      if (!bestsMap.containsKey(key)) {
        bestsMap.put(key, new ArrayList<>());
      }
      bestsMap.get(key).add(hand);
    });

    // Find the best of the best composite hands.
    Hand best = winner(new ArrayList<>(bestsMap.keySet()));

    ArrayList<Hand> bestOfBest = new ArrayList<>(bestsMap.get(best));
    bestOfBest.add(best);
    return bestOfBest;
  }

  /**
   * Determines the winner of a list of hands.
   *
   * @param hands ArrayList of hands to find winner of.
   * @return The winning hand.
   */
  public static Hand winner(ArrayList<Hand> hands) {
    return rankHands(hands).get(0);
  }

  private static ArrayList<Hand> buildCompositeHands(Hand hand, Hand communityHand) {
    ArrayList<Card> allCards = new ArrayList<>();
    allCards.addAll(hand.getCards());
    allCards.addAll(communityHand.getCards());

    ArrayList<Hand> compositeHands = new ArrayList<>();
    for (int[] ints : new Combinations(allCards.size(), 5)) {
      compositeHands.add(
          Evaluator.createHand(
              Arrays.stream(ints).mapToObj(allCards::get).collect(
                  Collectors.toCollection(ArrayList::new))));
    }
    return compositeHands;
  }

  private static HandType determineHandType(Hand hand) {
    if (hand.getCounts().size() == 4) {
      return HandType.ONE_PAIR;
    } else if (hand.getCounts().size() == 3) {
      if (hand.getCounts().equals(new ArrayList<>(THREE_OF_KIND))) {
        return HandType.THREE_OF_A_KIND;
      } else if (hand.getCounts().equals(new ArrayList<>(TWO_PAIR))) {
        return HandType.TWO_PAIR;
      }
    } else if (hand.getCounts().size() == 2) {
      if (hand.getCounts().equals(new ArrayList<>(QUADS))) {
        return HandType.QUADS;
      } else if (hand.getCounts().equals(new ArrayList<>(BOAT))) {
        return HandType.BOAT;
      }
    }

    boolean isStraight = testForStraight(hand);
    boolean isFlush = hand.getCards().stream().map(Card::getSuit).distinct().limit(2).count() <= 1;
    if (isStraight && isFlush) {
      return HandType.STRAIGHT_FLUSH;
    }

    if (isStraight) {
      return HandType.STRAIGHT;
    }
    if (isFlush) {
      return HandType.FLUSH;
    }
    return HandType.HIGH_CARD;
  }


  // Can probably modify Grouped to handle this.
  private static HashMap<Integer, Long> getHistogram(ArrayList<Card> cards) {
    Frequency frequency = new Frequency();
    cards.forEach(c -> frequency.addValue(c.getRank()));
    HashMap<Integer, Long> histogram = new HashMap<>();
    cards.forEach(c -> histogram.put(c.getRank(), frequency.getCount(c.getRank())));
    return histogram;
  }

  /**
   * Ranks hands from highest (winner) to lowest.
   *
   * @param hands An ArrayList of hands to be ranked
   * @return Ranked ArrayList of hands.
   */
  private static ArrayList<Hand> rankHands(ArrayList<Hand> hands) {
    hands.sort(Collections.reverseOrder());
    return hands;
  }

  private static boolean testForStraight(Hand hand) {
    ArrayList<Card> cards = hand.getCards();
    boolean highStraight =
        cards.get(0).getRank() - cards.get(4).getRank()
            == 4;
    // If cards contain an Ace and it's not a high straight test for Ace high and low straight.
    if (!highStraight && hand.containsByRank(Rank.ACE)) {
      cards.get(0).setRank(1);
      cards.sort(Collections.reverseOrder());
      boolean lowStraight =
          cards.get(0).getRank() - cards.get(cards.size() - 1).getRank()
              == 4;

      // If we didn't find a low straight put rank back.
      if (lowStraight) {
        return true;
      } else {
        cards.get(4).setRank(Rank.ACE);
      }
      cards.sort(Collections.reverseOrder());

    }
    return highStraight;
  }
}