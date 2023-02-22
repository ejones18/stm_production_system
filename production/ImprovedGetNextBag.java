package assignmenttwo.production;

import java.util.*;

/**
 * Production rule for ImprovedBagger System
 * Fetches the next bag
 * @author Ethan Jones @aca19ej
 */
public class ImprovedGetNextBag implements Production {

    private static final String NAME = "Get next bag";
    private static final String[] ANTECEDENTS = {"current step is: bag an item", "current bag is number ?N", "item to bag: ?I of space: ?S", "bags have space ?BS"};
    private static final String[] ADDITIONS = {"current bag is number ?NB"};
    private static final String[] DELETIONS = {"current bag is number ?N"};
    private static final String[] REMARKS = {"Changing to next bag"};

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
            String bagsSpaces = ((String) context.get("?BS"));
            int startIndex =  bagsSpaces.indexOf("B"+ String.valueOf(bagNumber));
            int endIndex =  bagsSpaces.indexOf("/B"+ String.valueOf(bagNumber+1));//Finds the substring of the current bag
            if (endIndex < 0){ //If endIndex (start of next bag) doesn't exist (< 0) then there is no next bag to get hence returning false
                return false;
            }
            String bagInfo = bagsSpaces.substring(startIndex, endIndex); //Creates a string of the bag info - bag No. and space left
            int verticalpipeIndex = bagInfo.indexOf("|"); //Finds index of vertical pipe that separates the bag No. and its space left
            String bagSpace = bagInfo.substring(verticalpipeIndex+1, bagInfo.length());
            int spaceLeft = Integer.parseInt(bagSpace); //Parses the bag space as an integer
            int spaceNeeded = Integer.parseInt((String) context.get("?S"));
            if(spaceLeft < spaceNeeded){
                return true; //If the item cannot fit in the current bag then the next bag can be fetched
            }
        }
        else{//Substring is slightly different when there is just one bag hence else statement
            String bagsSpaces = ((String) context.get("?BS"));
            int startIndex =  bagsSpaces.indexOf("B"+ String.valueOf(bagNumber));
            int endIndex =  bagsSpaces.indexOf("/");
            String bagInfo = bagsSpaces.substring(startIndex, endIndex);
            int verticalpipeIndex = bagInfo.indexOf("|");//Uses the same substring manipulation as above
            String bagSpace = bagInfo.substring(verticalpipeIndex+1, endIndex);
            int spaceLeft = Integer.parseInt(bagSpace);
            int spaceNeeded = Integer.parseInt((String) context.get("?S"));
            if(spaceLeft >= spaceNeeded){
                return false;
            }
            else{
                try{ //Uses a try-catch statement to check if the bag is the last one (this is only used when there is only one bag exactly)
                    int testingIndex = endIndex + 4;
                    char test = bagsSpaces.charAt(testingIndex);
                    return true;
                }
                catch( IndexOutOfBoundsException e ){
                    return false;
                }
            }
        }
        return false;
    }

    public Map<String, Object> modifyContext(Map<String, Object> context) {
        int bagNumber = Integer.parseInt((String) context.get("?N"));
        context.put("?NB", String.valueOf(bagNumber + 1));
        return context;
    }
}