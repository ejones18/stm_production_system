package assignmenttwo;

import assignmenttwo.logger.LoggerHandler;
import assignmenttwo.production.ProductionSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TestProductionSystem
 * testing production systems
 * 2013 Version Phil Green
 * <p>
 * 2020 Heidi Christensen (heidi.christensen@sheffield.ac.uk)
 */
public class RunProductionSystem {
    private static final Logger LOGGER = LoggerHandler.getLogger();

    public static void main(String[] arg) {

        // initial facts; feel free to amend them however you want
        List<String> shortTermMemory = new ArrayList<>();
        shortTermMemory.add("current step is: start bagging");
        shortTermMemory.add("the trolley contains bread of space: 5");
        shortTermMemory.add("the trolley contains spuds of space: 10");
        shortTermMemory.add("the trolley contains cornflakes of space: 40");

        ProductionSystem productionSystem = new ProductionSystem();
        List<String> result = productionSystem.runProductionSystem(shortTermMemory);
        LOGGER.log(Level.INFO, "Final Short Term Memory: {0}", result);
    }
}
