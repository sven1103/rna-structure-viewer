package utils;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sven on 1/31/16.
 */
public class PseudoKnotSolver {

    public static int bestSolutionLength = 0;

    /**
     * This method resolves pseudo-knots from a given list of pairs, representing
     * the pairing positions in the sequence.
     * The algorithm makes n*n pairwise checks for nestedness condition.
     * If false, then it will copy the list and removes one pair from the first list
     * and the other pair from the copied list. Both lists are both then evaluated
     * themselves for pseudo-knots recursively until no adjustments can be done anymore.
     * On the traceback, the algorithm will keep the list that still contains most
     * nested structures and therefore results in an overall minimum adjustment
     * necessary on the initial list, to resolve the pseudoknots.
     * @param basePairs The initial list of pairs
     * @return A pseudo-knot free list of pairs.
     */
    public static List<Pair<Integer, Integer>> removePseudoknots(List<Pair<Integer, Integer>> basePairs){
        if(basePairs.isEmpty() || basePairs.size() < bestSolutionLength){
            return basePairs;
        }

        for(Pair bond : basePairs){
            for(Pair otherBond : basePairs){
                if(isPseudoKnot(bond,otherBond)){
                    List copyBasePairs = new ArrayList<>(basePairs);
                    copyBasePairs.remove(otherBond);
                    basePairs.remove(bond);
                    List list1 = removePseudoknots(basePairs);
                    List list2 = removePseudoknots(copyBasePairs);
                    if(list1.size()> list2.size()){
                        return list1;
                    } else {
                        return list2;
                    }
                }
            }
        }
        bestSolutionLength = basePairs.size();
        return basePairs;
    }


    /**
     * Evaluation of two pairs, determines if they
     * form a non-nested structure (pseudo-knot).
     * @param pair The first pair
     * @param otherPair The other pair of the comparison
     * @return Forms a pseudo-knot, true or false
     */
    private static boolean isPseudoKnot(Pair<Integer, Integer> pair,
                                        Pair<Integer, Integer> otherPair){

        if(pair.equals(otherPair)){
            return false;
        }

        int i,j,iPrime, jPrime;

        if(pair.getKey() > otherPair.getKey()){
            iPrime = pair.getKey();
            jPrime = pair.getValue();
            i = otherPair.getKey();
            j = otherPair.getValue();
        } else{
            i = pair.getKey();
            j = pair.getValue();
            iPrime = otherPair.getKey();
            jPrime = otherPair.getValue();
        }


        // i<j<i'<j'
        if(((i<j) && (iPrime<jPrime)) && (j<iPrime)){
            return false;
        }
        // i<i'<j'<j
        if(((i<iPrime) && (jPrime < j)) && (iPrime<jPrime)){
            return false;
        } else{
            // Pseudoknot found
            return true;
        }
    }


    /**
     * Testing the PseudoKnotSolver
     * @param args CLI
     */
    public static void main(String[] args){
        List<Pair<Integer, Integer>> testList = new ArrayList<>();

        testList.add(new Pair<>(1,10));
        testList.add(new Pair<>(2,9));
        testList.add(new Pair<>(3,8));
        testList.add(new Pair<>(4,7));
        testList.add(new Pair<>(5,11));
        testList.add(new Pair<>(6,12));

        List newList = removePseudoknots(testList);

        newList.forEach(value -> System.out.println(value.toString()));

    }



}
