package io.chrisdima.poker.evaluator;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Group implements Comparable<Group>{
    private Card card;
    @Setter
    private int count;
    public Group(Card card){
        this.card = card;
        this.count = 1;
    }

    @Override
    public String toString(){
        return "[ card: " + card + ", count: " + count + "]";
    }

    @Override
    public int compareTo(Group other){
        if(this.getCount() != other.getCount())
            return this.getCount() - other.getCount();
        else
            return this.getCard().compareTo(other.getCard());
    }
}
