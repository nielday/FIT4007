import java.util.HashMap;
import java.util.Map;

public class BudgetManager {
    private double budgetLimit;
    private final Map<String, Double> categoryLimits = new HashMap<>();
}

public void setBudgetLimit(double limit) {
    budgetLimit = limit;
    System.out.println("Total budget limit set to: " + budgetLimit);
}
