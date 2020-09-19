package io.chrisdima.poker.evaluator;

import java.util.ArrayList;
import lombok.Getter;

public class HandHash {
  private static final int HAND_TYPE_POSITION = 10;
  private static final int HAND_RANKS_POSITION = 5;
  private static final int HAND_SUITS_POSITION = 0;

  @Getter
  private long handHash;

  protected HandHash(Hand hand) {
    ArrayList<Long> ranks = new ArrayList<>();
    ArrayList<Long> suits = new ArrayList<>();

    hand.getCards().forEach(card -> {
      ranks.add((long) card.getRank());
      suits.add((long) card.getSuit());
    });

    setHandType(hand.getHandType().getValue());
    setHandRanks(ranks);
    setHandSuits(suits);
  }

  private void setHandSuits(ArrayList<Long> suits) {
    setHalfByteRange(HAND_SUITS_POSITION, suits);
  }

  private void setHandRanks(ArrayList<Long> ranks) {
    setHalfByteRange(HAND_RANKS_POSITION, ranks);
  }

  private void setHandType(long handType) {
    setHalfByte(HAND_TYPE_POSITION, handType);
  }

  private void setHalfByteRange(int position, ArrayList<Long> lst) {
    for (int i = 0; i < lst.size(); i++) {
      setHalfByte(position + i, lst.get(i));
    }
  }

  private void setHalfByte(int position, long value) {
    // Shift value position * 4 bits to left
    value = value << position * 4;

    //Flip those bits in handValue.
    handHash = handHash ^ value;
  }
}
