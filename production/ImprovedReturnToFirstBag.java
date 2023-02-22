package assignmenttwo.production;

import java.util.*;

/**
 * Production rule for ImprovedBagger System
 * Returns to the very first bag started
 * @author Ethan Jones @aca19ej
 */
public class ImprovedReturnToFirstBag implements Production {

    private static final String NAME = "Back to first bag";
    private static final String[] ANTECEDENTS = {"current step is: return to first bag", "item to bag: ?I of space: ?S", "bags have space ?BS", "current bag is number ?N"};
    private static final String[] ADDITIONS = {"current step is: bag an item", "current bag is number ?NB"};
    private static final String[] DELETIONS = {"current step is: return to first bag", "current bag is number ?N"};
    private static final String[] REMARKS = {"Returning to first bag"};

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
        context.put("?NB", String.valueOf(1));
        return context;
    }
}