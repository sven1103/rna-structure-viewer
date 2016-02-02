package models.misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by sven on 12/12/15.
 */
public class AtomMapping {

    public static final Map<String, Integer> RIBOSE_MAPPING;
    static
    {
        RIBOSE_MAPPING = new HashMap<>();
        RIBOSE_MAPPING.put("C1'", 0);
        RIBOSE_MAPPING.put("C2'", 3);
        RIBOSE_MAPPING.put("C3'", 6);
        RIBOSE_MAPPING.put("C4'", 9);
        RIBOSE_MAPPING.put("O4'", 12);
    }


    public static final Map<String, Integer> PYRIMIDINE_MAPPING;
    static
    {
        PYRIMIDINE_MAPPING = new HashMap<>();
        PYRIMIDINE_MAPPING.put("N1", 0);
        PYRIMIDINE_MAPPING.put("C2", 3);
        PYRIMIDINE_MAPPING.put("N3", 6);
        PYRIMIDINE_MAPPING.put("C4", 9);
        PYRIMIDINE_MAPPING.put("C5", 12);
        PYRIMIDINE_MAPPING.put("C6", 15);
    }

    public static final Map<String, Integer> PURINE_MAPPING;
    static
    {
        PURINE_MAPPING = new HashMap<>();
        PURINE_MAPPING.put("N1",0);
        PURINE_MAPPING.put("C2",3);
        PURINE_MAPPING.put("N3",6);
        PURINE_MAPPING.put("C4",9);
        PURINE_MAPPING.put("C5",12);
        PURINE_MAPPING.put("C6",15);
        PURINE_MAPPING.put("N7",18);
        PURINE_MAPPING.put("C8",21);
        PURINE_MAPPING.put("N9",24);
    }

    public static final HashSet<String> PURINE_HBONDS;
    static
    {
        PURINE_HBONDS = new HashSet<>();
        PURINE_HBONDS.add("H62");
        PURINE_HBONDS.add("N6");
        PURINE_HBONDS.add("N1");
        PURINE_HBONDS.add("O6");
        PURINE_HBONDS.add("H1");
        PURINE_HBONDS.add("N2");
        PURINE_HBONDS.add("H21");
    }

    public static final HashSet<String> PYRIMIDINE_HBONDS;
    static
    {
        PYRIMIDINE_HBONDS = new HashSet<>();
        PYRIMIDINE_HBONDS.add("O4");
        PYRIMIDINE_HBONDS.add("N3");
        PYRIMIDINE_HBONDS.add("H3");
        PYRIMIDINE_HBONDS.add("H41");
        PYRIMIDINE_HBONDS.add("N4");
        PYRIMIDINE_HBONDS.add("O2");
    }


}
