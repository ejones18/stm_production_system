package assignmenttwo.production;

import java.util.Map;

/**
 * bagger 1 prodns
 * put an item in the current bag
 * 2013 Version Phil Green
 * <p>
 * 2020 Heidi Christensen (heidi.christensen@sheffield.ac.uk)
 */
public class BasicPutInCurrentBag implements Production {

    private static final String NAME = "Put In Current Bag";
    private static final String[] ANTECEDENTS = {"current step is: bag an item", "item to bag: ?I of space: ?S", "current bag is number ?N with space: ?BS"};
    private static final String[] ADDITIONS = {"current step is: get the next item", "bag number ?N contains ?I", "current bag is number ?N with space: ?RS"};
    private static final String[] DELETIONS = {"current step is: bag an item", "item to bag: ?I of space: ?S", "current bag is number ?N with space: ?BS"};
    private static final String[] REMARKS = {"Item ?I is now in bag number ?N"};

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
        int spaceLeft = Integer.parseInt((String) context.get("?BS"));
        int spaceNeeded = Integer.parseInt((String) context.get("?S"));
        return (spaceLeft >= spaceNeeded);
    }

    public Map<String, Object> modifyContext(Map<String, Object> context) {
        int spaceLeft = Integer.parseInt((String) context.get("?BS"));
        int spaceNeeded = Integer.parseInt((String) context.get("?S"));
        context.put("?RS", String.valueOf(spaceLeft - spaceNeeded));
        return context;
    }

}
