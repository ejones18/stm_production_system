package assignmenttwo.production;

import java.util.Map;

/**
 * prod1
 * 2013 version Phil Green
 * <p>
 * 2020 Heidi Christensen (heidi.christensen@sheffield.ac.uk)
 */
public class BasicGetNextItem implements Production {
    private static final String NAME = "Get Next Item";
    private static final String[] ANTECEDENTS = {"current step is: get the next item", "the trolley contains ?I of space: ?S"};
    private static final String[] ADDITIONS = {"current step is: bag an item", "item to bag: ?I of space: ?S"};
    private static final String[] DELETIONS = {"current step is: get the next item", "the trolley contains ?I of space: ?S"};
    private static final String[] REMARKS = {"Bagging item: ?I"};

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

    public boolean canRunInContext(Map<String, Object> c) {
        return true;
    }

    public Map<String, Object> modifyContext(Map<String, Object> c) {
        return c;
    }

}
