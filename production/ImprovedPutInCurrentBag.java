package assignmenttwo.production;

import java.util.*;

/**
 * Production rule for ImprovedBagger System
 * Puts item selected item in current bag
 * @author Ethan Jones @aca19ej
 */
public class ImprovedPutInCurrentBag implements Production {

    private static final String NAME = "Put In Current Bag";
    private static final String[] ANTECEDENTS = {"current step is: bag an item", "current bag is number ?N", "item to bag: ?I of space: ?S", "bags have space ?BS"};
    private static final String[] ADDITIONS = {"current step is: get the next item", "bag number ?N contains ?I", "bags have space ?RS"};
    private static final String[] DELETIONS = {"current step is: bag an item", "item to bag: ?I of space: ?S", "bags have space ?BS"};
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
        int bagNumber = Integer.parseInt((String) context.get("?N"));
        String bagsSpaces = ((String) context.get("?BS"));
        if (bagNumber > 1){
            int endIndex =  bagsSpaces.indexOf("/B"+ String.valueOf(bagNumber+1));
            if (endIndex > 0){ //If next bag doesn't exist, endIndex = -1, checks if the bag is the last one in use
                int startIndex =  bagsSpaces.indexOf("B"+ String.valueOf(bagNumber));
                String bagInfo = bagsSpaces.substring(startIndex, endIndex); //Creates a substring that contains the bag No. and bag space
                int verticalpipeIndex = bagInfo.lastIndexOf("|"); //Vertical pipe seperates the bag No. with the space left
                String bagSpace = bagInfo.substring(verticalpipeIndex+1,bagInfo.length());
                int spaceLeft = Integer.parseInt(bagSpace); //Finds bag space from the bagInfo substring and parses it as an integer
                int spaceNeeded = Integer.parseInt((String) context.get("?S"));
                return (spaceLeft >= spaceNeeded); //Checks if there enough space in the current bag
            }
            else{ //Else statement is for if the current bag is the last bag in use. Uses a similar process as above to check if there is enough space
                int endIndexA =  bagsSpaces.lastIndexOf("/");
                int verticalpipeIndex = bagsSpaces.lastIndexOf("|");
                String bagSpace = bagsSpaces.substring(verticalpipeIndex+1, endIndexA);
                int spaceLeft = Integer.parseInt(bagSpace);
                int spaceNeeded = Integer.parseInt((String) context.get("?S"));
                return (spaceLeft >= spaceNeeded);
            }
        }
        else{ //Else statement is for if the bag number is 1, Uses same process as above to check if there is enough space
            int startIndex =  bagsSpaces.indexOf("B"+ String.valueOf(bagNumber));
            int endIndexA =  bagsSpaces.indexOf("/");
            String bagInfo = bagsSpaces.substring(startIndex, endIndexA);
            int verticalpipeIndex = bagInfo.indexOf("|");
            String bagSpace = bagInfo.substring(verticalpipeIndex+1, endIndexA);
            int spaceLeft = Integer.parseInt(bagSpace);
            int spaceNeeded = Integer.parseInt((String) context.get("?S"));
            return (spaceLeft >= spaceNeeded);
        }
    }

    public Map<String, Object> modifyContext(Map<String, Object> context) {
        int bagNumber = Integer.parseInt((String) context.get("?N"));
        if (bagNumber > 1){
            String bagsSpaces = ((String) context.get("?BS"));
            int startIndex =  bagsSpaces.indexOf("B"+ String.valueOf(bagNumber));
            int endIndex =  bagsSpaces.indexOf("/B"+ String.valueOf(bagNumber+1));
            if (endIndex <= -1){ //Next bag doesn't exist if endIndex <= -1
                int endIndexActual =  bagsSpaces.lastIndexOf("/");
                int verticalpipeIndex = bagsSpaces.lastIndexOf("|");
                String bagSpace = bagsSpaces.substring(verticalpipeIndex+1,endIndexActual);
                int spaceLeft = Integer.parseInt(bagSpace);
                int spaceNeeded = Integer.parseInt((String) context.get("?S"));
                int newSpaceLeft = spaceLeft-spaceNeeded; //Calculates new space in the bag
                String bagInfo = bagsSpaces.substring(startIndex, endIndexActual);
                String newSpaceString = String.valueOf(newSpaceLeft); 
                String newBagInfo = ("B"+String.valueOf(bagNumber) + "|" + newSpaceString); //Builds a new substring for the bag
                String newBagSpaces = bagsSpaces.replace(bagInfo, newBagInfo); //Replaces old substring with new, updated one
                context.put("?RS", newBagSpaces);
                return context;
            }
            else{ //Same process as above, builds a new substring and replaces the old one
                String bagInfo = bagsSpaces.substring(startIndex, endIndex);
                int verticalpipeIndex = bagInfo.indexOf("|");
                String bagSpace = bagInfo.substring(verticalpipeIndex+1, bagInfo.length());
                int spaceLeft = Integer.parseInt(bagSpace);
                int spaceNeeded = Integer.parseInt((String) context.get("?S"));
                int newSpaceLeft = spaceLeft-spaceNeeded;
                String newSpaceString = String.valueOf(newSpaceLeft);
                String newBagInfo = ("B"+String.valueOf(bagNumber) + "|" + newSpaceString);
                String newBagSpaces = bagsSpaces.replace(bagInfo, newBagInfo);
                context.put("?RS", newBagSpaces);
                return context;
            }
        }
        else{ //Same process as above, builds a new substring and replaces the old one. However differs slightly as bag number is equal to 1
            String bagsSpaces = ((String) context.get("?BS"));
            int startIndex =  bagsSpaces.indexOf("B"+ String.valueOf(bagNumber));
            int endIndex =  bagsSpaces.indexOf("/");
            String bagInfo = bagsSpaces.substring(startIndex, endIndex);
            int verticalpipeIndex = bagInfo.indexOf("|");
            String bagSpace = bagInfo.substring(verticalpipeIndex+1, endIndex);
            int spaceLeft = Integer.parseInt(bagSpace);
            int spaceNeeded = Integer.parseInt((String) context.get("?S"));
            int newSpaceLeft = spaceLeft-spaceNeeded;
            String newSpaceString = String.valueOf(newSpaceLeft);
            String newBagInfo = ("B"+String.valueOf(bagNumber) + "|" + newSpaceString);
            String newBagSpaces = bagsSpaces.replace(bagInfo, newBagInfo);
            context.put("?RS", newBagSpaces);
            return context;
        }
    }
}