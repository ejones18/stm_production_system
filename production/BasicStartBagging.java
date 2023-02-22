package assignmenttwo.production;

import java.util.Map;

/**
 * prod1
 * 2013 version Phil Green
 * <p>
 * 2020 Heidi Christensen (heidi.christensen@sheffield.ac.uk)
 */
public class BasicStartBagging implements Production {
    private static final String NAME = "Start Bagging";
    private static final String[] ANTECEDENTS = {"current step is: start bagging"};
    private static final String[] ADDITIONS = {"current step is: get the next item", "current bag is number 1 with space: 100"};
    private static final String[] DELETIONS = {"current step is: start bagging"};
    private static final String[] REMARKS = {"Starting to bag"};

    public String getName() {
        return NAME;
    }

    public String[] getAntecedents() {
        return ANTECEDENTS;
    }

    public String[] getAdditions() {
        return ADDITIONS;
    }

    public String[] getDeletions() {
        return DELETIONS;
    }

    public String[] getRemarks() {
        return REMARKS;
    }

    public boolean canRunInContext(Map<String, Object> context) {
        return true;
    }

    public Map<String, Object> modifyContext(Map<String, Object> context) {
        return context;
    }

}
