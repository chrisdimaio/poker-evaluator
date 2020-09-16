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
        System.out.println(this);
        System.out.println(other);
        long diff = this.getHandHash() - other.getHandHash();
//        System.out.println(diff);
        return (int) diff;
    }

//    @Override
//    public int compareTo(Hand other){
//        if(this.getHandValue() > other.getHandValue()){
//            return this.getHandValue() - other.getHandValue();
//        } else if(this.getHandValue() < other.getHandValue()){
//            return this.getHandValue() - other.getHandValue();
//        } else {
//            Grouped grouped1 = new Grouped(this);
//            Grouped grouped2 = new Grouped(other);
//            if(grouped1.compare(grouped2)){
//                return 1;
//            } else {
//                return -1;
//            }
//        }
//    }

    @Override
    public String toString() {
        return String.valueOf(cards);
    }
}
