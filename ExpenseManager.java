import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseManager {
    private List<Expense> expenses;
    private String filePath;

    public ExpenseManager(String filePath) {
        this.expenses = new ArrayList<>();
        this.filePath = filePath;
    }

    // 1. Thêm khoản chi
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    // 2. Xóa khoản chi
    public void deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
        } else {
            System.out.println("Khoản chi không hợp lệ!");
        }
    }

    // 3. Xem tổng chi tiêu tháng
    public double getTotalExpenseByMonth(int month, int year) {
        return expenses.stream()
                .filter(expense -> expense.getDate().getMonthValue() == month && expense.getDate().getYear() == year)
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // 4. Xem chi tiêu theo loại
    public void displayExpensesByCategory(String category) {
        List<Expense> filteredExpenses = expenses.stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());

        if (filteredExpenses.isEmpty()) {
            System.out.println("Không có khoản chi nào thuộc loại: " + category);
        } else {
            filteredExpenses.forEach(expense -> System.out.println(expense.display()));
        }
    }

    // 5. Xem tất cả các khoản chi
    public void displayAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("Không có khoản chi nào.");
        } else {
            expenses.forEach(expense -> System.out.println(expense.display()));
        }
    }

    // 6. Lưu dữ liệu vào tệp TXT
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Expense expense : expenses) {
                writer.write(expense.toString());
                writer.newLine();
            }
            System.out.println("Dữ liệu đã được lưu vào file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 7. Đọc dữ liệu từ tệp TXT
    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            expenses.clear();  // Xóa danh sách cũ trước khi đọc dữ liệu mới
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                double amount = Double.parseDouble(data[0]);
                String category = data[1];
                String description = data[2];
                LocalDate date = LocalDate.parse(data[3]);
                expenses.add(new Expense(amount, category, description, date));
            }
            System.out.println("Dữ liệu đã được đọc từ file.");
            displayAllExpenses();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 8. Chỉnh sửa khoản chi
    public void editExpense(int index, Expense newExpense) {
        if (index >= 0 && index < expenses.size()) {
            expenses.set(index, newExpense);
            System.out.println("Khoản chi đã được chỉnh sửa.");
        } else {
            System.out.println("Khoản chi không hợp lệ!");
        }
    }
}
