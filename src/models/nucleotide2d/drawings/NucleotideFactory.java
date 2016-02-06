package models.nucleotide2d.drawings;


/**
 * advanced-java-bioinformatics
 *
 * Description:
 *  <- content ->
 *
 * @author fillinger
 * @version
 * Date: 11/26/15
 * EMail: sven.fillinger@student.uni-tuebingen.de
 */
public class NucleotideFactory {

    /**
     *
     * @param type Specifies an ENUM NucleotideType, so the type of nucleotide that
     *             shall be returned
     * @return An NucleotideCircle of type 'Adenosine', 'Cytosine', 'Guanine', 'Uracil'
     */
    public AbstractNucleotideCircle getNucleotide(NucleotideType type){
        AbstractNucleotideCircle requestedNucleotide;

        switch(type){
            case ADENINE:
                requestedNucleotide = new Adenine();
                break;
            case CYTOSINE:
                requestedNucleotide = new Cytosine();
                break;
            case GUANINE:
                requestedNucleotide = new Guanine();
                break;
            case URACIL:
                requestedNucleotide = new Uracil();
                break;
            default:
                requestedNucleotide = null;
                break;
        }

        return requestedNucleotide;
    }

    /**
     * Overloaded method
     * @param letter Specifies an String for a nucleotide, so the type of nucleotide that
     *              shall be returned
     * @return An NucleotideCircle of type 'Adenosine', 'Cytosine', 'Guanine', 'Uracil'
     */
    public AbstractNucleotideCircle getNucleotide(String letter){
        NucleotideType type;
        switch (letter.toLowerCase()){
            case "a":
                type = NucleotideType.ADENINE;
                break;
            case "c":
                type = NucleotideType.CYTOSINE;
                break;
            case "g":
                type = NucleotideType.GUANINE;
                break;
            case "u":
                type = NucleotideType.URACIL;
                break;
            default:
                type = null;
                break;
        }
        return (getNucleotide(type));
    }

}
