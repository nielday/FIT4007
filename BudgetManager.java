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

public void setCategoryLimit(String category, double limit) {
    categoryLimits.put(category, limit);
    System.out.println("Category " + category + " limit set to: " + limit);
}

public void checkBudgetStatus(double totalExpenses) {
    double percentageUsed = (totalExpenses / budgetLimit) * 100;
    System.out.printf("You have used %.2f%% of your total budget.\n", percentageUsed);
}

public void checkCategoryStatus(String category, double totalCategoryExpenses) {
    if (!categoryLimits.containsKey(category)) {
        System.out.println("Error: No limit set for category " + category);
    } else {
        double limit = categoryLimits.get(category);
        double percentageUsed = (totalCategoryExpenses / limit) * 100;
        System.out.printf("You have used %.2f%% of your budget for %s.\n", percentageUsed, category);
    }
}
