import java.util.Scanner;

public class FinancialManager {
    private final Scanner scanner = new Scanner(System.in);
    private final TransactionManager transactionManager = new TransactionManager();
    private final BudgetManager budgetManager = new BudgetManager();
    private final ReportManager reportManager = new ReportManager(transactionManager, budgetManager);

    public void run() {
        while (true) {
            displayMenu();
            int choice = getChoice();
            switch (choice) {
                case 1 -> transactionManager.addIncome(scanner);
                case 2 -> transactionManager.addExpense(scanner);
                case 3 -> transactionManager.displayIncomeDetails();
                case 4 -> transactionManager.displayExpenseDetails();
                case 5 -> transactionManager.clearAllData();
                case 6 -> budgetManager.setBudgetLimit(scanner);
                case 7 -> budgetManager.setCategoryLimit(scanner);
                case 8 -> budgetManager.checkBudgetStatus(transactionManager.getTotalExpenses());
                case 9 -> {
                    System.out.print("Nhập danh mục cần kiểm tra: ");
                    String category = scanner.nextLine();
                    double categoryExpenses = transactionManager.getCategoryExpenses(category);
                    budgetManager.checkCategoryStatus(category, categoryExpenses);
                }
                case 10 -> transactionManager.calculateAverageIncome();
                case 11 -> transactionManager.calculateAverageExpense();
                case 12 -> transactionManager.searchTransaction(scanner);
                case 13 -> transactionManager.addProjectedIncome(scanner);
                case 14 -> transactionManager.addProjectedExpense(scanner);
                case 15 -> transactionManager.displayProjections(); // New feature
                case 16 -> reportManager.exportReport();
                case 17 -> {
                    System.out.println("Thoát chương trình. Tạm biệt!");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại!");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n====== MENU QUẢN LÝ TÀI CHÍNH ======");
        System.out.println("1. Thêm thu nhập");
        System.out.println("2. Thêm chi tiêu");
        System.out.println("3. Hiển thị chi tiết thu nhập");
        System.out.println("4. Hiển thị chi tiết chi tiêu");
        System.out.println("5. Xóa toàn bộ dữ liệu");
        System.out.println("6. Thiết lập giới hạn ngân sách tổng");
        System.out.println("7. Thiết lập giới hạn ngân sách danh mục");
        System.out.println("8. Kiểm tra trạng thái ngân sách tổng");
        System.out.println("9. Kiểm tra trạng thái ngân sách danh mục");
        System.out.println("10. Tính trung bình thu nhập");
        System.out.println("11. Tính trung bình chi tiêu");
        System.out.println("12. Tìm kiếm giao dịch");
        System.out.println("13. Thêm dự thu");
        System.out.println("14. Thêm dự chi");
        System.out.println("15. Hiển thị dự thu và dự chi"); // New menu option
        System.out.println("16. Xuất báo cáo tài chính");
        System.out.println("17. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }



    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
