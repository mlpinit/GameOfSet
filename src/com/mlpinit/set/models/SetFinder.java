package com.mlpinit.set;

import java.util.ArrayList;

public class SetFinder {
    private ArrayList<ArrayList<Card>> possibleSets = new ArrayList<ArrayList<Card>>();

    public SetFinder(ArrayList<Card> cards) {
        findPossibleSets(cards);
    }

    public ArrayList<ArrayList<Card>> getPossibleSets() {
        return possibleSets;
    }

    public int possibleSetsSize() {
        return possibleSets.size();
    }

    public ArrayList<ArrayList<Card>> findPossibleSets(ArrayList<Card> cards) {
        possibleSets.clear();
        for (int i = 0; i < cards.size() - 2; i++) {
            for (int j = i + 1; j < cards.size() - 1; j++) {
                for (int k = j + 1; k < cards.size(); k++) {
                    if (SetValidator.isValid(cards.get(i), cards.get(j), cards.get(k))) {
                        ArrayList<Card> set = new ArrayList<Card>(3);
                        set.add(cards.get(i));
                        set.add(cards.get(j));
                        set.add(cards.get(k));
                        possibleSets.add(set);
                    }
                }
            }
        }
        return possibleSets;
    }

    public ArrayList<Card> getRandomSet() {
        int randomSetIndex = Misc.randomIntInRange(0, possibleSets.size() - 1);
        return possibleSets.get(randomSetIndex);
    }

}
