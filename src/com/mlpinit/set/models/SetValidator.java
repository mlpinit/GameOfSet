package com.mlpinit.set;

import java.util.ArrayList;

public class SetValidator {

    // This algorithm avoids the cumbersome use of multiple if statements. The
    // numValue() function returns a numerical representation of a card.  We
    // have a valid set only when each attribute of a card is the same on all of
    // the cards or different on all of the cards. For each attribute, the
    // summed numerical representation gives a digit divisible by 3 for a valid
    // set and not divisible by 3 for an invalid set.

    public static boolean isValid(ArrayList<Card> cards) {
        int numValueSum = 0;
        for (Card card : cards) numValueSum += card.numValue(); 
        while (numValueSum != 0) {
            if ((numValueSum % 10) % 3 != 0) return false;
            numValueSum /= 10;
        }
        return true;
    }

    public static boolean isValid(Card one, Card two, Card three) {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(one);
        cards.add(two);
        cards.add(three);
        return isValid(cards);
    }

}
