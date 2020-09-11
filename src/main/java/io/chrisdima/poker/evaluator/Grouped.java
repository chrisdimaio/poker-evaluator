package io.chrisdima.poker.evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Grouped {

    private final HashMap<Integer, Group> groups = new HashMap<>();

    public Grouped(Hand hand){
        hand.getCards().forEach(card -> {
            if(groups.containsKey(card.getRank())){
                Group group = this.groups.get(card.getRank());
                group.setCount(group.getCount() + 1);
                groups.put(card.getRank(), group);
            } else {
                groups.put(card.getRank(), new Group(card));
            }
        });
    }

    private ArrayList<Group> getSortedGroups(){
        ArrayList<Group> sortedGroups = new ArrayList<>(this.groups.values());
        sortedGroups.sort(Collections.reverseOrder());
        return sortedGroups;
    }

    protected boolean compare(Grouped other){
        ArrayList<Group> thisGroups = this.getSortedGroups();
        ArrayList<Group> otherGroups = other.getSortedGroups();
        for(int i=0; i < 5; i++){
            Group thisGroup = thisGroups.get(i);
            Group otherGroup = otherGroups.get(i);

            // The Hand that this group is derived from has already been determined to be the same as
            // the Hand 'other' has been derived from, therefore, we do not have to compare counts.
            if(thisGroup.getCount() > 1) {
                if (thisGroup.getCard().getRank() > otherGroup.getCard().getRank())
                    return true;
                if (thisGroup.getCard().getRank() < otherGroup.getCard().getRank())
                    return false;
            } else {
                // If card count is 1 it's a kicker, compare rank & suit.
                return thisGroup.getCard().compareTo(otherGroup.getCard()) > 0;
            }
        }
        return false;
    }
}
