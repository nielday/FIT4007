import java.io.FileWriter;
import java.io.IOException;

public class ReportManager {
    private final TransactionManager transactionManager;
    private final BudgetManager budgetManager;

    public ReportManager(TransactionManager transactionManager, BudgetManager budgetManager) {
        this.transactionManager = transactionManager;
        this.budgetManager = budgetManager;
    }

    public void exportReport() {
        try (FileWriter writer = new FileWriter("Report.txt")) {
            writer.write("====== BÁO CÁO TÀI CHÍNH ======\n");
            writer.write("Thu nhập:\n");
            for (Transaction t : transactionManager.getIncomes()) {
                writer.write(t + "\n");
            }
            writer.write("Chi tiêu:\n");
            for (Transaction t : transactionManager.getExpenses()) {
                writer.write(t + "\n");
            }
            System.out.println("Báo cáo đã được xuất ra tệp Report.txt.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi xuất báo cáo.");
        }
    }
}