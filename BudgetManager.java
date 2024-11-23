import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BudgetManager {
    private double budgetLimit;
    private final Map<String, Double> categoryLimits = new HashMap<>();

    public void setBudgetLimit(Scanner scanner) {
        System.out.print("Nhập giới hạn ngân sách: ");
        budgetLimit = Double.parseDouble(scanner.nextLine());
        System.out.println("Giới hạn ngân sách tổng đã được thiết lập.");
    }

    public void setCategoryLimit(Scanner scanner) {
        System.out.print("Nhập danh mục cần thiết lập ngân sách (VD: Ăn uống): ");
        String category = scanner.nextLine();
        System.out.print("Nhập giới hạn ngân sách cho danh mục này: ");
        double limit = Double.parseDouble(scanner.nextLine());
        categoryLimits.put(category, limit);
        System.out.println("Giới hạn ngân sách cho danh mục " + category + " đã được thiết lập.");
    }

    public void checkBudgetStatus(double totalExpenses) {
        if (budgetLimit == 0) {
            System.out.println("Bạn chưa thiết lập ngân sách tổng.");
        } else if (totalExpenses > budgetLimit) {
            System.out.println("Bạn đã vượt ngân sách tổng!");
        } else {
            double percentageUsed = (totalExpenses / budgetLimit) * 100;
            System.out.printf("Bạn đã tiêu %.2f%% ngân sách tổng.\n", percentageUsed);
            if (percentageUsed >= 80) {
                System.out.println("Cảnh báo: Ngân sách tổng đã tiêu 80% hoặc hơn!");
            }
        }
    }

    public void checkCategoryStatus(String category, double totalCategoryExpenses) {
        Double categoryLimit = categoryLimits.get(category);
        if (categoryLimit == null) {
            System.out.println("Chưa thiết lập giới hạn ngân sách cho danh mục " + category);
        } else if (totalCategoryExpenses > categoryLimit) {
            System.out.println("Bạn đã vượt ngân sách cho danh mục " + category + "!");
        } else {
            double percentageUsed = (totalCategoryExpenses / categoryLimit) * 100;
            System.out.printf("Bạn đã tiêu %.2f%% ngân sách cho danh mục %s.\n", percentageUsed, category);
            if (percentageUsed >= 80) {
                System.out.println("Cảnh báo: Ngân sách cho danh mục " + category + " đã tiêu 80% hoặc hơn!");
            }
        }
    }
}
