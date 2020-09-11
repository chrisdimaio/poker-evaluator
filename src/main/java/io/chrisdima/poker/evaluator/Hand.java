package io.chrisdima.poker.evaluator;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter @Setter
public class Hand implements Comparable<Hand>{
    private ArrayList<Card> cards;
    private HashMap<Integer, Long> histogram;
    private ArrayList<Long> counts;
    private int handValue;
    private Card highestCard;
    private boolean quads;
    private boolean boat;
    private boolean threeOfAKind;
    private boolean twoPair;
    private boolean onePair;
    private boolean flush;
    private boolean straight;
    private boolean straightFlush;
    private boolean highCard;

    public Hand(ArrayList<Card> cards){
        if(cards.size() == 5) {
            cards.sort(Collections.reverseOrder());
            this.cards = cards;
        } else {
            throw new IllegalStateException("Requires 5 cards in hand.");
        }
    }

    protected boolean containsByRank(int rank){
        for (Card card: cards) {
            if(card.getRank() == rank)
                return true;
        }
        return false;
    }

    @Override
    public int compareTo(Hand other){
        if(this.getHandValue() > other.getHandValue()){
            return this.getHandValue() - other.getHandValue();
        } else if(this.getHandValue() < other.getHandValue()){
            return this.getHandValue() - other.getHandValue();
        } else {
            Grouped grouped1 = new Grouped(this);
            Grouped grouped2 = new Grouped(other);
            if(grouped1.compare(grouped2)){
                return 1;
            } else {
                return -1;
            }
        }
    }

    @Override
    public String toString() {
        return String.valueOf(cards);
    }
}
