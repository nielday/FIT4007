import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseManager {
    private List<Expense> expenses;  // Danh sách các khoản chi tiêu
    private final String filePath = "expenses.txt";  // Đường dẫn tệp lưu chi tiêu

    public ExpenseManager() {
        expenses = new ArrayList<>();
        loadFromFile();  // Đọc dữ liệu từ file khi khởi động
    }

    // Thêm khoản chi
    public void addExpense(Expense expense) {
        expenses.add(expense);
        saveToFile();  // Lưu dữ liệu sau khi thêm khoản chi mới
    }

    // Xóa khoản chi theo chỉ số
    public void removeExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
            saveToFile();  // Lưu lại danh sách sau khi xóa
        } else {
            System.out.println("Không tìm thấy khoản chi với chỉ số này.");
        }
    }

    // Tính tổng chi tiêu cho một tháng cụ thể
    public double getTotalExpensesForMonth(int month, int year) {
        return expenses.stream()
                .filter(expense -> expense.getDate().getMonthValue() == month && expense.getDate().getYear() == year)
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // Lưu dữ liệu chi tiêu vào file
    public void saveToFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Expense expense : expenses) {
                writer.write(expense.getAmount() + "," + expense.getCategory() + "," + expense.getDescription() + "," + expense.getDate() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu dữ liệu vào tệp: " + e.getMessage());
        }
    }

    // Đọc dữ liệu chi tiêu từ file
    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                double amount = Double.parseDouble(data[0]);
                String category = data[1];
                String description = data[2];
                LocalDate date = LocalDate.parse(data[3]);
                expenses.add(new Expense(amount, category, description, date));
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc dữ liệu từ tệp: " + e.getMessage());
        }
    }
}
