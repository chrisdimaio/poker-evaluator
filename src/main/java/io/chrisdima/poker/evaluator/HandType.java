package io.chrisdima.poker.evaluator;

import lombok.Getter;

public enum HandType {
    INCOMPLETE(-1),
    HIGH_CARD(0),
    ONE_PAIR(1),
    TWO_PAIR(2),
    THREE_OF_A_KIND(3),
    STRAIGHT(4),
    FLUSH(5),
    BOAT(6),
    QUADS(7),
    STRAIGHT_FLUSH(8);

    @Getter
    private int value;
    HandType(int value){
        this.value = value;
    }
}
