package assignmenttwo.production;

import java.util.Map;

/**
 * b1Start_new_bag
 * bagger 1 prodns
 * start a new bag
 * 2013 version Phil Green
 * <p>
 * 2020 Heidi Christensen (heidi.christensen@sheffield.ac.uk)
 */
public class BasicStartNewBag implements Production {

    private static final String NAME = "Start New Bag";
    private static final String[] ANTECEDENTS = {"current step is: bag an item", "current bag is number ?N with space: ?BS"};
    private static final String[] ADDITIONS = {"current bag is number ?NB with space: 100"};
    private static final String[] DELETIONS = {"current bag is number ?N with space: ?BS"};
    private static final String[] REMARKS = {"Starting to use bag number ?NB"};

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
        int bagNumber = Integer.parseInt((String) context.get("?N"));
        context.put("?NB", String.valueOf(bagNumber + 1));
        return context;
    }
}
