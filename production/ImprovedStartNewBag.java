package assignmenttwo.production;

import java.util.*;

/**
 * Production rule for ImprovedBagger System
 * Starts a new bag
 * @author Ethan Jones @aca19ej
 */
public class ImprovedStartNewBag implements Production {

    private static final String NAME = "Start New Bag";
    private static final String[] ANTECEDENTS = {"current step is: bag an item", "current bag is number ?N", "item to bag: ?I of space: ?S", "bags have space ?BS"};
    private static final String[] ADDITIONS = {"current step is: return to first bag", "current bag is number ?NB", "bags have space ?RS"};
    private static final String[] DELETIONS = {"current bag is number ?N", "current step is: bag an item", "bags have space ?BS"};
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
        int bagNumber = Integer.parseInt((String) context.get("?N"));
        if (bagNumber > 1){
            String bagsSpaces = ((String) context.get("?BS")); //Fetches the long string of bags and their corresponding spaces
            int endIndex =  bagsSpaces.indexOf("/B"+ String.valueOf(bagNumber+1));
            if (endIndex <= -1){ //If endIndex (start of next bag) doesn't exist (< -1) then there is no next bag to get so checks if the item can fit in, if not a new bag is started
                int endIndexActual =  bagsSpaces.lastIndexOf("/");
                int verticalpipeIndex = bagsSpaces.lastIndexOf("|"); //Finds index of vertical pipe that separates the bag No. and its space left
                String bagSpace = bagsSpaces.substring(verticalpipeIndex+1,endIndexActual); //Creates a string of the bag info- space left
                int spaceLeft = Integer.parseInt(bagSpace); //Parses the bag space as an integer
                int spaceNeeded = Integer.parseInt((String) context.get("?S"));
                return (!(spaceLeft >= spaceNeeded));
            }
            else{
                return false;
            }
        }
        else{
            String bagsSpaces = ((String) context.get("?BS"));
            if (bagsSpaces.length() > 6){//Simple check to see if there are more than one bags in total, if there is can't start new bag as bag No. is currently 1
                return false;
            }
            else{
                int startIndex =  bagsSpaces.indexOf("B"+ String.valueOf(bagNumber));
                int endIndex =  bagsSpaces.indexOf("/");
                String bagInfo = bagsSpaces.substring(startIndex, endIndex);
                int verticalpipeIndex = bagInfo.indexOf("|"); //Finds index of vertical pipe that separates the bag No. and its space left
                String bagSpace = bagInfo.substring(verticalpipeIndex+1, endIndex); //Creates a string of the bag info - space left
                int spaceLeft = Integer.parseInt(bagSpace); //Parses the bag space as an integer
                int spaceNeeded = Integer.parseInt((String) context.get("?S"));
                return (!(spaceLeft >= spaceNeeded));
            }
        }
    }

    public Map<String, Object> modifyContext(Map<String, Object> context) {
        int bagNumber = Integer.parseInt((String) context.get("?N"));
        int bagNumberNew = bagNumber+1;
        String bagsSpaces = ((String) context.get("?BS"));
        String newBag = ("B"+String.valueOf(bagNumberNew) + "|" + String.valueOf(100)+"/"); //Concats the bagSpace string with the new bag
        String newBagSpaces = bagsSpaces.concat(newBag);
        context.put("?NB", String.valueOf(bagNumberNew));
        context.put("?RS", newBagSpaces);
        return context;
    }
}
