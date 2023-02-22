package assignmenttwo.production;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * pmatch.production.Production.java
 * Abstract class for productions
 * which have antecedents, a predicate method, a modifyContext method, add actions, delete actions & remark actions
 * the antecedents are matched against the STM. If they match a context is established
 * if the canRunInContext succeeds for the context, modifyContext runs,
 * then the actions are carried out using the resulting context
 *
 * @author Phil Green
 * 2013 version
 * <p>
 * 2020 Heidi Christensen (heidi.christensen@sheffield.ac.uk)
 */
public interface Production {
    List<Production> PRODUCTIONS_LIST = Arrays.asList(
            new BasicStartBagging(),
            new BasicGetNextItem(),
            new BasicPutInCurrentBag(),
            new BasicStartNewBag());

    String getName();

    String[] getAntecedents();

    String[] getAdditions();

    String[] getDeletions();

    String[] getRemarks();

    boolean canRunInContext(Map<String, Object> context);

    Map<String, Object> modifyContext(Map<String, Object> context);
}
