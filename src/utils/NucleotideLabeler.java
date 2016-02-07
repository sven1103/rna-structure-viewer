package utils;

import models.nucleotide2d.drawings.NucleotideType;

/**
 * Created by sven on 2/7/16.
 */
public class NucleotideLabeler {


    public static NucleotideType getType(char i){
        NucleotideType nucleotide;

        switch(i){
            case 'A':
                nucleotide = NucleotideType.ADENINE;
                break;
            case 'C':
                nucleotide = NucleotideType.CYTOSINE;
                break;
            case 'G':
                nucleotide = NucleotideType.GUANINE;
                break;
            case 'U':
                nucleotide = NucleotideType.URACIL;
                break;
            default:
                nucleotide = null;
        }
        return nucleotide;
    }

}
