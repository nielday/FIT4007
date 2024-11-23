import java.util.ArrayList;
import java.util.Scanner;

public class TransactionManager {
    private final ArrayList<Transaction> incomes = new ArrayList<>();
    private final ArrayList<Transaction> expenses = new ArrayList<>();
    private final ArrayList<Transaction> projectedIncomes = new ArrayList<>();
    private final ArrayList<Transaction> projectedExpenses = new ArrayList<>();

    // Thêm thu nhập
    public void addIncome(Scanner scanner) {
        incomes.add(createTransaction(scanner, "thu nhập"));
        System.out.println("Thu nhập đã được thêm thành công!");
    }

    // Thêm chi tiêu
    public void addExpense(Scanner scanner) {
        expenses.add(createTransaction(scanner, "chi tiêu"));
        System.out.println("Chi tiêu đã được thêm thành công!");
    }

    // Thêm dự thu
    public void addProjectedIncome(Scanner scanner) {
        projectedIncomes.add(createTransaction(scanner, "dự thu"));
        System.out.println("Dự thu đã được thêm thành công!");
    }

    // Thêm dự chi
    public void addProjectedExpense(Scanner scanner) {
        projectedExpenses.add(createTransaction(scanner, "dự chi"));
        System.out.println("Dự chi đã được thêm thành công!");
    }

    // Tạo giao dịch thu nhập, chi tiêu, dự thu, dự chi
    private Transaction createTransaction(Scanner scanner, String type) {
        System.out.print("Nhập số tiền " + type + ": ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Mô tả (cho cái gì): ");
        String description = scanner.nextLine();
        System.out.print("Loại: ");
        String category = scanner.nextLine();
        return new Transaction(amount, description, category);
    }

    // Hiển thị chi tiết thu nhập
    public void displayIncomeDetails() {
        displayTransactions("Thu nhập", incomes);
    }

    // Hiển thị chi tiết chi tiêu
    public void displayExpenseDetails() {
        displayTransactions("Chi tiêu", expenses);
    }

    // Hiển thị giao dịch
    private void displayTransactions(String type, ArrayList<Transaction> transactions) {
        System.out.println("\n====== CHI TIẾT " + type.toUpperCase() + " ======");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
    // Hiển thị dự thu và dự chi
    public void displayProjections() {
        System.out.println("\n====== DỰ THU ======");
        displayTransactions("Dự thu", projectedIncomes);

        System.out.println("\n====== DỰ CHI ======");
        displayTransactions("Dự chi", projectedExpenses);
    }
    // Xóa toàn bộ dữ liệu
    public void clearAllData() {
        incomes.clear();
        expenses.clear();
        projectedIncomes.clear();
        projectedExpenses.clear();
        System.out.println("Toàn bộ dữ liệu đã được xóa.");
    }

    // Tính thu nhập trung bình
    public void calculateAverageIncome() {
        System.out.println("Thu nhập trung bình: " + calculateAverage(incomes) + " VND");
    }

    // Tính chi tiêu trung bình
    public void calculateAverageExpense() {
        System.out.println("Chi tiêu trung bình: " + calculateAverage(expenses) + " VND");
    }

    // Tính trung bình của danh sách giao dịch
    private double calculateAverage(ArrayList<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getAmount).average().orElse(0);
    }

    // Tìm giao dịch theo số tiền
    public void searchTransaction(Scanner scanner) {
        System.out.print("Nhập số tiền cần tìm: ");
        double amount = Double.parseDouble(scanner.nextLine());
        boolean found = incomes.stream().anyMatch(t -> t.getAmount() == amount) ||
                expenses.stream().anyMatch(t -> t.getAmount() == amount);
        System.out.println(found ? "Tìm thấy giao dịch." : "Không tìm thấy giao dịch.");
    }

    // Tính tổng chi tiêu
    public double getTotalExpenses() {
        return expenses.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public double getCategoryExpenses(String category) {
        return expenses.stream()
                .filter(t -> t.getCategory().equalsIgnoreCase(category))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Lấy danh sách thu nhập
    public ArrayList<Transaction> getIncomes() {
        return incomes;
    }

    // Lấy danh sách chi tiêu
    public ArrayList<Transaction> getExpenses() {
        return expenses;
    }

    // Lấy danh sách dự thu
    public ArrayList<Transaction> getProjectedIncomes() {
        return projectedIncomes;
    }

    // Lấy danh sách dự chi
    public ArrayList<Transaction> getProjectedExpenses() {
        return projectedExpenses;
    }
}
