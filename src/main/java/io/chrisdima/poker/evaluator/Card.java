package io.chrisdima.poker.evaluator;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Card implements Comparable<Card>{
    @Setter
    private int rank;
    private final int suit;
    private final String unicodeChar;

    public Card(int rank, int suit){
        this.rank = rank;
        this.suit = suit;
        this.unicodeChar = Unicode.map(rank,suit);
    }

    @Override
    public int compareTo(Card other){
        if(this.rank != other.rank){
            return this.rank - other.rank;
        } else {
            return this.suit - other.suit;
        }
    }

    @Override
    public String toString(){
        return String.valueOf(unicodeChar + "[r: " + rank + " , s: " + suit + "]");
    }
}
