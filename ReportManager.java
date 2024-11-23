public class ReportManager {
    private final TransactionManager transactionManager;
    private final BudgetManager budgetManager;

    public ReportManager(TransactionManager transactionManager, BudgetManager budgetManager) {
        this.transactionManager = transactionManager;
        this.budgetManager = budgetManager;
    }
}
