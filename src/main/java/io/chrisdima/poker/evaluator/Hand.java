package io.chrisdima.poker.evaluator;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter @Setter
public class Hand implements Comparable<Hand>{
    private HandType handType;

    private ArrayList<Card> cards;
    private HashMap<Integer, Long> histogram;
    private ArrayList<Long> counts;

    Hand(ArrayList<Card> cards){
        if(cards.size() <= 5 && cards.size() >= 2) {
            cards.sort(Collections.reverseOrder());
            this.cards = cards;
        } else {
            throw new IllegalStateException("Requires 2-5 cards in hand. Got " + cards.size());
        }
    }

    protected boolean containsByRank(int rank){
        for (Card card: cards) {
            if(card.getRank() == rank)
                return true;
        }
        return false;
    }

    protected long getHandHash(){
        return new HandHash(this).getHandHash();
    }

    @Override
    public int compareTo(Hand other){
        long diff = this.getHandHash() - other.getHandHash();
        if(diff > 0)
            return 1;
        else if (diff < 0)
            return -1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return String.valueOf(cards);
    }
}
