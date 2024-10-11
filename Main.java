import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();
        boolean running = true;

        while (running) {
            System.out.println("\nChọn một hành động:");
            System.out.println("1. Thêm khoản chi");
            System.out.println("2. Xóa khoản chi");
            System.out.println("3. Chỉnh sửa khoản chi");
            System.out.println("4. Xem tổng chi tiêu tháng");
            System.out.println("5. Xem chi tiêu theo loại");
            System.out.println("6. Xem tất cả các khoản chi");
            System.out.println("7. Lưu dữ liệu vào file");
            System.out.println("8. Đọc dữ liệu từ file");
            System.out.println("9. Thoát");

            System.out.println("Vui lòng lựa chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // clear buffer


            switch (choice) {
                case 1:
                    System.out.println("Nhập số tiền:");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Nhập loại chi tiêu:");
                    String category = scanner.nextLine();

                    System.out.println("Nhập mô tả:");
                    String description = scanner.nextLine();

                    System.out.println("Nhập ngày chi tiêu (YYYY-MM-DD):");
                    LocalDate date = LocalDate.parse(scanner.nextLine());

                    Expense expense = new Expense(amount, category, description, date);
                    manager.addExpense(expense);
                    System.out.println("Đã thêm khoản chi tiêu!");
                    break;

                case 2:
                    System.out.println("Nhập chỉ số khoản chi muốn xóa:");
                    int indexToRemove = scanner.nextInt();
                    manager.removeExpense(indexToRemove);
                    System.out.println("Đã xóa khoản chi.");
                    break;

                case 3:
                    System.out.println("Nhập chỉ số khoản chi muốn chỉnh sửa:");
                    int indexToEdit = scanner.nextInt();
                    scanner.nextLine(); // clear buffer

                    System.out.println("Nhập số tiền mới:");
                    double newAmount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Nhập loại chi tiêu mới:");
                    String newCategory = scanner.nextLine();

                    System.out.println("Nhập mô tả mới:");
                    String newDescription = scanner.nextLine();

                    System.out.println("Nhập ngày chi tiêu mới (YYYY-MM-DD):");
                    LocalDate newDate = LocalDate.parse(scanner.nextLine());

                    Expense updatedExpense = new Expense(newAmount, newCategory, newDescription, newDate);
                    manager.editExpense(indexToEdit, updatedExpense);
                    System.out.println("Đã chỉnh sửa khoản chi tiêu.");
                    break;

                case 4:
                    System.out.println("Nhập tháng (1-12):");
                    int month = scanner.nextInt();
                    System.out.println("Nhập năm:");
                    int year = scanner.nextInt();

                    double totalExpenses = manager.getTotalExpensesForMonth(month, year);
                    System.out.println("Tổng chi tiêu trong tháng " + month + "/" + year + ": " + totalExpenses);
                    break;

                case 5:
                    System.out.println("Nhập loại chi tiêu để xem:");
                    String categoryToView = scanner.nextLine();
                    List<Expense> expensesByCategory = manager.getExpensesByCategory(categoryToView);
                    expensesByCategory.forEach(System.out::println);
                    break;

                case 6:
                    manager.getAllExpenses().forEach(System.out::println);
                    break;

                case 7:
                    manager.saveToFile();
                    System.out.println("Dữ liệu đã được lưu vào file.");
                    break;

                case 8:
                    manager.loadFromFile();
                    System.out.println("Dữ liệu đã được đọc từ file.");
                    break;

                case 9:
                    running = false;
                    System.out.println("Tạm biệt!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ.");
                    break;
            }
        }

        scanner.close();
    }
}
